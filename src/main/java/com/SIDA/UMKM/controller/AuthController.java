package com.SIDA.UMKM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.SIDA.UMKM.dto.TestimonialsDto;
import com.SIDA.UMKM.dto.UserDto;
import com.SIDA.UMKM.entities.Berita;
import com.SIDA.UMKM.entities.Kontak;
import com.SIDA.UMKM.entities.Slider;
import com.SIDA.UMKM.entities.UMKM;
import com.SIDA.UMKM.entities.User;
import com.SIDA.UMKM.service.UserService;
import com.SIDA.UMKM.service.impl.BeritaServiceImpl;
import com.SIDA.UMKM.service.impl.SliderServiceImpl;
import com.SIDA.UMKM.service.impl.TestimonialsServiceImpl;
import com.SIDA.UMKM.service.impl.UMKMServiceImpl;
import com.SIDA.UMKM.service.impl.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class AuthController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private SliderServiceImpl sliderServiceImpl;
	
	@Autowired
	private BeritaServiceImpl beritaServiceImpl;
	
	@Autowired
	private UMKMServiceImpl umkmServiceImpl;
	
	@Autowired
	private TestimonialsServiceImpl testimonialsServiceImpl;

	@GetMapping("/")
    public String index(Model model) {
		List<Slider> sliders = sliderServiceImpl.findAllSlider();
		List<Berita> berita = beritaServiceImpl.get3Berita();
		long jumlahUMKM = umkmServiceImpl.totalUMKM();
		List<UMKM> umkm = umkmServiceImpl.find9DataUMKM();
		List<TestimonialsDto> dto = testimonialsServiceImpl.getAllTestimonial();
		Kontak kontak = new Kontak();
		model.addAttribute("addNewKontak", kontak);
		model.addAttribute("berita", berita);
		model.addAttribute("slider", sliders);
		model.addAttribute("jumlahUMKM", jumlahUMKM);
		model.addAttribute("umkm", umkm);
		model.addAttribute("testi", dto);
        return "fe/index-fe"; // Halaman utama yang ingin ditampilkan sebelum login
    }
	
	@GetMapping("/index")
	public String home(Model model) {
		List<UserDto> user = userServiceImpl.findAllUsers();
		model.addAttribute("users", user);
		return "/be-admin/display";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		// create model object to store form data
		UserDto user = new UserDto();
		model.addAttribute("user", user);
		return "/register";
	}

	// handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userServiceImpl.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
            
        }

        userServiceImpl.saveUser(userDto);
        return "redirect:/register?success";
    }
    
    // handler method to handle list of users
    @GetMapping("/users")
    public String users(Model model){
        List<UserDto> users = userServiceImpl.findAllUsers();
        model.addAttribute("users", users);
        return "/be-admin/display";
    }
    
    // handler method to handle login request
    @GetMapping("/login")
    public String login(){
        return "/login2";
    }
    
//    @GetMapping("/logout")
//    public String logout() {
//    	return "redirect:/login";
//    }
    
    @GetMapping("/custom-logout")
    public String customLogout(HttpServletRequest request, HttpServletResponse response) {
        // Get the Spring Authentication object of the current request.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // In case you are not filtering the users of this request url.
        if (authentication != null){    
            new SecurityContextLogoutHandler().logout(request, response, authentication); // <= This is the call you are looking for.
        }
        return "redirect:/";
    }
}
