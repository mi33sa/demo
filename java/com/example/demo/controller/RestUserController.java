package com.example.demo.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.SiteUser;
import com.example.demo.repository.SiteUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest")
public class RestUserController {

	private final SiteUserRepository siteUserRepository;

	@PutMapping("/update")
	public int siteuserUpdate(SiteUser user) {
		siteUserRepository.save(user);
		return 0;

	}


}
