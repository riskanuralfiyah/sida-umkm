package com.SIDA.UMKM.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "jabatan")
public class Jabatan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;  // Menggunakan huruf kecil untuk konsistensi

	@Column(nullable = false, unique = true)
	private String namaJabatan;  // Menggunakan camelCase untuk konsistensi
	
	@Column(name = "deskripsi", columnDefinition = "TEXT", nullable = false)
	@NotBlank(message = "Deskripsi Harus Diisi")
	private String deskripsi;
	
	@Column(name = "pict_jabatan", nullable = true)
	private String pictJabatan;


}
