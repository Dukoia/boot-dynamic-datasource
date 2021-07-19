package com.dukoia.boot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: ForumDto
 * @Author: jiangze.He
 * @Date: 2021-07-15
 * @Version: v1.0
 */
@NoArgsConstructor
@Data
public class ForumDto {


    @JsonProperty("dateline")
    private Integer dateline;
    @JsonProperty("author")
    private String author;
    @JsonProperty("subject")
    private String subject;
    @JsonProperty("authorid")
    private Integer authorid;
    @JsonProperty("message")
    private String message;
    @JsonProperty("replies")
    private Integer replies;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("views")
    private Integer views;
    @JsonProperty("likes")
    private Integer likes;
}
