package com.SIDA.UMKM.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.SIDA.UMKM.entities.Desa;
import com.SIDA.UMKM.entities.Kecamatan;
import com.SIDA.UMKM.entities.Kota;
import com.SIDA.UMKM.entities.Provinsi;
import com.SIDA.UMKM.entities.UMKM;
import com.SIDA.UMKM.repository.DesaRepository;
import com.SIDA.UMKM.repository.KecamatanRepository;
import com.SIDA.UMKM.repository.KotaRepository;
import com.SIDA.UMKM.repository.ProvinsiRepository;
import com.SIDA.UMKM.repository.UMKMRepository;
import com.SIDA.UMKM.service.UMKMService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UMKMServiceImpl implements UMKMService {

	@Autowired
	private UMKMRepository umkmRepository;

	@Autowired
	private ProvinsiRepository provinsiRepository;

	@Autowired
	private KotaRepository kotaRepository;

	@Autowired
	private KecamatanRepository kecamatanRepository;

	@Autowired
	private DesaRepository desaRepository;

	@Override
	public List<UMKM> findUMKMList() {
		// TODO Auto-generated method stub
		return umkmRepository.findAll();
	}

	@Override
	public Page<UMKM> findPaginated(int page, int size) {
		// TODO Auto-generated method stub
		return umkmRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public Optional<UMKM> findUMKMById(Long id) {
		// TODO Auto-generated method stub
		return umkmRepository.findById(id);
	}

	@Override
	public UMKM saveNewUMKM(UMKM umkm, Long provinsi, Long kota, Long kecamatan, Long desa, Long provinsi2, Long kota2,
			Long kecamatan2, Long desa2) {

		Desa desaBaru = desaRepository.findById(desa).orElseThrow();
		Kecamatan kecamatanBaru = kecamatanRepository.findById(kecamatan).orElseThrow();
		Kota kotaBaru = kotaRepository.findById(kota).orElseThrow();
		Provinsi provinsiBaru = provinsiRepository.findById(provinsi).orElseThrow();

		Desa desaBaru2 = desaRepository.findById(desa2).orElseThrow();
		Kecamatan kecamatanBaru2 = kecamatanRepository.findById(kecamatan2).orElseThrow();
		Kota kotaBaru2 = kotaRepository.findById(kota2).orElseThrow();
		Provinsi provinsiBaru2 = provinsiRepository.findById(provinsi2).orElseThrow();

		String alamatPemilikGabung = (umkm.getAlamatPemilik() + ", " + desaBaru.getNamaDesa() + ", "
				+ kecamatanBaru.getNamaKecamatan() + ", " + kotaBaru.getNamaKota() + ", "
				+ provinsiBaru.getNamaProvinsi());
		String alamatUsahaGabung = (umkm.getAlamatUsaha() + ", " + desaBaru2.getNamaDesa() + ", "
				+ kecamatanBaru2.getNamaKecamatan() + ", " + kotaBaru2.getNamaKota() + ", "
				+ provinsiBaru2.getNamaProvinsi());

//		String alamatPemilikGabung = (umkm.getAlamatPemilik() + ", " + desa + ", " + kecamatan + ", " + kota + ", " + provinsi);
//		String alamatUsahaGabung = (umkm.getAlamatUsaha() + ", " + desa2 + ", " + kecamatan2 + ", " + kota2 + ", " + provinsi2);

		UMKM umkmBaru = new UMKM();
		umkmBaru.setNik(umkm.getNik());
		umkmBaru.setNamaPemilik(umkm.getNamaPemilik());
		umkmBaru.setProduk(umkm.getProduk());
		umkmBaru.setAlamatPemilik(alamatPemilikGabung);
		umkmBaru.setAlamatUsaha(alamatUsahaGabung);
		umkmBaru.setOmsetPerTahun(umkm.getOmsetPerTahun());
		umkmBaru.setKekayaanBersih(umkm.getKekayaanBersih());
		umkmBaru.setJenisKegiatanUsaha(umkm.getJenisKegiatanUsaha());
		umkmBaru.setIjinUsaha(umkm.getIjinUsaha());
		umkmBaru.setPermasalahan(umkm.getPermasalahan());
		umkmBaru.setKluster(umkm.getKluster());
		umkmBaru.setKeterangan(umkm.getKeterangan());
		umkmBaru.setLogo(umkm.getLogo());
		umkmBaru.setProvinsi(provinsiBaru2.getNamaProvinsi());
		umkmBaru.setKota(kotaBaru2.getNamaKota());
		umkmBaru.setKecamatan(kecamatanBaru2.getNamaKecamatan());
		umkmBaru.setDesa(desaBaru2.getNamaDesa());
		return umkmRepository.save(umkmBaru);
	}

	@Override
	public UMKM editExistingUMKM(UMKM umkm, Long provinsiId, Long kotaId, Long kecamatanId, Long desaId, Long provinsiId2,
	        Long kotaId2, Long kecamatanId2, Long desaId2) {

	    Desa desaBaru = desaRepository.findById(desaId).orElseThrow();
	    Kecamatan kecamatanBaru = kecamatanRepository.findById(kecamatanId).orElseThrow();
	    Kota kotaBaru = kotaRepository.findById(kotaId).orElseThrow();
	    Provinsi provinsiBaru = provinsiRepository.findById(provinsiId).orElseThrow();

	    Desa desaBaru2 = desaRepository.findById(desaId2).orElseThrow();
	    Kecamatan kecamatanBaru2 = kecamatanRepository.findById(kecamatanId2).orElseThrow();
	    Kota kotaBaru2 = kotaRepository.findById(kotaId2).orElseThrow();
	    Provinsi provinsiBaru2 = provinsiRepository.findById(provinsiId2).orElseThrow();

	    // Update alamat pemilik dan alamat usaha
	    umkm.setAlamatPemilik(umkm.getAlamatPemilik() + ", " + desaBaru.getNamaDesa() + ", "
	            + kecamatanBaru.getNamaKecamatan() + ", " + kotaBaru.getNamaKota() + ", "
	            + provinsiBaru.getNamaProvinsi());

	    umkm.setAlamatUsaha(umkm.getAlamatUsaha() + ", " + desaBaru2.getNamaDesa() + ", "
	            + kecamatanBaru2.getNamaKecamatan() + ", " + kotaBaru2.getNamaKota() + ", "
	            + provinsiBaru2.getNamaProvinsi());

	    // Simpan data yang sudah diupdate
	    return umkmRepository.save(umkm);
	}

//	@Override
//	public UMKM saveNewUMKM(UMKM umkm, Long provinsiId, Long kotaId, Long kecamatanId, Long desaId, Long provinsiId2,
//			Long kotaId2, Long kecamatanId2, Long desaId2) {
//		
//		// Validasi input
//		Provinsi provinsiBaru = provinsiRepository.findById(provinsiId).orElseThrow(
//				() -> new EntityNotFoundException("Provinsi dengan ID " + provinsiId + " tidak ditemukan"));
//		Kota kotaBaru = kotaRepository.findById(kotaId)
//				.orElseThrow(() -> new EntityNotFoundException("Kota dengan ID " + kotaId + " tidak ditemukan"));
//		Kecamatan kecamatanBaru = kecamatanRepository.findById(kecamatanId).orElseThrow(
//				() -> new EntityNotFoundException("Kecamatan dengan ID " + kecamatanId + " tidak ditemukan"));
//		Desa desaBaru = desaRepository.findById(desaId)
//				.orElseThrow(() -> new EntityNotFoundException("Desa dengan ID " + desaId + " tidak ditemukan"));
//
//		Provinsi provinsiBaru2 = provinsiRepository.findById(provinsiId2).orElseThrow(
//				() -> new EntityNotFoundException("Provinsi dengan ID " + provinsiId2 + " tidak ditemukan"));
//		Kota kotaBaru2 = kotaRepository.findById(kotaId2)
//				.orElseThrow(() -> new EntityNotFoundException("Kota dengan ID " + kotaId2 + " tidak ditemukan"));
//		Kecamatan kecamatanBaru2 = kecamatanRepository.findById(kecamatanId2).orElseThrow(
//				() -> new EntityNotFoundException("Kecamatan dengan ID " + kecamatanId2 + " tidak ditemukan"));
//		Desa desaBaru2 = desaRepository.findById(desaId2)
//				.orElseThrow(() -> new EntityNotFoundException("Desa dengan ID " + desaId2 + " tidak ditemukan"));
//
//		// Update alamat pemilik dan alamat usaha
//		umkm.setAlamatPemilik(
//				umkm.getAlamatPemilik() + ", " + desaBaru.getNamaDesa() + ", " + kecamatanBaru.getNamaKecamatan() + ", "
//						+ kotaBaru.getNamaKota() + ", " + provinsiBaru.getNamaProvinsi());
//
//		umkm.setAlamatUsaha(
//				umkm.getAlamatUsaha() + ", " + desaBaru2.getNamaDesa() + ", " + kecamatanBaru2.getNamaKecamatan() + ", "
//						+ kotaBaru2.getNamaKota() + ", " + provinsiBaru2.getNamaProvinsi());
//
//		// Simpan data yang sudah diupdate
//		return umkmRepository.save(umkm);
//	}

//	@Override
//	public UMKM editExistingUMKM(UMKM updatedUMKM, Long umkmId, Long provinsiId, Long kotaId, Long kecamatanId,
//			Long desaId, Long provinsiId2, Long kotaId2, Long kecamatanId2, Long desaId2) {
//
//		UMKM existingUMKM = umkmRepository.findById(umkmId)
//				.orElseThrow(() -> new EntityNotFoundException("UMKM dengan ID " + umkmId + " tidak ditemukan"));
//
//		// Validasi input
//		Provinsi provinsiBaru = provinsiRepository.findById(provinsiId).orElseThrow(
//				() -> new EntityNotFoundException("Provinsi dengan ID " + provinsiId + " tidak ditemukan"));
//		Kota kotaBaru = kotaRepository.findById(kotaId)
//				.orElseThrow(() -> new EntityNotFoundException("Kota dengan ID " + kotaId + " tidak ditemukan"));
//		Kecamatan kecamatanBaru = kecamatanRepository.findById(kecamatanId).orElseThrow(
//				() -> new EntityNotFoundException("Kecamatan dengan ID " + kecamatanId + " tidak ditemukan"));
//		Desa desaBaru = desaRepository.findById(desaId)
//				.orElseThrow(() -> new EntityNotFoundException("Desa dengan ID " + desaId + " tidak ditemukan"));
//
//		Provinsi provinsiBaru2 = provinsiRepository.findById(provinsiId2).orElseThrow(
//				() -> new EntityNotFoundException("Provinsi dengan ID " + provinsiId2 + " tidak ditemukan"));
//		Kota kotaBaru2 = kotaRepository.findById(kotaId2)
//				.orElseThrow(() -> new EntityNotFoundException("Kota dengan ID " + kotaId2 + " tidak ditemukan"));
//		Kecamatan kecamatanBaru2 = kecamatanRepository.findById(kecamatanId2).orElseThrow(
//				() -> new EntityNotFoundException("Kecamatan dengan ID " + kecamatanId2 + " tidak ditemukan"));
//		Desa desaBaru2 = desaRepository.findById(desaId2)
//				.orElseThrow(() -> new EntityNotFoundException("Desa dengan ID " + desaId2 + " tidak ditemukan"));
//
//		// Update alamat pemilik dan alamat usaha
//		existingUMKM.setAlamatPemilik(
//				updatedUMKM.getAlamatPemilik() + ", " + desaBaru.getNamaDesa() + ", " + kecamatanBaru.getNamaKecamatan()
//						+ ", " + kotaBaru.getNamaKota() + ", " + provinsiBaru.getNamaProvinsi());
//
//		existingUMKM.setAlamatUsaha(
//				updatedUMKM.getAlamatUsaha() + ", " + desaBaru2.getNamaDesa() + ", " + kecamatanBaru2.getNamaKecamatan()
//						+ ", " + kotaBaru2.getNamaKota() + ", " + provinsiBaru2.getNamaProvinsi());
//
//		// Simpan data yang sudah diupdate
//		return umkmRepository.save(existingUMKM);
//	}

	@Override
	public Optional<UMKM> updateDataUMKM(Long id) {
		// TODO Auto-generated method stub
		return umkmRepository.findById(id);
	}

	@Override
	public void deleteDataUMKM(Long id) {
		// TODO Auto-generated method stub
		umkmRepository.deleteById(id);
	}

	@Override
	public void importExcelSIDAUMKM(MultipartFile file) throws Exception {
		Workbook workbook = new XSSFWorkbook(file.getInputStream());
		Sheet sheet = workbook.getSheetAt(0);

		for (int i = 0; i < (CoutRowExcel(sheet.rowIterator())); i++) {
			if (i == 0) {
				continue;
			}

			Row row = sheet.getRow(i);

			UMKM umkm = new UMKM();
			String namaPemilik = row.getCell(2).getStringCellValue();
			String produk = row.getCell(3).getStringCellValue();
			String alamatPemilik = row.getCell(4).getStringCellValue();
			String alamatUsaha = row.getCell(5).getStringCellValue();
//			String omsetPerTahun = row.getCell(6).getStringCellValue();
//			String kekayaanBersih = row.getCell(7).getStringCellValue();
			String jenisKegiatanUsaha = row.getCell(8).getStringCellValue();
			String ijinUsaha = row.getCell(9).getStringCellValue();
			String permasalahan = row.getCell(10).getStringCellValue();
			String kluster = row.getCell(11).getStringCellValue();
			String keterangan = row.getCell(12).getStringCellValue();

			Cell cell1 = row.getCell(1); // Misalkan kolom pertama (indeks dimulai dari 0)

			if (cell1 != null && cell1.getCellType() == CellType.NUMERIC) {
				// Jika sel tidak kosong dan berisi nilai numerik
				String nik = NumberToTextConverter.toText(cell1.getNumericCellValue());
				System.out.println("Nilai dalam bentuk string: " + nik);
				umkm.setNik(nik);
			} else {
				// Handle jika sel kosong atau bukan tipe numerik
				System.out.println("Sel kosong atau bukan tipe numerik.");
			}
			double omsetPerTahunDouble = row.getCell(6).getNumericCellValue();
			BigDecimal omsetPerTahunBigDecimal = BigDecimal.valueOf(omsetPerTahunDouble);

			double kekayaanBersih = row.getCell(7).getNumericCellValue();
			BigDecimal kekayaanBersihBigDecimal = BigDecimal.valueOf(kekayaanBersih);

			umkm.setNamaPemilik(namaPemilik);
			umkm.setProduk(produk);
			umkm.setAlamatPemilik(alamatPemilik);
			umkm.setAlamatUsaha(alamatUsaha);
			umkm.setOmsetPerTahun(omsetPerTahunBigDecimal);
			umkm.setKekayaanBersih(kekayaanBersihBigDecimal);
			umkm.setJenisKegiatanUsaha(jenisKegiatanUsaha);
			umkm.setIjinUsaha(ijinUsaha);
			umkm.setPermasalahan(permasalahan);
			umkm.setKluster(kluster);
			umkm.setKeterangan(keterangan);

			umkmRepository.save(umkm);
		}
	}

	public static int CoutRowExcel(Iterator<Row> iterator) {
		int size = 0;
		while (iterator.hasNext()) {
			Row row = iterator.next();
			size++;
		}
		return size;
	}

	@Override
	public Long totalUMKM() {
		// TODO Auto-generated method stub
		return umkmRepository.count();
	}

	@Override
	public List<Object[]> countUMKMByCategory() {
		// TODO Auto-generated method stub
		return umkmRepository.countUMKMByCategory();
	}

	@Override
	public List<UMKM> find9DataUMKM() {
		// TODO Auto-generated method stub
		return umkmRepository.findTop9ByOrderByIdDesc();
	}

	@Override
	public List<Object[]> countKecamatanByKota(String kota) {
		// TODO Auto-generated method stub
		return umkmRepository.countUmkmByKecamatanInKotaCirebon(kota);
	}
	
	public Map<String, Integer> countUmkmByKecamatanInKotaCirebon(String kota) {
        List<Object[]> resultList = umkmRepository.countUmkmByKecamatanInKotaCirebon(kota);
        Map<String, Integer> result = new HashMap<>();
        
        for (Object[] row : resultList) {
            result.put((String) row[1], ((Number) row[0]).intValue());
        }
        
        return result;
    }

//	@Override
//	public List<Provinsi> getAllProvinsi() {
//		// TODO Auto-generated method stub
//		return provinsiRepository.findAll();
//	}
//
//	@Override
//	public List<Kota> getKotaByProvinsi(Integer provId) {
//		// TODO Auto-generated method stub
//		return kotaRepository.findByProvinsiId(provId);
//	}
//
//	@Override
//	public List<Kecamatan> getKecamatanByKota(Integer cityId) {
//		// TODO Auto-generated method stub
//		return kecamatanRepository.findByKotaId(cityId);
//	}
//
//	@Override
//	public List<Desa> getDesaByKecamatan(Integer disId) {
//		// TODO Auto-generated method stub
//		return desaRepository.findByKecamatanId(disId);
//	}
}
