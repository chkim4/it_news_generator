package com.ing.entity;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Scrap 테이블과의 매핑을 위한 Entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name="scrap")
public class Scrap {
    private Integer ScrapId; 
    private Integer memberId; 
    private Integer ArticleId;  
}
