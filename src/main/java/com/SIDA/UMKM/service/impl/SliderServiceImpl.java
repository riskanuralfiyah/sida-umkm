package com.SIDA.UMKM.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.SIDA.UMKM.entities.Slider;
import com.SIDA.UMKM.repository.SliderRepository;
import com.SIDA.UMKM.service.SliderService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SliderServiceImpl implements SliderService{

	private final SliderRepository sliderRepository;

	@Override
	public List<Slider> findAllSlider() {
		// TODO Auto-generated method stub
		return sliderRepository.findAll();
	}

	@Override
	public Slider saveSlider(Slider slider) {
		// TODO Auto-generated method stub
		return sliderRepository.save(slider);
	}

	@Override
	public Optional<Slider> updateSlider(Long id) {
		// TODO Auto-generated method stub
		return sliderRepository.findById(id);
	}

	@Override
	public void deleteSlider(Long id) {
		// TODO Auto-generated method stub
		sliderRepository.deleteById(id);
	}
}
