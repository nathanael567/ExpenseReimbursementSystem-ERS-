package com.reimbursement.model;

import java.sql.Timestamp;

public class Ticket {
    private int ticketid;
    private double amount;
    private Timestamp submitted;
    private Timestamp resolved;
    private String description;

    private int author;
    private int resolver;
    private int statusid;
    private int typeid;

    public Ticket() {
    }

    public Ticket(int id, double amount, Timestamp submitted, Timestamp resolved, // SQL constructor
            String description, int author, int resolver, int statusid, int typeid) {

        this.ticketid = id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;

        this.author = author;
        this.resolver = resolver;
        this.statusid = statusid;
        this.typeid = typeid;
    }

    public int getTicketid() {
        return ticketid;
    }

    public void setTicketid(int ticketid) {
        this.ticketid = ticketid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Timestamp submitted) {
        this.submitted = submitted;
    }

    public Timestamp getResolved() {
        return resolved;
    }

    public void setResolved(Timestamp resolved) {
        this.resolved = resolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public int getResolver() {
        return resolver;
    }

    public void setResolver(int resolver) {
        this.resolver = resolver;
    }

    public int getStatusid() {
        return statusid;
    }

    public void setStatusid(int statusid) {
        this.statusid = statusid;
    }

    public int gettypeid() {
        return typeid;
    }

    public void settypeid(int typeid) {
        this.typeid = typeid;
    }
    

}