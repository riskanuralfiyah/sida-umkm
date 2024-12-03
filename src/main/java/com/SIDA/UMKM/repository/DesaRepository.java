package com.SIDA.UMKM.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SIDA.UMKM.entities.Desa;
import com.SIDA.UMKM.entities.Kecamatan;

public interface DesaRepository extends JpaRepository<Desa, Long>{

	public List<Desa> findByKecamatanId(Kecamatan kecamatanId);
}
