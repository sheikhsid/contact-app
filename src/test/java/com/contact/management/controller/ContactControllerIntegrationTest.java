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

    @Test
    public void testSaveContact() {
        // create a new ContactDto object
        ContactDto newContact = new ContactDto();
        newContact.setName("Saad");
        newContact.setCompany("SD");
        newContact.setNumber(1234567890);

        // send a POST request to the "/api/v1/contacts" endpoint with the new ContactDto as the request body
        ResponseEntity<ContactDto> response = restTemplate.postForEntity("/api/v1/contacts", newContact, ContactDto.class);

        // assert that the response status is "201 Created"
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        // assert that the response body is not null and contains the expected data
        ContactDto savedContact = response.getBody();
        assertThat(savedContact).isNotNull();
        assertThat(savedContact.getId()).isNotNull();
        assertThat(savedContact.getName()).isEqualTo(newContact.getName());
        assertThat(savedContact.getCompany()).isEqualTo(newContact.getCompany());
        assertThat(savedContact.getNumber()).isEqualTo(newContact.getNumber());

        // delete the new ContactDto from the database
        contactService.deleteContactById(savedContact.getId());
    }

}