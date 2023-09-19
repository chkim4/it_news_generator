package com.ing.entity;

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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Member 테이블과의 매핑을 위한 Entity
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name="member")
public class Member {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "memberSEQ")
    @SequenceGenerator(sequenceName = "MEMBER_SEQ", name = "memberSEQ", allocationSize = 1)
    @Column(name = "member_id")
    private Integer memberId; 
    private String email; 
    private String pass; 
    private String nickname; 
    private Integer status;
    
    @OneToMany(mappedBy="member", fetch = FetchType.LAZY)
    private List<Scrap> scrap;
}
