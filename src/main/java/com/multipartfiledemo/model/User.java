package com.multipartfiledemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	
	//@JsonIgnore
	@Lob
	private byte[] resim;
	
	@Lob
	private String stringResim;

	public User(String name, String email, byte[] resim, String stringResim) {
		super();
		this.name = name;
		this.email = email;
		this.resim = resim;
		this.stringResim = stringResim;
	}
	
	
}
