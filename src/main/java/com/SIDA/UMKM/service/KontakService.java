package com.SIDA.UMKM.service;

import java.util.List;

import com.SIDA.UMKM.entities.Kontak;

public interface KontakService {

	public List<Kontak> findAllDataKontak();
	
	public Kontak saveKontak(Kontak kontak);
	
	public void deleteKontak(Long id);
}
