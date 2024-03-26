package ec.com.controlles;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
	
	@Autowired
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
}
