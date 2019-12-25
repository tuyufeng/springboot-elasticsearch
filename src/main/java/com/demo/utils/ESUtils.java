package com.demo.utils;/**
 * Created by Enzo Cotter on 2019/12/24.
 */

import com.demo.bean.Student;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2017-2019, Landy
 * Author: Administrator
 * Date: 2019/12/24 8:50
 * FileName: ESUtils
 * Description:
 */
public class ESUtils {

    //进行转换Es语句快速存储
    public static void add(ElasticsearchTemplate es , List<Student> list){

        List<IndexQuery> indexQueries = new ArrayList<>();
        for (Student item:list){
            IndexQuery query = new IndexQueryBuilder().withId(item.getId()).withObject(item).build();
            indexQueries.add(query);
        }
        System.out.println("转换Es语句"+indexQueries);
        es.bulkIndex(indexQueries);
    }



    public static JestClient jestClient;


    //创建节点
    public  void createIndex(String index) {
        try {
            JestResult jestResult = jestClient.execute(new CreateIndex.Builder(index).build());
            System.out.println("createIndex:{}" + jestResult.isSucceeded());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //删除index节点
    public void deleteIndex(String index) {
        try {
            JestResult jestResult = jestClient.execute(new DeleteIndex.Builder(index).build());
            System.out.println("deleteIndex result:{}" + jestResult.isSucceeded());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }





}
