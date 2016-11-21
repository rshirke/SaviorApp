package com.savior.model.security;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="TRANSACTIONS")
public class Transaction {

	@Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name="ID")
	private Long id;
	
	@Column(name="BLOODGROUP")
	private String bloodGroup;
	
	@Column(name="Status")
	private String status;
	
	@Column(name="QTY")
	private int quantity;
	
	@Column(name="DATE")
	private Date date;
	
	@ManyToOne
    @JoinColumn(name = "userId")
	private User user;
	
    public User getUser() {
        return user;
    }
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BloodBankId",nullable=false)
	private BloodBank bloodBank;
	
    public BloodBank getBloodBank() {
        return bloodBank;
    }
	
	public void setBloodBank(BloodBank bloodBank) {
		this.bloodBank = bloodBank;
	}
	
	public void setUser(User user) {
		this.user=user;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
