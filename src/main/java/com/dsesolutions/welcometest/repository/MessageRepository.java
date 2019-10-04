package com.dsesolutions.welcometest.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsesolutions.welcometest.model.Message;

/**
 * The repository layer of the Message entity
 * 
 * @author Abhishek Shah
 *
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {
}
