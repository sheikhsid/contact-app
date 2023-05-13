package com.contact.management.util;

import org.springframework.stereotype.Component;

import com.contact.management.entity.ContactEntity;
import com.contact.management.model.ContactDto;
@Component
public class ContactConverter {


	public ContactDto convertToDTO(ContactEntity contactEntity) {
		ContactDto contactDTO = new ContactDto();
		contactDTO.setId(contactEntity.getId());
		contactDTO.setName(contactEntity.getName());
		contactDTO.setCompany(contactEntity.getCompany());
		contactDTO.setNumber(contactEntity.getNumber());
		return contactDTO;
	}

	public ContactEntity convertToEntity(ContactDto contactDTO) {
		ContactEntity contactEntity = new ContactEntity();
		contactEntity.setId(contactDTO.getId());
		contactEntity.setName(contactDTO.getName());
		contactEntity.setCompany(contactDTO.getCompany());
		contactEntity.setNumber(contactDTO.getNumber());
		return contactEntity;
	}
}
