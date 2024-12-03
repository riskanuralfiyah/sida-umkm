package com.SIDA.UMKM.service;

import java.util.List;
import java.util.Optional;

import com.SIDA.UMKM.entities.Provinsi;

public interface ProvinsiService {
	
	public List<Provinsi> getProvinsiList();

	public Optional<Provinsi> findProvinsiById(Long id);
}
