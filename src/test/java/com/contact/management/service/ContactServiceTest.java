package com.contact.management.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.contact.management.entity.ContactEntity;
import com.contact.management.exception.ContactNotFoundException;
import com.contact.management.model.ContactDto;
import com.contact.management.repository.ContactRepository;
import com.contact.management.util.ContactConverter;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTest {

	@InjectMocks
	private ContactService contactService;

	@Mock
	private ContactRepository contactRepository;

	@Mock
	private ContactConverter contactConverter;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSaveContact() {
		// Arrange
		ContactDto contactDto = new ContactDto();
		ContactEntity contactEntity = new ContactEntity();
		when(contactConverter.convertToEntity(contactDto)).thenReturn(contactEntity);
		when(contactRepository.save(contactEntity)).thenReturn(contactEntity);
		when(contactConverter.convertToDTO(contactEntity)).thenReturn(contactDto);

		// Act
		ContactDto result = contactService.saveContact(contactDto);

		// Assert
		verify(contactConverter).convertToEntity(contactDto);
		verify(contactRepository).save(contactEntity);
		verify(contactConverter).convertToDTO(contactEntity);
		assertEquals(contactDto, result);
	}

	@Test
	public void testGetContactById() {
		// Arrange
		Integer id = 1;
		ContactEntity contactEntity = new ContactEntity();
		ContactDto contactDto = new ContactDto();
		when(contactRepository.findById(id)).thenReturn(Optional.of(contactEntity));
		when(contactConverter.convertToDTO(contactEntity)).thenReturn(contactDto);

		// Act
		ContactDto result = contactService.getContactById(id);

		// Assert
		verify(contactRepository).findById(id);
		verify(contactConverter).convertToDTO(contactEntity);
		assertEquals(contactDto, result);
	}

	@Test(expected = ContactNotFoundException.class)
	public void testGetContactByIdNotFound() {
		// Arrange
		Integer id = 1;
		when(contactRepository.findById(id)).thenReturn(Optional.empty());

		// Act
		contactService.getContactById(id);
	}

}
