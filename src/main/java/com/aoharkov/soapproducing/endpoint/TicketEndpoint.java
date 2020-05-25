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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.NoSuchElementException;
import java.util.Optional;

@Endpoint
public class TicketEndpoint {
    private static final String NAMESPACE_URI = "http://soapproducing.aoharkov.com";
    private static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();

    private final TicketRepository ticketRepository;
    private final Mapper<TicketEntity, Ticket> ticketMapper;

    @Autowired
    public TicketEndpoint(TicketRepository ticketRepository, Mapper<TicketEntity, Ticket> ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTicketRequest")
    @ResponsePayload
    public GetTicketResponse getTicket(@RequestPayload GetTicketRequest request) {
        GetTicketResponse response = OBJECT_FACTORY.createGetTicketResponse();
        Optional<TicketEntity> ticketFromDB = ticketRepository.findById(request.getId());

        if (!ticketFromDB.isPresent()) {
            throw new NoSuchElementException();
        }
        Ticket ticketDto = ticketMapper.mapEntityToDto(ticketFromDB.get());
        response.setTicket(ticketDto);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveTicketRequest")
    @ResponsePayload
    public SaveTicketResponse saveTicket(@RequestPayload SaveTicketRequest request) {
        SaveTicketResponse response = OBJECT_FACTORY.createSaveTicketResponse();
        TicketEntity ticketEntity = ticketMapper.mapDtoToEntity(request.getTicket());
        response.setId(ticketRepository.save(ticketEntity));
        return response;
    }
}
