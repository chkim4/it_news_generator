package com.ing.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

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
@ToString(exclude={"scrap"})
@Entity(name="article")
public class Article {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "articleSEQ")
    @SequenceGenerator(sequenceName = "ARTICLE_SEQ", name = "articleSEQ", allocationSize = 1)
    @Column(name = "article_id")
    private Integer articleId; 
    private String summary; 
    private String url; 
    private Integer ord;
    private String site;
    
    @Column(name = "created_at")
    private LocalDate createdAt; 
    
    @OneToMany(mappedBy="article", fetch = FetchType.LAZY)
    private List<Scrap> scrap;

}
