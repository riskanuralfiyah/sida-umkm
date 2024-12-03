package com.SIDA.UMKM.service;

import java.util.List;
import java.util.Optional;

import com.SIDA.UMKM.entities.JenisUsaha;


public interface JenisUsahaService {

	public List<JenisUsaha> findAllJenisUsaha();
	
	public JenisUsaha saveJenisUsaha(JenisUsaha jenisUsaha);
	
	public Optional<JenisUsaha> updateJenisUsaha(Long id);
	
	public void deleteJenisUsaha(Long id);
}
