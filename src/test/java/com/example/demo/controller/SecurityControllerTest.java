package com.example.demo.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.model.SiteUser;
import com.example.demo.util.Role;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class SecurityControllerTest {
	
	@Autowired
	MockMvc mockMvc;

	@Test
	@DisplayName("登録エラーがある時、エラー表示することを期待")
	void whenThereIsResistrationError_expectToSeeErrors() throws Exception{
		mockMvc
			//リクエストの実行
			.perform(
				post("/register")
				.flashAttr("user", new SiteUser())
				.with(csrf())
			)
			//エラーがあることを検証
			.andExpect(model().hasErrors())
			//指定したhtmlを表示しているか検証
			.andExpect(view().name("register"));
	}
	
	@Test
	@DisplayName("管理者ユーザとして登録する時、成功することを期待")
	void whenResisteringAsAdminUser_expectToSucceed() throws Exception{
		SiteUser user = new SiteUser();
		user.setUsername("管理者ユーザ");
		user.setPassword("password");
		user.setEmail("admin@example.com");
		user.setGender(0);
		user.setAdmin(true);
		user.setRole(Role.ADMIN.name());
		
		mockMvc.perform(post("/register")
			.flashAttr("user", user).with(csrf()))
			//エラーがないか
			.andExpect(model().hasNoErrors())
			//指定したurlにリダイレクトするか
			.andExpect(redirectedUrl("/login?register"))
			//ステータスが302であることを検証
			.andExpect(status().isFound());
	}
	
	@Test
	@DisplayName("管理者ユーザでログイン時、ユーザ一覧を表示することを期待")
	@WithMockUser(username = "admin",roles = "ADMIN")
	void whenLoggedInAsAdminUser_expectToSeeListOfUsers() throws Exception{
		mockMvc.perform(get("/admin/list"))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("ユーザ一覧")))
		.andExpect(view().name("list"));
	}

}
