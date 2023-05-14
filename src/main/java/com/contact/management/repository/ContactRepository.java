package com.contact.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contact.management.entity.ContactEntity;

public interface ContactRepository extends JpaRepository<ContactEntity, Integer> {

}
