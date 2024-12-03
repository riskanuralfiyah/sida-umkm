package com.SIDA.UMKM.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SIDA.UMKM.entities.Kota;
import com.SIDA.UMKM.entities.Provinsi;
import com.SIDA.UMKM.repository.KotaRepository;
import com.SIDA.UMKM.service.KotaService;

@Service
public class KotaServiceImpl implements KotaService{
	
	@Autowired
	private KotaRepository kotaRepository;

	@Override
	public List<Kota> getkotaByProvinsi(Provinsi provinsiId) {
		// TODO Auto-generated method stub
		return kotaRepository.findByProvinsiId(provinsiId);
	}

	@Override
	public Optional<Kota> findKotaById(Long id) {
		// TODO Auto-generated method stub
		return kotaRepository.findById(id);
	}

}
