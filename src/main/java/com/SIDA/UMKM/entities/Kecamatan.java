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
@Table(name = "kecamatan")
public class Kecamatan {

	@Id
	@Column(name = "kecamatan_id")
	private Long kecamatanId;
	
	@Column(name = "nama_kecamatan")
	private String namaKecamatan;
	
	@ManyToOne
	@JoinColumn(name = "kota_id")
	private Kota kotaId;
}
