package com.aoharkov.soapproducing.repository;

import com.aoharkov.soapproducing.entity.TicketEntity;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class TicketRepositoryInMemoryImpl implements TicketRepository {
    private static final String TITLE_OF_EVENT = "The Night of the AD Eaters";
    private final List<TicketEntity> ticketEntities;

    public TicketRepositoryInMemoryImpl() {
        ticketEntities = Arrays.asList(
                new TicketEntity(1L, TITLE_OF_EVENT, "John Doe", 3, 11),
                new TicketEntity(2L, TITLE_OF_EVENT, "Jane Doe", 3, 12),
                new TicketEntity(3L, TITLE_OF_EVENT, "Mr. Smith", 4, 21),
                new TicketEntity(4L, TITLE_OF_EVENT, "Mrs. Smith", 4, 22));
    }

    @Override
    public Optional<TicketEntity> findById(Long id) {
        return ticketEntities.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    @Override
    public Long save(TicketEntity ticketEntity) {
        if (!findById(ticketEntity.getId()).isPresent()) {
            ticketEntities.add(ticketEntity);
        }
        return ticketEntity.getId();
    }
}
