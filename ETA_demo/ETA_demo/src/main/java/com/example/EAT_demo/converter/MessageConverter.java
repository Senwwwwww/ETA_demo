package com.example.EAT_demo.converter;

import com.example.EAT_demo.dto.MessageDTO;
import com.example.EAT_demo.pojo.Message;

public class MessageConverter {

    public static MessageDTO convertMessage(Message message) {
        MessageDTO messageDTO = new MessageDTO();

        messageDTO.setMessageId(message.getMessageId());
        messageDTO.setUser(message.getUser()); // 直接设置 User 对象
        messageDTO.setMessageTime(message.getMessageTime());
        messageDTO.setMessageContent(message.getMessageContent());
        messageDTO.setMessageStatus(message.getMessageStatus());

        return messageDTO;
    }

    public static Message convertMessage(MessageDTO messageDTO) {
        Message message = new Message();

        message.setMessageContent(messageDTO.getMessageContent());
        return message;
    }
}

