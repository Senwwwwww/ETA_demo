package com.example.EAT_demo.service;

import com.example.EAT_demo.dto.MessageDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MessageService {
    long addNewMessageInfo(MessageDTO messageDTO, Long userId);

    @Transactional
    MessageDTO updateMessageStatusById(MessageDTO messageDTO);

    List<MessageDTO> getAllUserMessageInfo(Long userId);

    long deleteMessageInfo(Long messageId);
}
