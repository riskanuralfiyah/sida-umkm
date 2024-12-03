package com.SIDA.UMKM.service;

import java.util.List;
import java.util.Optional;

import com.SIDA.UMKM.entities.Desa;
import com.SIDA.UMKM.entities.Kecamatan;


public interface DesaService {

	public List<Desa> getDesaByKecamatan(Kecamatan kecamatanId); 
	
	public Optional<Desa> findDesaById(Long id);
}
