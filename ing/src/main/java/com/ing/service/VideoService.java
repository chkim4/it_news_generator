package com.ing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ing.repository.VideoRepository;

/**
 * Video 테이블 관련 Service
 */
@Service
public class VideoService {
    
    @Autowired
    private VideoRepository videoRepository;
    
     
    public String findLocationByCreatedAt(String date) {
        
        return videoRepository.findLocationByCreatedAt(date);
    }  
}
