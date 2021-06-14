package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.model.SiteUser;
import com.example.demo.repository.SiteUserRepository;
import com.example.demo.util.Role;

@SpringBootTest
@Transactional
class UserDetailsServiceImpTest {
	
	@Autowired
	SiteUserRepository repository;
	
	@Autowired
	UserDetailServiceImpl service;

	@Test
	@DisplayName("ユーザ名が存在する時、ユーザ詳細を取得することを期待します")
	void whenUsernameExists_expectToGetUserDetails() {
		//arrange(準備）
		SiteUser user = new SiteUser();
		user.setUsername("原田");
		user.setPassword("password");
		user.setEmail("testtest@examplea.com");
		user.setRole(Role.USER.name());
		repository.save(user);
		
		//act（実行）
		UserDetails actual = service.loadUserByUsername("原田");
		
		//assert（検証）
		assertEquals(user.getUsername(), actual.getUsername());

	}
	
	@Test
	@DisplayName("ユーザ名が存在しない時、例外をスローします")
	void whenUsernameDoesNotExist_throwException() {
		//act&assert
		assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername("武田"));
	}

}
