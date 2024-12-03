package com.SIDA.UMKM.service;

import java.util.List;
import java.util.Optional;

import com.SIDA.UMKM.entities.Berita;

public interface BeritaService {

	public List<Berita> findAllBerita();
	
	public Berita saveBerita(Berita berita);
	
	public Optional<Berita> findBeritaById(Long id);
	
	public void deleteBerita(Long id);
	
	public List<Berita> getBeritaTerbaru();
	
	public List<Berita> get3Berita();
}
