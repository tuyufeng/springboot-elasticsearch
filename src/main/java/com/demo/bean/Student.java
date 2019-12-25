package com.demo.bean;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Document(indexName = "item",type = "docs", shards = 1, replicas = 0, refreshInterval = "1s", createIndex = false)
@Data
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student implements Serializable {

    private static final long serialVersionUID = -2897922709266157634L;
    @Id
    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title; //标题
    @JsonProperty("brand")
    private String brand; // 品牌
    @JsonProperty("price")
    private String price; // 价格


}
