package com.dukoia.boot;

import cn.hutool.json.JSONUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.dukoia.boot.content.UserContent;
import com.dukoia.boot.mapper.ConfigInfoMapper;
import com.dukoia.boot.model.PromoteImageDO;
import com.dukoia.boot.service.ConfigInfoService;
import com.dukoia.boot.service.PromoteImageService;
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

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
@Slf4j
class BootApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    PromoteImageService promoteImageService;

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Autowired
    ConfigInfoService configInfoMapper;

    @Autowired
    ConfigInfoMapper mapper;

    @Test
    public void filter(){
        BloomFilter<CharSequence> bloomFilter  = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()),1000,0.0000001);
        bloomFilter.put("abc");
        boolean  isContains = bloomFilter.mightContain("abc");
        System.out.println(isContains );
    }

    @Test
    @DS("master")
    public void addDoc(){

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
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("美国", "subject", "message");

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
    public void redis(){
        HashOperations hashOperations = redisTemplate.opsForHash();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("hello","hello");

        System.out.println(valueOperations.get("hello"));

        hashOperations.put("dukoia","name","dukoia123");
        System.out.println(hashOperations.get("dukoia", "age"));
        Map dukoia = hashOperations.entries("dukoia");
        System.out.println(dukoia);

    }
    @Test
    public void put(){
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
        executorService.execute(() ->{
            System.out.println("========");
            UserContent.put("123");

        });
        System.out.println("========");
        UserContent.put("123");
        System.out.println("11111" + UserContent.get());

        executorService.execute(() ->{
            try {
                System.out.println("222" + UserContent.get());
            } catch(Exception e) {
                e.printStackTrace();
            }

        });
        System.out.println("=============");
        executorService.execute(() -> {
            System.out.println(UserContent.get());
        });

    }

}
