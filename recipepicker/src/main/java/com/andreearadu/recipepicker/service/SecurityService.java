package com.andreearadu.recipepicker.service;

public interface SecurityService {
	
	String findLoggedInUsername();

    void autoLogin(String username, String password);
}
