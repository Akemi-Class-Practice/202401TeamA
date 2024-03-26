package ec.com.controlles;

import static org.mockito.ArgumentMatchers.eq;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import ec.com.models.UserEntity;
import ec.com.services.UserService;
import jakarta.servlet.http.HttpSession;

@SpringBootTest
@AutoConfigureMockMvc
public class UserLoginControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;
	
	
	//user 登録の確認
	@BeforeEach
	public void prepareDaa() {
		UserEntity userEntity = new UserEntity("123456","123456","usertest@test.com",0);
		when(userService.userCheckLogin(eq("123456"),eq("123456"))).thenReturn(userEntity);
		when(userService.userCheckLogin(eq(""),eq("123456"))).thenReturn(null);
		when(userService.userCheckLogin(eq("123456"),eq(""))).thenReturn(null);
	}
	
	@Test
	public void testGetLoginPage_Succeed()throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.get("/user/login");
		
		mockMvc.perform(request)
		.andExpect(view().name("/user/user_login.html"));
	}

	@Test
	public void testLogin_Succeeful() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.post("/user/login")
								.param("username","123456")
								.param("password","123456");
		
		MvcResult result = mockMvc.perform(request).andExpect(redirectedUrl("/user/product/viewlist")).andReturn();
		HttpSession session =  result.getRequest().getSession();
		
		UserEntity loggedInUser = (UserEntity) session.getAttribute("user");
		assertNotNull(loggedInUser);
		
	}

	@Test
	public void testLogin_WrongUserName() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.post("/user/login")
								.param("username","")
								.param("password","123456");
		
		MvcResult result = mockMvc.perform(request).andExpect(view().name("/user/user_register.html"))
													.andReturn();
		
		HttpSession session =  result.getRequest().getSession();
		
		UserEntity loginUser = (UserEntity) session.getAttribute("user");
		assertNull(loginUser);		
	}
	
	@Test
	public void testLogin_WrongUserPassword() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.post("/user/login")
								.param("username","123456")
								.param("password","");
		
		MvcResult result = mockMvc.perform(request).andExpect(view().name("/user/user_register.html"))
													.andReturn();
		
		HttpSession session =  result.getRequest().getSession();
		
		UserEntity loginUser = (UserEntity) session.getAttribute("user");
		assertNull(loginUser);		
	}
}


