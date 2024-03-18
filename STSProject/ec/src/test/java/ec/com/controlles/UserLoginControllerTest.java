package ec.com.controlles;

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
		UserEntity userEntity = new UserEntity();
		when(userService.userCheckLogin(any(),any())).thenReturn(null);
		when(userService.userCheckLogin(any(),any())).thenReturn(userEntity);
	}
	
	@Test
	public void testGetLoginPage_Succeed()throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.get("/user/user_login");
		
		mockMvc.perform(request).andExpect(view().name("user_login"));
	}

	@Test
	public void testLogin_Succeeful() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.post("/user/user_login")
								.param("","")
								.param("","");
		
		MvcResult result = mockMvc.perform(request).andExpect(redirectedUrl("")).andReturn();
		HttpSession session =  result.getRequest().getSession();
		
		UserEntity loggedInUser = (UserEntity) session.getAttribute("UserInfo");
		assertNotNull(loggedInUser);
		assertEquals("",loggedInUser.getUserName());
		assertEquals("",loggedInUser.getUserPassword());
		
	}

	@Test
	public void testLogin_WrongUserName() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.post("/user/user_login")
								.param("","")
								.param("","");
		
		MvcResult result = mockMvc.perform(request).andExpect(view().name("User_login.html"))
													.andReturn();
		
		HttpSession session =  result.getRequest().getSession();
		
		UserEntity loginUser = (UserEntity) session.getAttribute("UserInfo");
		assertNotNull(loginUser);		
	}
	
	@Test
	public void testLogin_WrongUserPassword() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.post("/user/user_login")
								.param("","")
								.param("","");
		
		MvcResult result = mockMvc.perform(request).andExpect(view().name("User_login.html"))
													.andReturn();
		
		HttpSession session =  result.getRequest().getSession();
		
		UserEntity loginUser = (UserEntity) session.getAttribute("UserInfo");
		assertNotNull(loginUser);		
	}
}


