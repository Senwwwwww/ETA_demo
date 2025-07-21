package com.example.EAT_demo.service;

import com.example.EAT_demo.dto.FeedbackDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface FeedbackService {
    long addNewFeedbackInfo(FeedbackDTO feedbackDTO, Long userId);

    @Transactional
    FeedbackDTO updateFeedbackStatusById(long id, FeedbackDTO feedbackDTO);

    List<FeedbackDTO> getAllFeedbackInfo();

    List<FeedbackDTO> getAllUserFeedbackInfo(Long userId);

    long deleteFeedbackInfo(Long feedbackId);
}
