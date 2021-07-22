package com.dukoia.boot.handler;

import cn.hutool.core.lang.UUID;
import com.dukoia.boot.content.UserContent;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jiangze.he
 */
@Component
@Slf4j
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String authorization = request.getHeader("Authorization");
        String uid = UUID.randomUUID().toString().replace("-", "");
        UserContent.put(uid);
        ThreadContext.put("mark", uid);
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
