package com.SIDA.UMKM.entities;

import java.math.BigDecimal;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "t_umkm")
public class UMKM {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nik")
	@NotBlank(message = "NIK tidak boleh kosong")
	@Pattern(regexp = "\\d{16}", message = "NIK harus terdiri dari 16 angka")
	private String nik;

	@Column(name = "nama_pemilik")
	@NotBlank(message = "Nama pemilik tidak boleh kosong")
	@Pattern(regexp = "^[a-zA-Z\\s.,]*$", message = "Nama hanya boleh terdiri dari huruf")
	private String namaPemilik;

	@Column(name = "produk")
	@Pattern(regexp = "^[a-zA-Z0-9\\s.,]*$", message = "Nama produk hanya boleh terdiri dari huruf dan angka")
	@NotBlank(message = "Produk tidak boleh kosong")
	private String produk;

	@Column(name = "alamat_pemilik")
	@NotBlank(message = "Alamat pemilik tidak boleh kosong")
	private String alamatPemilik;

	@Column(name = "alamat_usaha")
	@NotBlank(message = "Alamat Usaha tidak boleh kosong")
	private String alamatUsaha;
	
	@Column(name = "provinsi")
	private String provinsi;
	
	@Column(name = "kota")
	private String kota;
	
	@Column(name = "kecamatan")
	private String kecamatan;
	
	@Column(name = "desa")
	private String desa;

	@Column(name = "omset_pertahun")
	@NotNull(message = "Omset Pertahun tidak boleh kosong")
	@DecimalMin(value = "0", inclusive = false, message = "Omset per tahun harus lebih besar dari 0")
	private BigDecimal omsetPerTahun;

	@Column(name = "kekayaan_bersih")
	@NotNull(message = "Kekayaan bersih tidak boleh kosong")
	@DecimalMin(value = "0", inclusive = false, message = "Kekayaan bersih harus lebih besar dari 0")
	private BigDecimal kekayaanBersih;

	@Column(name = "jenis_kegiatan_usaha")
	@NotBlank(message = "Jenis kegiatan usaha tidak boleh kosong")
	private String jenisKegiatanUsaha;

	@Column(name = "ijin_usaha")
	@NotBlank(message = "Ijin usaha tidak boleh kosong")
	private String ijinUsaha;

	@Column(name = "permasalahan")
	@NotBlank(message = "Permasalahan tidak boleh kosong")
	private String permasalahan;

	@Column(name = "kluster")
	@NotBlank(message = "Kluster tidak boleh kosong")
	private String kluster;

	@Column(name = "keterangan")
	@NotBlank(message = "Keterangan tidak boleh kosong")
	private String keterangan;

	@Column(name = "logo")
	private String logo;

	@Transient
	public String getPhotosImagePath() {
		if (logo == null || id == null)
			return null;

		return "/assets/images/umkm/" + logo;
	}
}
