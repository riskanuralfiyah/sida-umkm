package com.SIDA.UMKM.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SIDA.UMKM.entities.Kecamatan;
import com.SIDA.UMKM.entities.Kota;

public interface KecamatanRepository extends JpaRepository<Kecamatan, Long> {

	List<Kecamatan> findByKotaId(Kota kotaId);
}
