package com.contact.management.controller;

import com.contact.management.model.ContactDto;
import com.contact.management.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<ContactDto> saveContact(@RequestBody ContactDto contactDto) {
        ContactDto savedContact = contactService.saveContact(contactDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedContact);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDto> getContactById(@PathVariable Integer id) {
        ContactDto contact = contactService.getContactById(id);
        return ResponseEntity.ok(contact);
    }

    @GetMapping
    public ResponseEntity<List<ContactDto>> getAllContacts() {
        List<ContactDto> contacts = contactService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }

}
