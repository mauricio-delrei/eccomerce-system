package com.multitone.resources;


import com.multitone.model.Message;
import com.multitone.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageResource {

    private final MessageService service;

    public MessageResource(MessageService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Message>>getAllMessages(){
        List<Message>messages = service.getAllMessages();
        return ResponseEntity.status(HttpStatus.OK).body(messages);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Message>findById(@PathVariable("id")final Long id){
        var entity = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(entity);
    }

    @PostMapping
    public ResponseEntity<Message>saveMessage(@RequestBody final Message message){
        var entity = service.saveMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }
}
