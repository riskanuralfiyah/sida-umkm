package com.SIDA.UMKM.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "kota")
public class Kota {

	@Id
	@Column(name = "kota_id")
	private Long kotaId;
	
	@Column(name = "nama_kota")
	private String namaKota;
	
	@ManyToOne
	@JoinColumn(name = "provinsi_id")
	private Provinsi provinsiId;
	
}
