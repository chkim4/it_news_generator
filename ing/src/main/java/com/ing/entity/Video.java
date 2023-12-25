package com.ing.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Video 테이블과의 매핑을 위한 Entity
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name="video")
public class Video {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "videoSEQ")
    @SequenceGenerator(sequenceName = "VIDEO_SEQ", name = "videoSEQ", allocationSize = 1)
    @Column(name = "video_id")
    private Integer videoId; 
    
    private String location; 
    
    @Column(name = "created_at")
    private Date createdAt;
    
}
