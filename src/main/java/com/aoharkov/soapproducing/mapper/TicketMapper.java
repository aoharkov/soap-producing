package com.aoharkov.soapproducing.mapper;

import com.aoharkov.soapproducing.Ticket;
import com.aoharkov.soapproducing.entity.TicketEntity;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper implements Mapper<TicketEntity, Ticket> {

    @Override
    public Ticket mapEntityToDto(TicketEntity ticketEntity) {
        if (ticketEntity == null) {
            return null;
        }
        Ticket ticketDto = new Ticket();
        ticketDto.setId(ticketEntity.getId());
        ticketDto.setTitleOfEvent(ticketEntity.getTitleOfEvent());
        ticketDto.setFullNameOfAttendee(ticketEntity.getFullNameOfAttendee());
        ticketDto.setRow(ticketEntity.getRow());
        ticketDto.setSeat(ticketEntity.getSeat());
        return ticketDto;
    }

    @Override
    public TicketEntity mapDtoToEntity(Ticket ticketDto) {
        if (ticketDto == null) {
            return null;
        }
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setId(ticketDto.getId());
        ticketEntity.setTitleOfEvent(ticketDto.getTitleOfEvent());
        ticketEntity.setFullNameOfAttendee(ticketDto.getFullNameOfAttendee());
        ticketEntity.setRow(ticketDto.getRow());
        ticketEntity.setSeat(ticketDto.getSeat());
        return ticketEntity;
    }
}
