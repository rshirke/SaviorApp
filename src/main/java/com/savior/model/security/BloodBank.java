package com.savior.model.security;



import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "BLOODBANK")
public class BloodBank {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	Long id;
	
	@Column(name = "BLOODBANKID")
	Long bloodBankId;
	
	@Column(name = "BLOODBANKNAME")
	String bloodBankName;
	
	@Column(name = "GEOX")
	double geoX;
	
	@Column(name = "GEOY")
	double geoY;
	
	@Column(name = "APLUSGROUPQTY")
	double aPlusGroupQty;
	
	@Column(name = "AMINGROUPQTY")
	double aMinGroupQty;
	
	@Column(name = "BPLUSGROUPQTY")
	double bPlusGroupQty;
	
	@Column(name = "BMINGROUPQTY")
	double bMinGroupQty;
	
	@Column(name = "ABPLUSGROUPQTY")
	double abPlusGroupQty;
	
	@Column(name = "ABMINGROUPQTY")
	double abMinGroupQty;
	
	@Column(name = "OPLUSGROUPQTY")
	double oPlusGroupQty;
	
	@Column(name = "OMINGROUPQTY")
	double oMinGroupQty;
	
	@OneToMany(mappedBy = "bloodBank", cascade = CascadeType.ALL)
	private Set<Transaction> transactions;
	
	public Set<Transaction> getTransaction() {
		return transactions;
	}

	public void setTransaction(Set<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBloodBankId() {
		return bloodBankId;
	}

	public void setBloodBankId(Long bloodBankId) {
		this.bloodBankId = bloodBankId;
	}

	public String getBloodBankName() {
		return bloodBankName;
	}

	public void setBloodBankName(String bloodBankName) {
		this.bloodBankName = bloodBankName;
	}

	public double getGeoX() {
		return geoX;
	}

	public void setGeoX(double geoX) {
		this.geoX = geoX;
	}

	public double getGeoY() {
		return geoY;
	}

	public void setGeoY(double geoY) {
		this.geoY = geoY;
	}

	public double getaPlusGroupQty() {
		return aPlusGroupQty;
	}

	public void setaPlusGroupQty(double aPlusGroupQty) {
		this.aPlusGroupQty = aPlusGroupQty;
	}

	public double getaMinGroupQty() {
		return aMinGroupQty;
	}

	public void setaMinGroupQty(double aMinGroupQty) {
		this.aMinGroupQty = aMinGroupQty;
	}

	public double getbPlusGroupQty() {
		return bPlusGroupQty;
	}

	public void setbPlusGroupQty(double bPlusGroupQty) {
		this.bPlusGroupQty = bPlusGroupQty;
	}

	public double getbMinGroupQty() {
		return bMinGroupQty;
	}

	public void setbMinGroupQty(double bMinGroupQty) {
		this.bMinGroupQty = bMinGroupQty;
	}

	public double getAbPlusGroupQty() {
		return abPlusGroupQty;
	}

	public void setAbPlusGroupQty(double abPlusGroupQty) {
		this.abPlusGroupQty = abPlusGroupQty;
	}

	public double getAbMinGroupQty() {
		return abMinGroupQty;
	}

	public void setAbMinGroupQty(double abMinGroupQty) {
		this.abMinGroupQty = abMinGroupQty;
	}

	public double getoPlusGroupQty() {
		return oPlusGroupQty;
	}

	public void setoPlusGroupQty(double oPlusGroupQty) {
		this.oPlusGroupQty = oPlusGroupQty;
	}

	public double getoMinGroupQty() {
		return oMinGroupQty;
	}

	public void setoMinGroupQty(double oMinGroupQty) {
		this.oMinGroupQty = oMinGroupQty;
	}

}
