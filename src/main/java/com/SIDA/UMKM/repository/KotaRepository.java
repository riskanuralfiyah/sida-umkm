package com.SIDA.UMKM.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SIDA.UMKM.entities.Kota;
import com.SIDA.UMKM.entities.Provinsi;

public interface KotaRepository extends JpaRepository<Kota, Long> {
    
	List<Kota> findByProvinsiId(Provinsi provinsiId);
}

