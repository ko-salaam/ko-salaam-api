package com.kosalaam.api.modules.chat;

import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class ChatController {

    @MessageMapping("ws/chat")
    public void chatGET() {
        log.info("@ChatController, chat GET()");
    }
}
