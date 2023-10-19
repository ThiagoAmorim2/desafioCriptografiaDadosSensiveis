package com.desafios.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_limites")
@Getter
@Setter
public class Limite {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Long id;
	
	@Column(name = "userDocument", unique = true)
	private String userDocument;

	@Column(name = "creditCardToken", unique = true)
	private String creditCardToken;

	@Column(name = "value")
	private Long value;
}
