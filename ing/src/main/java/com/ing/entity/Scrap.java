package com.ing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@Entity(name="scrap")
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scrapSEQ")
    @SequenceGenerator(sequenceName = "SCRAP_SEQ", name = "scrapSEQ", allocationSize = 1)
    @Column(name = "scrap_id")
    private Integer scrapId; 
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
