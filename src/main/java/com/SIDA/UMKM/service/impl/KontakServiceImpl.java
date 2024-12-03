package com.SIDA.UMKM.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SIDA.UMKM.entities.Kontak;
import com.SIDA.UMKM.repository.KontakRepository;
import com.SIDA.UMKM.service.KontakService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class KontakServiceImpl implements KontakService{
	
	@Autowired
	private KontakRepository kontakRepository;

	@Override
	public List<Kontak> findAllDataKontak() {
		// TODO Auto-generated method stub
		return kontakRepository.findAll();
	}

	@Override
	public Kontak saveKontak(Kontak kontak) {
		// TODO Auto-generated method stub
		return kontakRepository.save(kontak);
	}

	@Override
	public void deleteKontak(Long id) {
		// TODO Auto-generated method stub
		kontakRepository.deleteById(id);
	}

}
