package com.ing.entity;

import java.sql.Date;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Article 테이블과의 매핑을 위한 Entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name="article")
public class Article {
    private Integer articleId; 
    private String summary; 
    private String url; 
    private Integer ord;
    private String site;
    private Date createdAt;
}
