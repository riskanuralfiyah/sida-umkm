package com.SIDA.UMKM.service;

import java.util.List;
import java.util.Optional;

import com.SIDA.UMKM.entities.Management;

public interface ManagementService {

	public List<Management> findAllManagement();
	
	public Management saveManagement(Management management);
	
	public Optional<Management> updateManagement(Long id);
	
	public void deleteManagement(Long id);
}
