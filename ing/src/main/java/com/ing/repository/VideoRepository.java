package com.ing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ing.entity.Video;

/**
 * Member 테이블 관련 JPaRepository
 */
@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {
    @Query(value = "SELECT location " + 
            "FROM video " + 
            "WHERE TRUNC(created_at) = to_date(:requestDate, 'YYYY-MM-DD') ", nativeQuery = true )
    public String findLocationByCreatedAt(@Param(value = "requestDate") String date);

}