package com.SIDA.UMKM.service;

import com.SIDA.UMKM.entities.User;

public interface AuthenticationService {

	public User findUserAndPassword(String username, String password);
}
