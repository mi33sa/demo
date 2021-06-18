package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.demo.validator.UniqueLogin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "siteuser")
public class SiteUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	@Size(min=2,max=20)
	@UniqueLogin
	@Column(name = "username")
	private String username;
	
	@Size(min=4,max=255)
	@Column(name = "password")
	private String password;
	
	@NotBlank
	@Email
	@Column(name = "email")
	private String email;
	
	@Column(name = "gender")
	private int gender;
	
	@Column(name = "admin")
	private boolean admin;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "active")
	private boolean active = true;

}
