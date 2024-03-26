package ec.com.controlles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ec.com.models.AdminEntity;
import ec.com.models.UserEntity;
import ec.com.services.AdminService;
import jakarta.servlet.http.HttpSession;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminLoginControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AdminService adminService;

	// Admin登録の確認
	@BeforeEach
	public void prepareData() {
		AdminEntity adminEntity = new AdminEntity();
		when(adminService.adminCheckLogin(any(), any())).thenReturn(null);
		when(adminService.adminCheckLogin(any(), any())).thenReturn(adminEntity);
	}

	@Test
	public void testAdminLoginPage_Succeed() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/admin/login");

		mockMvc.perform(request).andExpect(view().name("/admin/login-admin.html"));
	}
	
	@Test
	public void testAdminLogout_Suceed()throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.get("/logout");
		mockMvc.perform(request).andExpect(view().name("/admin/login-admin.html"));
	}

	@Test
	public void testAdminLogin_Succeed() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/admin/login")
				.param("adminName", "1234").param("adminPassword", "1234");

		MvcResult result = mockMvc.perform(request)
				.andExpect(redirectedUrl("/admin/product/listview")).andReturn();
		HttpSession session = result.getRequest().getSession();

		AdminEntity loggedInAdmin = (AdminEntity) session.getAttribute("AdminInfo");
		assertNotNull(loggedInAdmin);
		assertEquals("", loggedInAdmin.getAdminName());
		assertEquals("", loggedInAdmin.getAdminPassword());
	}

	@Test
	public void testAdminLogin_WrongAdminName() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/admin/login")
		.param("", "").param("", "");
		
		MvcResult result = mockMvc.perform(request).andExpect(view().name("login-admin.html")).andReturn();
		HttpSession session = result.getRequest().getSession();
		
		AdminEntity loginAdmin = (AdminEntity) session.getAttribute("AdminInfo");
		assertNotNull(loginAdmin);
	}
	
	@Test
	public void testAdminLogin_WrongAdminPassword()throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.post("/admin/login")
				.param("", "").param("", "");
	
		MvcResult result = mockMvc.perform(request).andExpect(view().name("login-admin.html")).andReturn();
		HttpSession session = result.getRequest().getSession();
		
		AdminEntity loginAdmin = (AdminEntity) session.getAttribute("AdminInfo");
		assertNotNull(loginAdmin);
	}

}
