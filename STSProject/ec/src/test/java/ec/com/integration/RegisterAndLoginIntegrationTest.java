package ec.com.integration;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc

public class RegisterAndLoginIntegrationTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testRegisterAndLogin_NewUser_Succeed()throws Exception{
		//登録ページを表示させるテスト
		RequestBuilder request = MockMvcRequestBuilders
				.get("/user/register");
		mockMvc.perform(request)
		//UserRegisterController 26
		.andExpect(model().attribute("error",false))
		.andExpect(view().name("/user/user_register.html"));
	
	
		// 登録する
		request = MockMvcRequestBuilders
					.post("/user/register")
					.param("username", "Alice")
					.param("userPassword", "1234")
					.param("userEmail", "test@test.com");
		
		mockMvc.perform(request)
		.andExpect(redirectedUrl("/user/login"));
		
		//ログイン処理
		request = MockMvcRequestBuilders
				.post("/user/login")
				.param("username", "Alice")
				.param("password", "1234");
		mockMvc.perform(request)
		//UserLoginController 43
		.andExpect(redirectedUrl("/user/product/viewlist"));
		
	}
		//パスワードが間違っている場合のテスト
	@Test
	public void testRegisterAndLogin_WrongPassword_Failed()throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.get("/user/login");
		mockMvc.perform(request)
		//UserLoginController 26 & 27
		.andExpect(model().attribute("error",false))
		.andExpect(view().name("/user/user_login.html"));
		
		//ログイン失敗処理
		request = MockMvcRequestBuilders
				.post("/user/login")
				.param("username", "Alice")
				.param("password", "WrongPassword1234");
		mockMvc.perform(request)
		//UserLoginController 38
		.andExpect(view().name("/user/user_register.html"));
			
	}
		//ユーザーネームを間違って失敗
	@Test
	public void testRegisterAndLogin_WrongUsername_Failed()throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.get("/user/login");
		mockMvc.perform(request)
		//UserLoginController 26 & 27
		.andExpect(model().attribute("error",false))
		.andExpect(view().name("/user/user_login.html"));
		
		request = MockMvcRequestBuilders
				.post("/user/login")
				.param("username", "AliceWrongName")
				.param("password", "1234");
		mockMvc.perform(request)
		//UserLoginController 38
		.andExpect(view().name("/user/user_register.html"));
}	
	//既に登録したユーザーがいる場合、登録が失敗する場合テスト
	@Test
	public void testRegisterAndLogin_ExistingUsername_Failed()throws Exception{
		//1 登録ページ表示させる
		RequestBuilder request = MockMvcRequestBuilders
				.get("/user/register");
		mockMvc.perform(request)
		//UserRegisterController 26
		.andExpect(model().attribute("error",false))
		.andExpect(view().name("/user/user_register.html"));
	
	
		// 登録する
		request = MockMvcRequestBuilders
					.post("/user/register")
					.param("username", "Bob")
					.param("userPassword", "bob1234")
					.param("userEmail", "bob@test.com");
		
		mockMvc.perform(request)
		.andExpect(redirectedUrl("/user/login"));
		
		//2 登録ページ表示させる
		request = MockMvcRequestBuilders
				.get("/user/register");
		mockMvc.perform(request)
		//UserRegisterController 26
		.andExpect(model().attribute("error",false))
		.andExpect(view().name("/user/user_register.html"));
		
		//3 登録処理
		request = MockMvcRequestBuilders
							.post("/user/register")
							.param("username", "Bob2")
							.param("userPassword", "12341234")
							.param("userEmail", "bob@test.com");
				
		mockMvc.perform(request)
		.andExpect(model().attribute("error", true))
		.andExpect(view().name("/user/user_register.html"));
	
	}
	
	
}