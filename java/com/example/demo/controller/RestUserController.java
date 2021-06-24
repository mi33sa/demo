package com.example.demo.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		Optional<SiteUser> userBefore = siteUserRepository.findById(user.getId());
		userBefore.ifPresent(u -> {
			user.setPassword(u.getPassword());
			siteUserRepository.save(user);
		});

		return 0;

		/*		SiteUser userBefore = siteUserRepository.findByUsername(user.getUsername());
				if(Objects.nonNull(userBefore)) {
					user.setPassword(userBefore.getPassword());
					siteUserRepository.save(user);

					return 0;

				}else {
					return 1;
				}*/

	}

	@DeleteMapping("/delete/{id}")
	public int siteuserDelete(@PathVariable("id") Long id) {
		siteUserRepository.deleteById(id);

		return 0;
	}

}
