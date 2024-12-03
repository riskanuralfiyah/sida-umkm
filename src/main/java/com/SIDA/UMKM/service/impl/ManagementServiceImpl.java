package com.SIDA.UMKM.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SIDA.UMKM.entities.Management;
import com.SIDA.UMKM.repository.ManagementRepository;
import com.SIDA.UMKM.service.ManagementService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ManagementServiceImpl implements ManagementService{
	
	@Autowired
	private final ManagementRepository managementRepository;

	@Override
	public List<Management> findAllManagement() {
		// TODO Auto-generated method stub
		return managementRepository.findAll();
	}

	@Override
	public Management saveManagement(Management management) {
		// TODO Auto-generated method stub
		return managementRepository.save(management);
	}

	@Override
	public Optional<Management> updateManagement(Long id) {
		// TODO Auto-generated method stub
		return managementRepository.findById(id);
	}

	@Override
	public void deleteManagement(Long id) {
		// TODO Auto-generated method stub
		this.managementRepository.deleteById(id);
	}

}
