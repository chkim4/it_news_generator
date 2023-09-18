package com.ing.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ing.entity.Member;
import com.ing.repository.MemberRepository;

/**
 * Member 테이블 관련 Service
 */
@Service
public class MemberService {
    
    @Autowired
    private MemberRepository memberRepository;
    
    /**
     * CustomAuthenticationProvider에서 로그인을 통해 인증하려는 사용자의 정보를 DB에서 조회 (기존 인증 X) <br>
     * findByEmail은 Member 타입을 반환하지만, 이 메소드는 UserDetails 타입을 반환하므로 별도 정의
     * 
     * @param email 사용자가 로그인을 통해 입력한 이메일 주소
     * @return email을 DB에서 조회한 결과 (조회한 결과가 있으면 UserDetails 객체를, 없으면 null 반환) 
     */ 
    public UserDetails loadByEmail(String email) {
        Member member = memberRepository.findByEmail(email);
        
        if(member == null) {
            return null;
        }
        
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        UserDetails securityUser = new User(member.getMemberId().toString(), member.getPass(), authorities);
        
        return securityUser;
    } 
    
    /**
     * CustomAuthenticationProvider에서 기존 인증 정보를 통해 인증하려는 사용자의 정보를 DB에서 조회 (기존 인증 O) <br>
     * 
     * @param memberId 기존 인증을 통해 저장한 memberId 값
     * @return memberId를 DB에서 조회한 결과 (조회한 결과가 있으면 UserDetails 객체를, 없으면 null 반환) 
     */ 
    public UserDetails loadById(int memberId) {
        Member member = memberRepository.findByMemberId(memberId);
        
        if(member == null) {
            return null;
        }
        
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        UserDetails securityUser = new User(member.getMemberId().toString(), member.getPass(), authorities);
        
        return securityUser;
    }
    
    /**
     * 회원 가입 기능 처리
     * 
     * @param member Controller에서 전달받은 가입 정보
     * @return 회원 가입 성공 여부를 나타내는 Boolean 값
     */ 
    public Boolean register(Member member) {
        
        return memberRepository.save(member) != null;
    }

    /**
     * 주어진 email 값을 이용하여 사용자 조회
     * 
     * @param email Controller에서 전달 받은 이메일 주소
     * @return email을 DB에서 조회한 결과 
     */ 
    public Member findByEmail(String email) {
        Member member = memberRepository.findByEmail(email); 
        
        return member;    
    } 
    
    
    public Member findByMemberId(int memberId) {
        return memberRepository.findByMemberId(memberId);
    }
   
}
