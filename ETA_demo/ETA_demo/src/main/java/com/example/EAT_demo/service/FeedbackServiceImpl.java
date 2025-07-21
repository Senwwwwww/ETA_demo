package com.example.EAT_demo.service;

import com.example.EAT_demo.converter.FeedbackConverter;
import com.example.EAT_demo.dao.FeedbackRepository;
import com.example.EAT_demo.dao.UserRepository;
import com.example.EAT_demo.dto.FeedbackDTO;
import com.example.EAT_demo.pojo.Feedback;
import com.example.EAT_demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@SuppressWarnings("all")
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public long addNewFeedbackInfo(FeedbackDTO feedbackDTO, Long userId) {
        User user  =userRepository.findByUserId(userId);
        Feedback feedback = FeedbackConverter.convertFeedback(feedbackDTO);
        feedback.setUser(user);
        feedbackRepository.save(feedback);
        return feedback.getFeedbackId();
    }

    @Transactional
    @Override
    public FeedbackDTO updateFeedbackStatusById(long id, FeedbackDTO feedbackDTO) {
        Feedback feedback = feedbackRepository.findById(feedbackDTO.getFeedbackId()).get();
        feedback.setFeedbackStatus(feedbackDTO.getFeedbackStatus());
        feedbackRepository.save(feedback);
        return FeedbackConverter.convertFeedback(feedback);
    }

    @Override
    public List<FeedbackDTO> getAllFeedbackInfo() {
        List<Feedback> feedbacks = feedbackRepository.findAll();
        return feedbacks.stream()
                .map(FeedbackConverter::convertFeedback)
                .collect(Collectors.toList());
    }

    @Override
    public List<FeedbackDTO> getAllUserFeedbackInfo(Long userId) {
        User user = userRepository.findByUserId(userId);
        List<Feedback> feedbacks = feedbackRepository.findAllByUser(user);
        return feedbacks.stream()
                .map(FeedbackConverter::convertFeedback)
                .collect(Collectors.toList());
    }

    @Override
    public long deleteFeedbackInfo(Long feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId).get();
        if (feedback != null) {
            feedbackRepository.delete(feedback);
        }
        return feedbackId;
    }

}
