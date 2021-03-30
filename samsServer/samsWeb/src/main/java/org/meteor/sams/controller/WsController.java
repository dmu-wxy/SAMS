package org.meteor.sams.controller;

import org.meteor.sams.model.ChatMsg;
import org.meteor.sams.model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;

@Controller
public class WsController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/ws/chat")
    @CrossOrigin(originPatterns = "*")
    public void handleMsg(Authentication authentication, ChatMsg chatMsg){
        Manager manager = (Manager) authentication.getPrincipal();
        chatMsg.setFrom(manager.getUsername());
        chatMsg.setFromNickName(manager.getMname());
        chatMsg.setDate(new Date());
        simpMessagingTemplate.convertAndSendToUser(chatMsg.getTo(),"/queue/chat",chatMsg);
    }

}
