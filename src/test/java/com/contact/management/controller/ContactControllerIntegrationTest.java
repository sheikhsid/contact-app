package com.contact.management.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.contact.management.model.ContactDto;
import com.contact.management.service.ContactService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContactControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ContactService contactService;

    private ContactDto contactDto;

    @Before
    public void setUp() {
        // create a test ContactDto object
        contactDto = new ContactDto();
        contactDto.setName("Sheikh");
        contactDto.setCompany("SD");
        contactDto.setNumber(1234567890);

        // save the test ContactDto to the database
        contactDto = contactService.saveContact(contactDto);
    }

    @After
    public void tearDown() {
        // delete the test ContactDto from the database
        contactService.deleteContactById(contactDto.getId());
    }

}