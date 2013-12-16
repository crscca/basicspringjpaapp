package com.samples.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table( name = "Contact" )
public class Contact implements IAddressHolder {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column( nullable=false)
    private String title;
//	@Column( nullable=false)
//    private char gender;
	@Column( nullable=false)
    private String firstName;
	@Column( nullable=false)
    private String lastName;
    private String emailId;
    private String phone;
    @OneToOne(cascade=CascadeType.ALL)
    private Address address;
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Contact(String title,/* char gender,*/ String firstName,
			String lastName, String emailId, String phone) {
		super();
		this.title = title;
		//this.gender = gender;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.phone = phone;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
/*	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}*/
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/* (non-Javadoc)
	 * @see org.hibernate.tutorial.entities.IAddressHolder#getAddress()
	 */
	@Override
	public Address getAddress() {
		return address;
	}
	/* (non-Javadoc)
	 * @see org.hibernate.tutorial.entities.IAddressHolder#setAddress(org.hibernate.tutorial.entities.Address)
	 */
	@Override
	public void setAddress(Address address) {
		this.address = address;
	}
   
	


}
