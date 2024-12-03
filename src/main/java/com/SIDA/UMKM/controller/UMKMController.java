package com.SIDA.UMKM.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.SIDA.UMKM.entities.Desa;
import com.SIDA.UMKM.entities.Kecamatan;
import com.SIDA.UMKM.entities.Kota;
import com.SIDA.UMKM.entities.Provinsi;
import com.SIDA.UMKM.entities.UMKM;
import com.SIDA.UMKM.service.impl.UMKMServiceImpl;
import com.SIDA.UMKM.util.FileUploadUtil;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import com.SIDA.UMKM.service.impl.DesaServiceImpl;
import com.SIDA.UMKM.service.impl.KecamatanServiceImpl;
import com.SIDA.UMKM.service.impl.KotaServiceImpl;
import com.SIDA.UMKM.service.impl.ProvinsiServiceImpl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Controller
@RequestMapping("/umkm")
@AllArgsConstructor
@NoArgsConstructor
public class UMKMController {

	@Autowired
	private UMKMServiceImpl umkmServiceImpl;

	@Autowired
	private ProvinsiServiceImpl provinsiServiceImpl;

	@Autowired
	private KotaServiceImpl kotaServiceImpl;

	@Autowired
	private KecamatanServiceImpl kecamatanServiceImpl;

	@Autowired
	private DesaServiceImpl desaServiceImpl;

//	@GetMapping("/list")
//	public String findUMKMList(Model model) {
//		model.addAttribute("umkmList", umkmServiceImpl.findUMKMList());
//		return "/be/umkm/umkm";
//	}

	@GetMapping("/list")
	public String showItems(Model model, @RequestParam(defaultValue = "0") String page) {
		int pageSize = 10; // Atur sesuai kebutuhan
		int currentPage = Integer.parseInt(page); // Konversi page ke int
		Page<UMKM> itemPage = umkmServiceImpl.findPaginated(currentPage, pageSize);
		List<UMKM> umkmList = itemPage.getContent();
		model.addAttribute("umkmList", umkmList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", itemPage.getTotalPages());

		return "/be-admin/umkm/umkm";
	}
	
//	@GetMapping("/new")
//	public String loadFormUMKM(Model model) {
//		model.addAttribute("addNewUMKM", new UMKM());
//		return "/be/umkm/addNewUMKM";
//	}
//	
//	@PostMapping("/submit")
//	public String saveUMKM(@Valid @ModelAttribute(value = "addNewUMKM") UMKM umkm, BindingResult result, Model model) {
//		if(result.hasErrors()) {
//			return "/be/umkm/addNewUMKM";
//		}
//		return "redirect:/umkm/list";
//	}

	@GetMapping("/new")
	public String loadFormUMKM(Model model) {
		UMKM umkm = new UMKM();
		model.addAttribute("addNewUMKM", umkm);
		model.addAttribute("listProvinsi", provinsiServiceImpl.getProvinsiList());
		return "/be-admin/umkm/add-new-UMKM";
	}

	@PostMapping("/submit")
	public String saveUMKM(@Valid @ModelAttribute(value = "addNewUMKM") UMKM umkm, BindingResult result, Model model,
			@RequestParam("provinsi") Long provinsi, @RequestParam("kota") Long kota,
			@RequestParam("kecamatan") Long kecamatan, @RequestParam("desa") Long desa,
			@RequestParam("provinsi2") Long provinsi2, @RequestParam("kota2") Long kota2,
			@RequestParam("kecamatan2") Long kecamatan2, @RequestParam("desa2") Long desa2,
			@RequestParam("image") @NotNull MultipartFile multipartFile) {
		if (result.hasErrors()) {
			// Jika terdapat kesalahan validasi, kembali ke halaman form dengan model yang
			// sama
			model.addAttribute("listProvinsi", provinsiServiceImpl.getProvinsiList());
			return "/be-admin/umkm/add-new-UMKM";
		}
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		umkm.setLogo(fileName);
		umkmServiceImpl.saveNewUMKM(umkm, provinsi, kota, kecamatan, desa, provinsi2, kota2, kecamatan2, desa2);
		String uploadDir = "src/main/resources/static/assets/images/umkm/";
		try {
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/umkm/list";

	}

	@GetMapping("/edit/{id}")
	public String loadEditFormUMKM(Model model, @PathVariable(name = "id") Long id) {
		UMKM umkm = umkmServiceImpl.updateDataUMKM(id)
				.orElseThrow(() -> new IllegalArgumentException("Gagal mendapatkan id: " + id));
		model.addAttribute("getEditUMKM", umkm);
		model.addAttribute("listProvinsi", provinsiServiceImpl.getProvinsiList());
		return "/be-admin/umkm/edit-UMKM";
	}

	@PostMapping("/update/{id}")
	public String updateDataUMKM(@ModelAttribute(value = "umkm") UMKM umkm, @RequestParam("provinsi") Long provinsi,
			@RequestParam("kota") Long kota, @RequestParam("kecamatan") Long kecamatan, @RequestParam("desa") Long desa,
			@RequestParam("provinsi2") Long provinsi2, @RequestParam("kota2") Long kota2,
			@RequestParam("kecamatan2") Long kecamatan2, @RequestParam("desa2") Long desa2,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {
//		umkmServiceImpl.saveDataUMKM(umkm, provinsi, kota, kecamatan, desa, provinsi2, kota2, kecamatan2, desa2);
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		umkm.setLogo(fileName);
		umkmServiceImpl.editExistingUMKM(umkm, provinsi, kota, kecamatan, desa, provinsi2, kota2, kecamatan2, desa2);
		String uploadDir = "src/main/resources/static/assets/images/umkm/";
		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		return "redirect:/umkm/list";
	}

	@GetMapping("/delete/{id}")
	public String deleteDataUMKM(@PathVariable(name = "id") Long id) {
		this.umkmServiceImpl.deleteDataUMKM(id);
		return "redirect:/umkm/list";
	}

	@PostMapping("/import")
	public String cretePostImport(@RequestParam(name = "file") MultipartFile file) throws Exception {

		umkmServiceImpl.importExcelSIDAUMKM(file);

		return "redirect:/umkm/list";
	}

	@GetMapping("/api/kota")
	@ResponseBody
	public List<Kota> getKotaByProvinsi(@RequestParam Provinsi provinsiId) {
		return kotaServiceImpl.getkotaByProvinsi(provinsiId);
	}

	@GetMapping("/api/kecamatan")
	@ResponseBody
	public List<Kecamatan> getKecamatanByKota(@RequestParam Kota kotaId) {
		return kecamatanServiceImpl.getKecamatanByKota(kotaId);
	}

	@GetMapping("/api/desa")
	@ResponseBody
	public List<Desa> getDesaByKecamatan(@RequestParam Kecamatan kecamatanId) {
		return desaServiceImpl.getDesaByKecamatan(kecamatanId);
	}
}
