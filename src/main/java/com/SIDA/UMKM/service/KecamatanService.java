package com.SIDA.UMKM.service;

import java.util.List;
import java.util.Optional;

import com.SIDA.UMKM.entities.Kecamatan;
import com.SIDA.UMKM.entities.Kota;

public interface KecamatanService {

	public List<Kecamatan> getKecamatanByKota(Kota kotaId);
	
	public Optional<Kecamatan> findKecamatanById(Long id);
}
