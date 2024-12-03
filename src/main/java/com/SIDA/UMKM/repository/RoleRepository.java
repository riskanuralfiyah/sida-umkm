package com.SIDA.UMKM.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SIDA.UMKM.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByName(String name);
}
