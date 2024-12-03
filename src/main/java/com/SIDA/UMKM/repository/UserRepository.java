package com.SIDA.UMKM.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SIDA.UMKM.dto.ChangePasswordUserDto;
import com.SIDA.UMKM.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);
	
//	ChangePasswordUserDto findByEmailDto(String email);
	
	User findByPassword(String password);
	
	Long getUserById(Long userId);
}
