package com.SIDA.UMKM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SIDA.UMKM.entities.Management;
import com.SIDA.UMKM.service.impl.ManagementServiceImpl;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/management")
public class ManagementController {

	private final ManagementServiceImpl managementServiceImpl;
	
	@GetMapping(value = "/list")
	public String findManagementList(Model model) {
		model.addAttribute("management", managementServiceImpl.findAllManagement());
		return "be-admin/management/management";
	}
	
	@GetMapping(value = "/new")
	public String loadFormAdd(Model model) {
		Management management = new Management();
		model.addAttribute("addNewmanagement", management);
		return "be-admin/management/tambah-management";
	}
	
	@PostMapping(value = "/submit")
	public String saveManagement(@ModelAttribute(value = "addNewmanagement") Management management){
        Management saveManagement = managementServiceImpl.saveManagement(management);
		return "redirect:/management/list";
	}
	
	@GetMapping(value = "/form/{id}")
	public String loadFormUpdate(Model model, @PathVariable(name = "id") Long id) {
		Management management = managementServiceImpl.updateManagement(id)
				.orElseThrow(() -> new IllegalArgumentException("Gagal mendapatkan id: " + id));
		model.addAttribute("getEditManagement", management);
		return "be-admin/management/edit-management";
	}
	
	@PostMapping(value = "/update/{id}")
	private String updateDataManagement(Model model, @ModelAttribute(value = "getEditManagement") Management management, BindingResult result) {
		managementServiceImpl.saveManagement(management);
		return "redirect:/management/list";
	}
	
	@GetMapping(value = "/delete/{id}")
	private String deleteManagement(@PathVariable(value = "id") Long id) {
		this.managementServiceImpl.deleteManagement(id);
		return "redirect:/management/list";
	}
}
