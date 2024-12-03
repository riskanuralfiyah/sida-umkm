package com.SIDA.UMKM.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.SIDA.UMKM.entities.Provinsi;
import com.SIDA.UMKM.repository.ProvinsiRepository;
import com.SIDA.UMKM.service.ProvinsiService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProvinsiServiceImpl implements ProvinsiService{

	private final ProvinsiRepository provinsiRepository;
	
	@Override
	public List<Provinsi> getProvinsiList() {
		// TODO Auto-generated method stub
		return provinsiRepository.findAll();
	}

	@Override
	public Optional<Provinsi> findProvinsiById(Long id) {
		// TODO Auto-generated method stub
		return provinsiRepository.findById(id);
	}

}
