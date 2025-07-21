package com.example.EAT_demo.service;

import com.example.EAT_demo.converter.MessageConverter;
import com.example.EAT_demo.dao.MessageRepository;
import com.example.EAT_demo.dao.UserRepository;
import com.example.EAT_demo.dto.MessageDTO;
import com.example.EAT_demo.pojo.Message;
import com.example.EAT_demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("all")
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public long addNewMessageInfo(MessageDTO messageDTO, Long userId) {
        User user = userRepository.findByUserId(userId);
        Message message = MessageConverter.convertMessage(messageDTO);
        message.setUser(user);
        messageRepository.save(message);
        return message.getMessageId();
    }

    @Transactional
    @Override
    public MessageDTO updateMessageStatusById(MessageDTO messageDTO) {
        Message message = messageRepository.findById(messageDTO.getMessageId()).get();
        message.setMessageStatus(messageDTO.getMessageStatus());
        messageRepository.save(message);
        return MessageConverter.convertMessage(message);
    }


    @Override
    public List<MessageDTO> getAllUserMessageInfo(Long userId) {
        User user = userRepository.findByUserId(userId);
        List<Message> messages = messageRepository.findAllByUser(user);
        return messages.stream()
                .map(MessageConverter::convertMessage)
                .collect(Collectors.toList());
    }

    @Override
    public long deleteMessageInfo(Long messageId) {
        Message message = messageRepository.findById(messageId).get();
        if (message != null) {
            messageRepository.delete(message);
        }
        return messageId;
    }
}
