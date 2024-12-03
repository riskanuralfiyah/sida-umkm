package com.SIDA.UMKM.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SIDA.UMKM.entities.Jabatan;

import org.springframework.data.jpa.repository.JpaRepository;
import com.SIDA.UMKM.entities.Jabatan;

public interface JabatanRepository extends JpaRepository<Jabatan, Long> {
    // Metode default findById tersedia
}
