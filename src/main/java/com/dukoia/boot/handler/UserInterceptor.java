package com.dukoia.boot.handler;

import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import com.dukoia.boot.common.AccessLimit;
import com.dukoia.boot.common.Result;
import com.dukoia.boot.content.UserContent;
import com.dukoia.boot.enums.ReturnCode;
import com.dukoia.boot.utils.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author jiangze.he
 */
//@Component
@Slf4j
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate redisTemplate;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String authorization = request.getHeader("Authorization");
        String uid = UUID.randomUUID().toString().replace("-", "");
        UserContent.put(uid);
        ThreadContext.put("mark", uid);

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            AccessLimit methodAnnotation = handlerMethod.getMethodAnnotation(AccessLimit.class);
            boolean b = handlerMethod.hasMethodAnnotation(AccessLimit.class);
            if (!method.isAnnotationPresent(AccessLimit.class)) {
                return true;
            }
            AccessLimit accessLimit = method.getAnnotation(AccessLimit.class);
            if (accessLimit == null) {
                return true;
            }
            int limit = accessLimit.limit();
            int sec = accessLimit.sec();
            String key = request.getRequestURI() + ":" + IpUtil.getIpAddr(request);
            if (!isPeriodLimiting(key, sec, limit)) {
                response.reset();
                //设置编码格式
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=UTF-8");

                PrintWriter writer = response.getWriter();
                writer.write(JSONUtil.toJsonStr(Result.fail(ReturnCode.RC200)));
                writer.flush();
                writer.close();
                return false;
            }
            return true;
        }

        return true;
    }

    /**
     * 限流方法（滑动时间算法）
     *
     * @param key      限流标识
     * @param period   限流时间范围（单位：秒）
     * @param maxCount 最大运行访问次数
     * @return
     */
    private boolean isPeriodLimiting(@Nullable String key, int period, int maxCount) {
        long nowTs = System.currentTimeMillis();
        // 删除非时间段内的请求数据（清除老访问数据，比如 period=60 时，标识清除 60s 以前的请求记录）
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        Assert.notNull(key, "限流接口参数不为空");
        zSetOperations.removeRangeByScore(key, 0, nowTs - period * 1000L);
        // 当前请求次数
        long currCount = zSetOperations.zCard(key);
        if (currCount >= maxCount) {
            // 超过最大请求次数，执行限流
            return false;
        }
        // 未达到最大请求数，正常执行业务
        zSetOperations.add(key, nowTs, nowTs);
        redisTemplate.expire(key, 100, TimeUnit.SECONDS);
        return true;
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        UserContent.clean();
        log.info("postHandle clean 之后 mark:{}", UserContent.get());
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
        UserContent.clean();
        log.info("afterCompletion clean 之后 mark:{}", UserContent.get());
    }
}
