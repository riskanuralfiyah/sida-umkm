package com.SIDA.UMKM.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.SIDA.UMKM.entities.Desa;
import com.SIDA.UMKM.entities.Kecamatan;
import com.SIDA.UMKM.entities.Kota;
import com.SIDA.UMKM.entities.Provinsi;
import com.SIDA.UMKM.entities.UMKM;

public interface UMKMService {

	//Get All Data
	public List<UMKM> findUMKMList();
	
	//Get Data By id
	public Optional<UMKM> findUMKMById(Long id);
	
	//Save Data UMKM
	public UMKM saveNewUMKM(UMKM umkm, Long provinsi, Long kota, Long kecamatan, Long desa, Long provinsi2, Long kota2, Long kecamatan2, Long desa2);
	
	//Update Data UMKM
	public Optional<UMKM> updateDataUMKM(Long id);
	
	//Save Data Edit UMKM
	public UMKM editExistingUMKM(UMKM umkm, Long provinsi, Long kota, Long kecamatan, Long desa, Long provinsi2, Long kota2, Long kecamatan2, Long desa2);
	
	//Delete Data UMKM
	public void deleteDataUMKM(Long id);
	
	public void importExcelSIDAUMKM(MultipartFile file) throws Exception;
	
	public Page<UMKM> findPaginated(int page, int size);
	
	public Long totalUMKM();
	
	public List<Object[]> countUMKMByCategory();
	
	public List<UMKM> find9DataUMKM();
	
	public List<Object[]> countKecamatanByKota(String kota);
	
//	public List<Provinsi> getAllProvinsi();
//	
//	public List<Kota> getKotaByProvinsi(Integer provId);
//	
//	public List<Kecamatan> getKecamatanByKota(Integer cityId);
//	
//	public List<Desa> getDesaByKecamatan(Integer disId);
}
