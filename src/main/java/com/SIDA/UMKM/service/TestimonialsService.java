package com.SIDA.UMKM.service;

import java.util.List;

import com.SIDA.UMKM.dto.TestimonialsDto;

public interface TestimonialsService {

	public List<TestimonialsDto> getAllTestimonial();
	
	public TestimonialsDto getTestimonialById(Long testimonialId);
	
	public void createTestimonial(TestimonialsDto dto);
	
	public void updateTestimonial(Long testimonialId, TestimonialsDto dto);
	
	public void deleteTestimonial(Long testimonialId);
	
//	private Testimonials convertToEntity(TestimonialDto dto);
//	
//	private TestimonialDto convertToDto(Testimonials testimonial);
}
