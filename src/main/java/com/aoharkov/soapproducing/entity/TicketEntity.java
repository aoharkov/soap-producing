package com.aoharkov.soapproducing.entity;

public class TicketEntity {
    private Long id;
    private String titleOfEvent;
    private String fullNameOfAttendee;
    private Integer row;
    private Integer seat;

    public TicketEntity(Long id, String titleOfEvent, String fullNameOfAttendee, Integer row, Integer seat) {
        this.id = id;
        this.titleOfEvent = titleOfEvent;
        this.fullNameOfAttendee = fullNameOfAttendee;
        this.row = row;
        this.seat = seat;
    }

    public TicketEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleOfEvent() {
        return titleOfEvent;
    }

    public void setTitleOfEvent(String titleOfEvent) {
        this.titleOfEvent = titleOfEvent;
    }

    public String getFullNameOfAttendee() {
        return fullNameOfAttendee;
    }

    public void setFullNameOfAttendee(String fullNameOfAttendee) {
        this.fullNameOfAttendee = fullNameOfAttendee;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }
}
