package com.SIDA.UMKM.dto;

import jakarta.validation.constraints.NotBlank;

public class AddJabatanDTO {
	
	@NotBlank(message = "Judul Harus Diisi")
	private String namaJabatan;
	
	@NotBlank(message = "Deskripsi Harus Diisi")
	private String deskripsi;
	
	@NotBlank(message = "Foto Harus Diisi")
	private String pictJabatan;

}
