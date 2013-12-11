package com.samples.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

@Entity
//@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@DynamicUpdate(true)
@Table( name = "User" )
public class User implements IAddressHolder {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(unique=true, nullable=false)
	private String loginId;
	@Column(nullable=false)
	private String password;
	@Column( nullable=false)
    private String title;
	@Column( nullable=false)
    private char gender;
	@Column( nullable=false)
    private String firstName;
	@Column( nullable=false)
    private String lastName;
    private String emailId;
    private String phone;
    @OneToOne(cascade=CascadeType.ALL)
    private Address address;
    @OneToMany(cascade=CascadeType.ALL)
    private Set<Contact> contacts=new HashSet<Contact>();
    
    @OneToMany(cascade=CascadeType.ALL)
    private Set<Additional> additionals=new HashSet<Additional>();
   
	public Set<Additional> getAdditionals() {
		return additionals;
	}



	public void setAdditionals(Set<Additional> additionals) {
		this.additionals = additionals;
	}



	@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=false)
    private Date birthday;

	public User() {
		super();
		
	}

	

	public User(String loginId, String password, String title, char gender, String firstName,
			String lastName, String emailId, String phone, Date birthday) {
		super();
		this.loginId = loginId;
		this.password=password;
		this.title = title;
		this.gender = gender;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.phone=phone;
		this.birthday = birthday;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getLoginId() {
		return loginId;
	}



	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public char getGender() {
		return gender;
	}



	public void setGender(char gender) {
		this.gender = gender;
	}



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



	public Date getBirthday() {
		return birthday;
	}



	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}



	public Address getAddress() {
		return address;
	}



	public void setAddress(Address address) {
		this.address = address;
	}



	public Set<Contact> getContacts() {
		return contacts;
	}



	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}
	

}
