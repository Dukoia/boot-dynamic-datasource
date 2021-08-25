package com.dukoia.boot;

import cn.hutool.json.JSONUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dukoia.boot.content.UserContent;
import com.dukoia.boot.mapper.ConfigInfoMapper;
import com.dukoia.boot.model.ConfigInfoDO;
import com.dukoia.boot.model.PromoteImageDO;
import com.dukoia.boot.model.UserInfo;
import com.dukoia.boot.service.ConfigInfoService;
import com.dukoia.boot.service.IUserInfoService;
import com.dukoia.boot.service.PromoteImageService;
import com.dukoia.boot.utils.JacksonUtil;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.IdsQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@SpringBootTest
@Slf4j
class BootApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    PromoteImageService promoteImageService;

//    @Autowired
//    PromoteImageMapper promoteImageMapper;

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Autowired
    ConfigInfoService configInfoMapper;

    @Autowired
    ConfigInfoMapper mapper;

    @Autowired
    IUserInfoService iUserInfoService;

    @Test
    @DS("master")
    public void apply(){
        QueryWrapper<ConfigInfoDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("YEAR(gmt_create) = {0}",2019).orderByAsc("gmt_create").last("limit 1");

        List<ConfigInfoDO> list = configInfoMapper.list(queryWrapper);
        System.out.println(list);
    }

    @Test
    public void limit() throws InterruptedException {

        for (int i = 0; i < 15; i++) {
            boolean res = isPeriodLimiting("java", 3, 10);
            if (res) {
                System.out.println("正常执行请求：" + i);
            } else {
                System.out.println("被限流：" + i);
            }
        }
        // 休眠 4s
        Thread.sleep(4000);
        // 超过最大执行时间之后，再从发起请求
        boolean res = isPeriodLimiting("java", 3, 10);
        if (res) {
            System.out.println("休眠后，正常执行请求");
        } else {
            System.out.println("休眠后，被限流");
        }
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
        long nowTs = System.currentTimeMillis(); // 当前时间戳
        // 删除非时间段内的请求数据（清除老访问数据，比如 period=60 时，标识清除 60s 以前的请求记录）
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.removeRangeByScore(key, 0, nowTs - period * 1000);
        long currCount = zSetOperations.zCard(key);// 当前请求次数
        if (currCount >= maxCount) {
            // 超过最大请求次数，执行限流
            return false;
        }
        // 未达到最大请求数，正常执行业务
        zSetOperations.add(key, nowTs, nowTs);
        redisTemplate.expire(key, 100, TimeUnit.SECONDS);
        return true;
    }

    @Test
    public void future() throws ExecutionException, InterruptedException {
        CompletableFuture<String> string = CompletableFuture.supplyAsync(() -> {
            return "hello";
        });
        CompletableFuture<List<UserInfo>> list = CompletableFuture.supplyAsync(() -> {
            return iUserInfoService.list();
        });
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("11111");
        });
        CompletableFuture<Void> allOf = CompletableFuture.allOf(future, string, list);
        allOf.join();

        System.out.println(string.get());
        System.out.println(JSONUtil.toJsonStr(list.get()));
        System.out.println(JacksonUtil.toJson(list.get()));
    }

    @Test
    @DS("master")
    public void userinfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("duko211");
        userInfo.setGender(1);
        userInfo.setEmail("hhaha12111@sina.com");
        userInfo.setVersion(1);
        iUserInfoService.save(userInfo);
//        iUserInfoService.updateById(userInfo);

        List<UserInfo> list = iUserInfoService.list();
        System.out.println(JacksonUtil.toJson(list));
//        boolean save = iUserInfoService.save(userInfo);
//        System.out.println(save);

//        boolean b = iUserInfoService.removeById(1417304903917359105L);
//        System.out.println(b);
    }

    @Test
    public void filter() {
        BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), 1000, 0.0000001);
        bloomFilter.put("abc");
        boolean isContains = bloomFilter.mightContain("abc");
        System.out.println(isContains);
    }

    @Test
    @DS("master")
    public void addDoc() {
        Date date = new Date();
        LocalDateTime now = LocalDateTime.now();
        System.out.println(date + "--" + now);
        @NotBlank String s = "";

        System.out.println("========");
//        List<ForumDto> forums = configInfoMapper.get();
//
//        BulkRequest request = new BulkRequest();
//        IndexRequest indexRequest;
//        for (ForumDto tid : forums) {
//
//            indexRequest = new IndexRequest("forum", "_doc", String.valueOf(tid.getId())).source(JSONUtil.toJsonStr(tid),XContentType.JSON);
//            request.add(indexRequest);
//        }
//        try {
//            restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
//        } catch (IOException e) {
//            log.error("批量更新用户帖子失败", e);
//        }
    }

    @Test
    public void search() throws IOException {
        SearchRequest searchRequest = new SearchRequest("forum");
        //搜索源构建对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //搜索方式 在[subject, message]字段上搜索，匹配度=50%、将标题得分提高10倍
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("美国", "subject", "message").field("subject",50);

        searchSourceBuilder.query(multiMatchQueryBuilder);

        //向搜索请求对象中设置搜索源
        searchRequest.source(searchSourceBuilder);
        //执行搜索
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        for (SearchHit hit : searchResponse.getHits()) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            log.info(JSONUtil.toJsonStr(sourceAsMap));
//            ForumDto forumDto = JSONUtil.toBean(JSONUtil.toJsonStr(sourceAsMap), ForumDto.class);
//            log.info(JSONUtil.toJsonStr(forumDto));
        }

    }

    @Test
    public void es() throws IOException {
        SearchRequest commodity = new SearchRequest("commodity");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        IdsQueryBuilder idsQueryBuilder = new IdsQueryBuilder();
        idsQueryBuilder.addIds("112");
        searchSourceBuilder.query(idsQueryBuilder);
        commodity.source(searchSourceBuilder);
        SearchResponse search = restHighLevelClient.search(commodity, RequestOptions.DEFAULT);
        System.out.println(search);
    }

    @Test
    public void redis() {
        HashOperations hashOperations = redisTemplate.opsForHash();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("hello", "hello");

        System.out.println(valueOperations.get("hello"));

        hashOperations.put("dukoia", "name", "dukoia123");
        System.out.println(hashOperations.get("dukoia", "age"));
        Map dukoia = hashOperations.entries("dukoia");
        System.out.println(dukoia);

    }

    @Test
    public void put() {
        PromoteImageDO picoPromoteImageDO = new PromoteImageDO();

        picoPromoteImageDO.setImageUrl("http://hello.com");
        picoPromoteImageDO.setSubjectName("master");
        picoPromoteImageDO.setCreateUser("dukoia");
        promoteImageService.save(picoPromoteImageDO);
    }

    @Test
    void contextLoads() throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

//        executorService.submit(() -> {
//            System.out.println("==========");
//        });
        executorService.execute(() -> {
            System.out.println("========");
            UserContent.put("123");

        });
        System.out.println("========");
        UserContent.put("123");
        System.out.println("11111" + UserContent.get());

        executorService.execute(() -> {
            try {
                System.out.println("222" + UserContent.get());
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        System.out.println("=============");
        executorService.execute(() -> {
            System.out.println(UserContent.get());
        });

    }

}
