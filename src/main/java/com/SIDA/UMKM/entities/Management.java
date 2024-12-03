package com.SIDA.UMKM.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_management")
public class Management {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nama")
	private String nama;
	
	@Column(name = "divisi")
	private String divisi;
	
	@Column(name = "no_hp")
	private String noHp;
	
	@Column(name = "email")
	private String email;
}
