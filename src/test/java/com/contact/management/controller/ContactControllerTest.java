package com.contact.management.controller;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import com.contact.management.model.ContactDto;
import com.contact.management.service.ContactService;

@RunWith(MockitoJUnitRunner.class)
public class ContactControllerTest {

	@Mock
	private ContactService contactService;

	@InjectMocks
	private ContactController contactController;

	private MockMvc mockMvc;

	private ObjectMapper objectMapper;

	private ContactDto testContact;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(contactController).build();
		objectMapper = new ObjectMapper();
		testContact = new ContactDto();
		testContact.setId(1);
		testContact.setName("Sheikh Saad");
		testContact.setCompany("sheikh@example.com");
		testContact.setNumber(123456789);
	}

	@Test
	public void testSaveContact() throws Exception {
		Mockito.when(contactService.saveContact(Mockito.any(ContactDto.class))).thenReturn(testContact);
		String requestBody = objectMapper.writeValueAsString(testContact);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/contacts").contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)).andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(testContact.getId()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(testContact.getName()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.company").value(testContact.getCompany()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.number").value(testContact.getNumber()));
		Mockito.verify(contactService, Mockito.times(1)).saveContact(Mockito.any(ContactDto.class));
	}

	@Test
	public void testGetContactById() throws Exception {
		Mockito.when(contactService.getContactById(Mockito.anyInt())).thenReturn(testContact);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/contacts/{id}", testContact.getId()))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(testContact.getId()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(testContact.getName()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.company").value(testContact.getCompany()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.number").value(testContact.getNumber()));
		Mockito.verify(contactService, Mockito.times(1)).getContactById(Mockito.anyInt());
	}

}
