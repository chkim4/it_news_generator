package com.ing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ing.entity.Member;

/**
 * Member 테이블 관련 JPaRepository
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    public Member findByMemberId(Integer memberId);
    public Member findByEmail(String email);

}