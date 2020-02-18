package com.ssafy.socket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import com.ssafy.socket.domain.AddressPermission;
import com.ssafy.socket.domain.Message;

@Controller
public class PushController {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	
	@MessageMapping("/push")
	public void push(Message message, SimpMessageHeaderAccessor messageHeaderAccessor) {
		System.out.println("message : " + message);
		String id = message.getReceiver();
		messagingTemplate.convertAndSend( "/topic/push/"+id, message);
	}
	
	@MessageMapping("/addresspush")
	public void addresspush(AddressPermission message, SimpMessageHeaderAccessor messageHeaderAccessor) {
		System.out.println("message : " + message);
		messagingTemplate.convertAndSend( "/topic/push/"+message.getUserid(), message);
	}
	
	
}
