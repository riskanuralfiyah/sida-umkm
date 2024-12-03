package com.SIDA.UMKM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SIDA.UMKM.entities.Berita;
import com.SIDA.UMKM.entities.Slider;
import com.SIDA.UMKM.entities.UMKM;
import com.SIDA.UMKM.service.impl.BeritaServiceImpl;
import com.SIDA.UMKM.service.impl.SliderServiceImpl;
import com.SIDA.UMKM.service.impl.UMKMServiceImpl;

@Controller
@RequestMapping("/fe")
public class FeController {
	
	@Autowired
	private SliderServiceImpl sliderServiceImpl;
	
	@Autowired
	private BeritaServiceImpl beritaServiceImpl;
	
	@Autowired
	private UMKMServiceImpl umkmServiceImpl;

	@GetMapping("/home")
	public String Home(Model model) {
		List<Slider> sliders = sliderServiceImpl.findAllSlider();
		List<Berita> berita = beritaServiceImpl.get3Berita();
		List<UMKM> jumlahUMKM = umkmServiceImpl.find9DataUMKM();
		model.addAttribute("berita", berita);
		model.addAttribute("slider", sliders);
		model.addAttribute("jumlahUMKM", jumlahUMKM);
		return "/fe/index-fe";
	}
}
