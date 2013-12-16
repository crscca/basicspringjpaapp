package com.samples.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

@Entity
//@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@DynamicUpdate(true)
@Table( name = "UserSimplified" )
public class UserSimplified implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	public UserSimplified() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Column(unique=true, nullable=false)
	private String loginId;
	@Column(nullable=false)
	private String password;
	@Column( nullable=false)
    private String title;
	//@Column( nullable=false)
   // private char gender;
	@Column( nullable=false)
    private String firstName;
	@Column( nullable=false)
    private String lastName;
    private String emailId;
    private String phone;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=false)
    private Date birthday;
    
    @OneToOne(cascade=CascadeType.ALL)
    private Address address;
    
    
    @OneToMany(cascade=CascadeType.ALL)
    private Set<Additional> additionals=new HashSet<Additional>();
   
	public void setAdditionals(Set<Additional> additionals) {
		this.additionals = additionals;
	}

	public Set<Additional> getAdditionals() {
		return additionals;
	}
	
	public void addAddional(Additional additional)
	{
		additionals.add(additional);
	}
	
	public void removeAddional(Additional additional)
	{
		additionals.remove(additional);
	}
    
    public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	/*public char getGender() {
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
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public UserSimplified(String loginId, String password, String title,
			String firstName, String lastName, String emailId, String phone,
			Date birthday) {
		super();
		this.loginId = loginId;
		this.password = password;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.phone = phone;
		this.birthday = birthday;
	}

}
