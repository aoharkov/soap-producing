package com.aoharkov.soapproducing.endpoint;

import com.aoharkov.soapproducing.GetTicketRequest;
import com.aoharkov.soapproducing.GetTicketResponse;
import com.aoharkov.soapproducing.ObjectFactory;
import com.aoharkov.soapproducing.SaveTicketRequest;
import com.aoharkov.soapproducing.SaveTicketResponse;
import com.aoharkov.soapproducing.Ticket;
import com.aoharkov.soapproducing.entity.TicketEntity;
import com.aoharkov.soapproducing.mapper.Mapper;
import com.aoharkov.soapproducing.repository.TicketRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketEndpointTest {
    private static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();
    private static final Long TICKET_ID = 1L;
    private static final String TITLE_OF_EVENT = "The Night of the AD Eaters";
    private static final String FULL_NAME_OF_ATTENDEE = "John Doe";
    private static final Integer ROW = 3;
    private static final Integer SEAT = 11;
    private static final TicketEntity TICKET_ENTITY =
            new TicketEntity(TICKET_ID, TITLE_OF_EVENT, FULL_NAME_OF_ATTENDEE, ROW, SEAT);
    private static final Ticket TICKET_DTO = new Ticket();

    static {
        TICKET_DTO.setId(TICKET_ID);
        TICKET_DTO.setTitleOfEvent(TITLE_OF_EVENT);
        TICKET_DTO.setFullNameOfAttendee(FULL_NAME_OF_ATTENDEE);
        TICKET_DTO.setRow(ROW);
        TICKET_DTO.setSeat(SEAT);
    }

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private Mapper<TicketEntity, Ticket> ticketMapper;

    @InjectMocks
    private TicketEndpoint ticketEndpoint;

    @AfterEach
    void resetMocks() {
        Mockito.reset(ticketRepository, ticketMapper);
    }

    @Test
    void getTicketShouldReturnTicket() {
        when(ticketRepository.findById(TICKET_ID)).thenReturn(Optional.of(TICKET_ENTITY));
        when(ticketMapper.mapEntityToDto(TICKET_ENTITY)).thenReturn(TICKET_DTO);

        GetTicketRequest getTicketRequest = OBJECT_FACTORY.createGetTicketRequest();
        getTicketRequest.setId(TICKET_ID);
        GetTicketResponse actual = ticketEndpoint.getTicket(getTicketRequest);

        assertEquals(TICKET_ID, actual.getTicket().getId());
        assertEquals(TITLE_OF_EVENT, actual.getTicket().getTitleOfEvent());
        assertEquals(FULL_NAME_OF_ATTENDEE, actual.getTicket().getFullNameOfAttendee());
        assertEquals(ROW, actual.getTicket().getRow());
        assertEquals(SEAT, actual.getTicket().getSeat());
    }

    @Test
    void getTicketShouldThrowNoSuchElementException() {
        when(ticketRepository.findById(any())).thenReturn(Optional.empty());

        GetTicketRequest getTicketRequest = OBJECT_FACTORY.createGetTicketRequest();
        getTicketRequest.setId(TICKET_ID + 1);

        assertThrows(NoSuchElementException.class, () -> ticketEndpoint.getTicket(getTicketRequest));
    }

    @Test
    void saveTicket() {
        when(ticketMapper.mapDtoToEntity(TICKET_DTO)).thenReturn(TICKET_ENTITY);
        when(ticketRepository.save(TICKET_ENTITY)).thenReturn(TICKET_ID);

        SaveTicketRequest saveTicketRequest = OBJECT_FACTORY.createSaveTicketRequest();
        saveTicketRequest.setTicket(TICKET_DTO);
        SaveTicketResponse actual = ticketEndpoint.saveTicket(saveTicketRequest);

        assertEquals(TICKET_ID, actual.getId());
    }
}
