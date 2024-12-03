package com.SIDA.UMKM.service;

import java.util.List;
import java.util.Optional;

import com.SIDA.UMKM.entities.Kota;
import com.SIDA.UMKM.entities.Provinsi;

public interface KotaService {

	public List<Kota> getkotaByProvinsi(Provinsi provinsiId);
	
	public Optional<Kota> findKotaById(Long id);
}
