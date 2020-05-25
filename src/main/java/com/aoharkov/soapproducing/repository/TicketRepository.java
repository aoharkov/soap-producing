package com.aoharkov.soapproducing.repository;

import com.aoharkov.soapproducing.entity.TicketEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository {

    Optional<TicketEntity> findById(Long id);

    Long save(TicketEntity ticketEntity);
}
