package com.SIDA.UMKM.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.SIDA.UMKM.entities.Berita;
import com.SIDA.UMKM.entities.Jabatan;
import com.SIDA.UMKM.service.ExcelExportJabatanService;
import com.SIDA.UMKM.service.PdfExportJabatanService;
import com.SIDA.UMKM.service.impl.JabatanServiceImpl;
import com.SIDA.UMKM.util.FileUploadUtil;
import java.util.List; // Ganti import ini


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Controller
@RequestMapping("/jabatan")
public class JabatanController {

    @Autowired
    private JabatanServiceImpl jabatanServiceImpl;
    
    @Autowired
    private PdfExportJabatanService pdfExportJabatanService;
    
    @Autowired
    private ExcelExportJabatanService excelExportJabatanService;

    @GetMapping(value = "/list")
    public String findJabatanList(Model model) {
        model.addAttribute("jabatan", jabatanServiceImpl.findAllJabatan());
        return "be-admin/jabatan/jabatan";
    }

    @GetMapping(value = "/new")
    public String loadFormAdd(Model model) {
        model.addAttribute("addNewJabatan", new Jabatan());
        return "be-admin/jabatan/tambah-jabatan";
    }

    @PostMapping(value = "/submit")
	public String saveBerita(@Valid @ModelAttribute(value = "addNewJabatan") Jabatan jabatan, BindingResult result,
			@RequestParam("image") @NotNull MultipartFile multipartFile) {
		if (result.hasErrors()) {
			return "/be-admin/jabatan/tambah-jabatan"; // Return the form page with errors
		} else {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			jabatan.setPictJabatan(fileName);
			Jabatan saveJabatan = jabatanServiceImpl.saveJabatan(jabatan);
			String uploadDir = "src/main/resources/static/assets/images/jabatan/";
			try {
				FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:/jabatan/list";
		}
	}


    @GetMapping(value = "/form/{id}")
    public String loadFormUpdate(Model model, @PathVariable(name = "id") Long id) {
        Jabatan jabatan = jabatanServiceImpl.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Gagal mendapatkan id: " + id));
        model.addAttribute("getEditJabatan", jabatan);
        return "be-admin/jabatan/edit-jabatan";
    }



    @PostMapping("/update/{id}")
    public String updateJabatan(@PathVariable("id") Long id,
                                 @Valid @ModelAttribute("getEditJabatan") Jabatan jabatan,
                                 BindingResult result,
                                 @RequestParam(value = "image", required = false) MultipartFile multipartFile) {
        if (result.hasErrors()) {
            return "be-admin/jabatan/edit-jabatan"; // Kembali ke form dengan error jika ada
        }

        // Cek apakah file gambar baru diunggah
        if (multipartFile != null && !multipartFile.isEmpty()) {
            try {
                // Simpan file gambar baru ke server
                String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                String uploadDir = "src/main/resources/static/assets/images/jabatan/";

                // Simpan file gambar di direktori yang ditentukan
                FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

                // Simpan nama file gambar baru ke dalam entitas Jabatan
                jabatan.setPictJabatan(fileName);
            } catch (IOException e) {
                e.printStackTrace();
                result.rejectValue("image", "error.image", "Gagal mengunggah gambar");
                return "be-admin/jabatan/edit-jabatan";
            }
        } else {
            // Jika tidak ada file baru yang diunggah, tetap gunakan gambar yang sudah ada
            Optional<Jabatan> existingJabatanOpt = jabatanServiceImpl.findById(id);
            if (existingJabatanOpt.isPresent()) {
                Jabatan existingJabatan = existingJabatanOpt.get();
                jabatan.setPictJabatan(existingJabatan.getPictJabatan());
            } else {
                result.rejectValue("id", "error.id", "Jabatan tidak ditemukan");
                return "be-admin/jabatan/edit-jabatan";
            }
        }

        // Simpan perubahan pada entitas Jabatan ke database
        jabatanServiceImpl.saveJabatan(jabatan);

        return "redirect:/jabatan/list"; // Redirect ke list page setelah berhasil update
    }


    @GetMapping(value = "/delete/{id}")
    public String deleteJabatan(@PathVariable(value = "id") Long id) {
        jabatanServiceImpl.deleteJabatan(id);
        return "redirect:/jabatan/list";
    }
    
    @GetMapping("/export/pdf")
    public ResponseEntity<InputStreamResource> exportJabatanToPdf() {
        List<Jabatan> jabatans = jabatanServiceImpl.findAllJabatan(); // Gunakan jabatanServiceImpl
        ByteArrayInputStream bis = pdfExportJabatanService.exportJabatanToPdf(jabatans); // Panggil metode dari service

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=jabatan.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
    
    
    @GetMapping("/export/excel")
    public ResponseEntity<InputStreamResource> exportJabatanToExcel() {
    	List<Jabatan> jabatans = jabatanServiceImpl.findAllJabatan(); 
        ByteArrayInputStream bis = excelExportJabatanService.exportJabatanToExcel(jabatans);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=jabatan.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(bis));
    }
}
