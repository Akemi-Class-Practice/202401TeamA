package ec.com.controlles;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import ec.com.services.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRegisterControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@BeforeEach
	public void prepareData() {
		when(userService.createUser(eq("123"),eq("123"),eq("test@test.com"))).thenReturn(true);
		when(userService.createUser(eq("123"),eq("existingPassword"),eq("test@test.com"))).thenReturn(false);
		
	}
	
	@Test
	public void testUserRegisterPage_Suceed()throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.get("/user/register");
		mockMvc.perform(request)
		//.andExpect(model().attributeDoesNotExist("error"))
		.andExpect(model().attribute("error",false))
		.andExpect(view().name("/user/user_register.html"));		
		
	}
	
	@Test
	public void testUserRegister_Succed()throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.post("/user/register")
				.param("username", "123")
				.param("userPassword","123")
				.param("userEmail","test@test.com");
		mockMvc.perform(request)
		.andExpect(redirectedUrl("/user/login"));
		verify(userService, times(1)).createUser(eq("123"), eq("123"), eq("test@test.com"));
	}
	
	@Test
	public void testUserRegister_Failed()throws Exception{
		RequestBuilder request= MockMvcRequestBuilders.post("/user/register")
				.param("username", "123")
				.param("userPassword", "existingPassword")
				.param("userEmail", "test@test.com");
		mockMvc.perform(request)
		.andExpect(model().attribute("error", true))
		.andExpect(view().name("/user/user_register.html"));
		verify(userService,times(1)).createUser(eq("123"), eq("existingPassword"), eq("test@test.com"));
		
	}

	
	
	
}
