package com.SIDA.UMKM.service;

import java.util.List;
import java.util.Optional;

import com.SIDA.UMKM.entities.Jabatan;

public interface JabatanService {
	
public List<Jabatan> findAllJabatan();
	
	public Jabatan saveJabatan(Jabatan jabatan);
	
	public Optional<Jabatan> updateJabatan(Long id);
	
	public void deleteJabatan(Long id);

	Optional<Jabatan> findById(Long id);

}
