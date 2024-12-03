package com.SIDA.UMKM.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SIDA.UMKM.entities.UMKM;

public interface UMKMRepository extends JpaRepository<UMKM, Long> {

//	public UMKM saveUMKM(UMKM umkm, String alamatPemilik, String alamatUsaha);

	long count();

	@Query("SELECT u.jenisKegiatanUsaha, COUNT(u) FROM UMKM u GROUP BY u.jenisKegiatanUsaha")
	List<Object[]> countUMKMByCategory();

	@Query("SELECT u FROM UMKM u")
	List<UMKM> getUMKMLimit3();

//    @Query("SELECT u FROM UMKM u ORDER BY u.id DESC")
	List<UMKM> findTop9ByOrderByIdDesc();

	@Query(value = "SELECT COUNT(kecamatan) AS jumlah, kecamatan " + "FROM UMKM " + "WHERE kota LIKE %:kota% "
			+ "GROUP BY kecamatan")
	List<Object[]> countUmkmByKecamatanInKotaCirebon(String kota);
}
