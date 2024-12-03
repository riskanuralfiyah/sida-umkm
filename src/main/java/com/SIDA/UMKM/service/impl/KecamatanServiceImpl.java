package com.SIDA.UMKM.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SIDA.UMKM.entities.Kecamatan;
import com.SIDA.UMKM.entities.Kota;
import com.SIDA.UMKM.repository.KecamatanRepository;
import com.SIDA.UMKM.service.KecamatanService;

@Service
public class KecamatanServiceImpl implements KecamatanService{
	
	@Autowired
	private KecamatanRepository kecamatanRepository;

	@Override
	public List<Kecamatan> getKecamatanByKota(Kota kotaId) {
		// TODO Auto-generated method stub
		return kecamatanRepository.findByKotaId(kotaId);
	}

	@Override
	public Optional<Kecamatan> findKecamatanById(Long id) {
		// TODO Auto-generated method stub
		return kecamatanRepository.findById(id);
	}

}
