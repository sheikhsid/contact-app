package com.contact.management.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contact.management.entity.ContactEntity;
import com.contact.management.exception.ContactNotFoundException;
import com.contact.management.model.ContactDto;
import com.contact.management.repository.ContactRepository;
import com.contact.management.util.ContactConverter;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;
    
    @Autowired
    private ContactConverter contactConverter;

    public ContactDto saveContact(ContactDto contactDTO) {
        ContactEntity contactEntity = contactConverter.convertToEntity(contactDTO);
        ContactEntity savedEntity = contactRepository.save(contactEntity);
        return contactConverter.convertToDTO(savedEntity);
    }

}