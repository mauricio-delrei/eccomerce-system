package com.multitone.service;

import com.multitone.model.Message;
import com.multitone.repository.MessageRepository;
import com.multitone.service.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private final MessageRepository repository;



    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public List<Message>getAllMessages(){
      return (List<Message>) repository.findAll();
    }

    public Message findById(final Long id){
            Optional<Message> messageId = repository.findById(id);
            return messageId.orElseThrow(() -> new ObjectNotFoundException("Object is not valid!"));
        }

    public void delete(final Long id) {
        findById(id);
            repository.deleteById(id);
    }
    public Message saveMessage(final Message message) {
        return repository.save(message);
    }
}
