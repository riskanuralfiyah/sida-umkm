package com.SIDA.UMKM.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SIDA.UMKM.entities.Desa;
import com.SIDA.UMKM.entities.Kecamatan;
import com.SIDA.UMKM.repository.DesaRepository;
import com.SIDA.UMKM.service.DesaService;

@Service
public class DesaServiceImpl implements DesaService{
	
	@Autowired
	private DesaRepository desaRepository;

	@Override
	public List<Desa> getDesaByKecamatan(Kecamatan kecamatanId) {
		// TODO Auto-generated method stub
		return desaRepository.findByKecamatanId(kecamatanId);
	}

	@Override
	public Optional<Desa> findDesaById(Long id) {
		// TODO Auto-generated method stub
		return desaRepository.findById(id);
	}

}
