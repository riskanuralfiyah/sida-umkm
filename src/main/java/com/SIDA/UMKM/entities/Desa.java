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
@Table(name = "desa")
public class Desa {

	@Id
	@Column(name = "desa_id")
	private Long desaId;
	
	@Column(name = "nama_desa")
	private String namaDesa;
	
	@ManyToOne
	@JoinColumn(name = "kecamatan_id")
	private Kecamatan kecamatanId;
}
