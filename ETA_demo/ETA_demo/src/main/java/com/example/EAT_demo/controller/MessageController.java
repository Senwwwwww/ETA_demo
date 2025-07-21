package com.example.EAT_demo.controller;

import com.example.EAT_demo.Response;
import com.example.EAT_demo.dto.MessageDTO;
import com.example.EAT_demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("all")
@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/user/addNewMessageInfo/{userId}")
    @CrossOrigin
    public Response<Long> addNewMessageInfo(@PathVariable Long userId, @RequestBody MessageDTO messageDTO) {
        return Response.newSuccess(messageService.addNewMessageInfo(messageDTO, userId));
    }

    @PutMapping("/user/updateMessageStatus")
    @CrossOrigin
    public Response<MessageDTO> updateMessageStatusById(@RequestBody MessageDTO messageDTO) {
        return Response.newSuccess(messageService.updateMessageStatusById(messageDTO));
    }

    @GetMapping("/user/getAllUserMessageInfo/{userId}")
    @CrossOrigin
    public Response<List<MessageDTO>> getAllUserMessageInfo(@PathVariable Long userId) {
        return Response.newSuccess(messageService.getAllUserMessageInfo(userId));
    }

    @DeleteMapping("/user/admin/deleteMessageInfo")
    @CrossOrigin
    public Response<Long> deleteMessageInfo(@RequestParam Long messageId) {
        return Response.newSuccess(messageService.deleteMessageInfo(messageId));
    }
}
