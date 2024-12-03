package com.SIDA.UMKM.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "provinsi")
public class Provinsi {

	@Id
	@Column(name = "provinsi_id")
	private Long provinsiId;
	
	@Column(name = "nama_provinsi")
	private String namaProvinsi;
	
	@Column(name = "lokasi_id")
	private Long lokasiId;
	
	@Column(name = "status")
	private Long status;
}
