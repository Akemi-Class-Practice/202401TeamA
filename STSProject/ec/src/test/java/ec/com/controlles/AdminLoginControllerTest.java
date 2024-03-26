package ec.com.controlles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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
		//括弧裏的變數參考Entity
		AdminEntity adminEntity = new AdminEntity("1234","test@test.com","1234",0);
		when(adminService.adminCheckLogin(eq("1234"), eq("1234"))).thenReturn(adminEntity);
		when(adminService.adminCheckLogin(eq(""), eq("1234"))).thenReturn(null);
		when(adminService.adminCheckLogin(eq("1234"), eq(""))).thenReturn(null);
		when(adminService.adminCheckLogin(eq(""), eq(""))).thenReturn(null);
	}

	@Test
	public void testAdminLoginPage_Succeed() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/admin/login");

		mockMvc.perform(request)
		//28行的測試
		.andExpect(model().attribute("error",false))
		.andExpect(view().name("/admin/login-admin.html"));
		
	}
	
	@Test
	public void testAdminLogout_Suceed()throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.get("/admin/logout");
		mockMvc.perform(request).andExpect(redirectedUrl("/admin/login"));
	}

	
	@Test
	public void testAdminLogin_Succeed() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/admin/login")
				.param("adminName", "1234").param("adminPassword", "1234");

		MvcResult result = mockMvc.perform(request)
				.andExpect(redirectedUrl("/admin/product/listview")).andReturn();
		HttpSession session = result.getRequest().getSession();

		AdminEntity loggedInAdmin = (AdminEntity) session.getAttribute("admin");
		assertNotNull(loggedInAdmin);
		assertEquals("1234", loggedInAdmin.getAdminName());
		assertEquals("1234", loggedInAdmin.getAdminPassword());
	}

	@Test
	public void testAdminLogin_WrongAdminName() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/admin/login")
		.param("adminName", "").param("adminPassword", "1234");
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(model().attribute("error",true))
				.andExpect(view().name("/admin/login-admin.html")).andReturn();
		HttpSession session = result.getRequest().getSession();
		
		AdminEntity loginAdmin = (AdminEntity) session.getAttribute("admin");
		assertNull(loginAdmin);
	}
	
	@Test
	public void testAdminLogin_WrongAdminPassword()throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.post("/admin/login")
				.param("adminName", "1234").param("adminPassword", "");
	
		MvcResult result = mockMvc.perform(request).andExpect(view().name("/admin/login-admin.html")).andReturn();
		HttpSession session = result.getRequest().getSession();
		
		AdminEntity loginAdmin = (AdminEntity) session.getAttribute("admin");
		//Login AdminNullですか、ないですか、そういうテストです。
		assertNull(loginAdmin);
	}
	
	@Test
	public void testAdminLogin_NullAdminNameAndNullAdminPassword()throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.post("/admin/login")
				.param("adminName", "").param("adminPassword", "");
		
		MvcResult result = mockMvc.perform(request).andExpect(view().name("/admin/login-admin.html")).andReturn();
		HttpSession session = result.getRequest().getSession();
		
		AdminEntity loginAdmin = (AdminEntity) session.getAttribute("admin");
		//Login AdminNullですか、ないですか、そういうテストです。
		assertNull(loginAdmin);
	
		
		
	}
	

}
