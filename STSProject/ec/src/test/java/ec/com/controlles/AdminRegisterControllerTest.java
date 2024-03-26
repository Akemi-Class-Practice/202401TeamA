package ec.com.controlles;

import static org.mockito.ArgumentMatchers.eq;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.queryParam;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;


import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import ec.com.models.AdminEntity;
import ec.com.services.AdminService;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminRegisterControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AdminService adminService;
	
	@BeforeEach
	public void prepareData() {
		//AdminEntity adminEntity = new AdminEntity("123","test@test.com","123",0);
		when(adminService.createAdmin(eq("123"), eq("test@test.com"), eq("123") )).thenReturn(true);
		when(adminService.createAdmin( eq("123"), eq("test@test.com"), eq("existingPassword")  )).thenReturn(false);	
	}
	
	@Test
	public void testAdminRegisterPage_Succeed()throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.get("/admin/register");
		
		mockMvc.perform(request)
		.andExpect(model().attribute("error",false))
		.andExpect(view().name("/admin/register-admin.html"));	
	}
	
	@Test
	public void testAdminRegister_Succeed()throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.post("/admin/register")
			.param("adminName", "123")
			.param("adminEmail","test@test.com")
			.param("adminPassword","123");
		mockMvc.perform(request)
		.andExpect(redirectedUrl("/admin/login"));
		verify(adminService, times(1)).createAdmin(eq("123"), eq("test@test.com"), eq("123"));
	}
	
	@Test
	public void testAdminRegister_Failed()throws Exception{
		RequestBuilder request=MockMvcRequestBuilders.post("/admin/register")
				.param("adminName", "123")
				.param("adminEmail", "test@test.com")
				.param("adminPassword", "existingPassword");
		mockMvc.perform(request)
		.andExpect(model().attribute("error",true))
		.andExpect(view().name("/admin/register-admin.html"));
		verify(adminService, times(1)).createAdmin(eq("123"), eq("test@test.com"), eq("existingPassword"));
		.andExpect(view().name("/admin/register-admin.html"));
		
	}
}
