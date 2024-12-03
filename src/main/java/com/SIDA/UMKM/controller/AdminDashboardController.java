package com.SIDA.UMKM.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SIDA.UMKM.service.impl.UMKMServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

	@Autowired
	private UMKMServiceImpl umkmServiceImpl;

	@GetMapping("/dashboard")
	public String GetDashboardIndex(Model model) {
		
		// Chart 1: Jumlah UMKM berdasarkan kategori
		List<Object[]> umkmData = umkmServiceImpl.countUMKMByCategory();
		String[] labels = new String[umkmData.size()];
		int[] data = new int[umkmData.size()];

		for (int i = 0; i < umkmData.size(); i++) {
			Object[] row = umkmData.get(i);
			labels[i] = (String) row[0];
			data[i] = ((Number) row[1]).intValue();
		}
		
		model.addAttribute("labels", labels);
		model.addAttribute("data", data);

		// Chart 2: Jumlah UMKM berdasarkan kecamatan di Kota CIREBON
		String kota = "CIREBON";
		Map<String, Integer> umkmDataKec = umkmServiceImpl.countUmkmByKecamatanInKotaCirebon(kota);
		String[] labelsByKecamatan = umkmDataKec.keySet().toArray(new String[0]);
		int[] dataByKecamatan = umkmDataKec.values().stream().mapToInt(Integer::intValue).toArray();
		model.addAttribute("labelsByKecamatan", labelsByKecamatan);
		model.addAttribute("dataByKecamatan", dataByKecamatan);
		
//		String kota = "CIREBON";
//		List<Object[]> umkmDataKec = umkmServiceImpl.countKecamatanByKota(kota);
//		String[] labelsByKecamatan = new String[umkmDataKec.size()];
//		int[] dataByKecamatan = new int[umkmDataKec.size()];
//
//		for (int i = 0; i < umkmDataKec.size(); i++) {
//			Object[] row = umkmDataKec.get(i);
//			labelsByKecamatan[i] = (String) row[0];
//			dataByKecamatan[i] = ((Number) row[1]).intValue();
//		}
//		
//		model.addAttribute("labelsByKecamatan", labelsByKecamatan);
//		model.addAttribute("dataByKecamatan", dataByKecamatan);
		
		return "/be-admin/dashboard/dashboard";
	}

//	@GetMapping("/umkm/chart")
//	public String showChart(Model model) {
//		String kota = "CIREBON";
//		Map<String, Integer> umkmData = umkmServiceImpl.countUmkmByKecamatanInKotaCirebon(kota);
//		String[] labelsKec = umkmData.keySet().toArray(new String[0]);
//		int[] dataKec = umkmData.values().stream().mapToInt(Integer::intValue).toArray();
//		model.addAttribute("labels", labelsKec);
//		model.addAttribute("data", dataKec);
//		return "chart";
//	}
}
