package ec.com.integration;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.queryParam;
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

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterAndLoginIntegrationTest {

	@Autowired
	private MockMvc movMvc;
	
	@Test
	//名前とパスワードが正しい場合
	public void testRegiserAndLogin_NewUser_suceed()throws Exception{
		//登録ページを表示させるテスト
		RequestBuilder request = MockMvcRequestBuilders
				//UserRegisterControllerのURL
				.get("/user/register");
		movMvc.perform(request)
		//UserRegisterControllerの26行目のエラー文をテスト
		.andExpect(model().attribute("error", false))
		//UserRegisterControllerの27行目のをテスト
		.andExpect(view().name("/user/user_register.html"));
		
		//登録処理
		//UserRegisterControllerの33行目PostMappingの方
		request = MockMvcRequestBuilders
				.post("/user/register")
				//34行目
				.param("username", "Alice")
				.param("userPassword", "1234")
				.param("userEmail", "test@test.com");
		//37行目
		movMvc.perform(request)
		.andExpect(redirectedUrl("/user/login"));	
		
		//ログイン処理 
		//UserLoginController
		//UserLoginControllerの33行目PostMappingの方
		request = MockMvcRequestBuilders
				.post("/user/login")
				//34行目
				.param("username", "Alice")
				.param("password", "1234");
		//43行目
		movMvc.perform(request)
		.andExpect(redirectedUrl("/user/product/viewlist"));
	}
	
	@Test
	//パスワードが間違っている場合のテスト
	public void testRegiserAndLogin_WrongPassword_Fail()throws Exception{
		//登録ページを表示させるテスト
		RequestBuilder request = MockMvcRequestBuilders
		//UserLoginControllerのURL
				.get("/user/login");
		     movMvc.perform(request)
		//UserLoginControllerの26行目のエラー文をテスト
			 .andExpect(model().attribute("error", false))
		//UserLoginControllerの27行目のをテスト
			 .andExpect(view().name("/user/user_login.html"));
		     
		     //ログイン処理
		 	//UserLoginController
			//UserLoginControllerの33行目PostMappingの方
				request = MockMvcRequestBuilders
					.post("/user/login")
					//34行目
					.param("username", "Alice")
					.param("password", "WrongPassword1234");
					//38行目
					movMvc.perform(request)
					.andExpect(view().name("/user/user_register.html"));
	}
	
	@Test
	//名前が間違っている場合のテスト
	public void testRegiserAndLogin_AliceWrongName_Fail()throws Exception{
		//登録ページを表示させるテスト
		RequestBuilder request = MockMvcRequestBuilders
		//UserLoginControllerのURL
				.get("/user/login");
		     movMvc.perform(request)
		//UserLoginControllerの26行目のエラー文をテスト
			 .andExpect(model().attribute("error", false))
		//UserLoginControllerの27行目のをテスト
			 .andExpect(view().name("/user/user_login.html"));
		     
		     //ログイン処理
		 	//UserLoginController
			//UserLoginControllerの33行目PostMappingの方
				request = MockMvcRequestBuilders
					.post("/user/login")
					//34行目
					//テスト仕様書の方で確認
					.param("username", "AliceWrongName")
					.param("password", "1234");
					//38行目
					movMvc.perform(request)
					.andExpect(view().name("/user/user_register.html"));
	}
	
	//すでに登録したユーザーがいる場合、登録が失敗する場合テスト
	@Test
	public void testRegiserAndLogin_ExistingUsername_Fail()throws Exception{
		//登録ページを表示させるテスト
				RequestBuilder request = MockMvcRequestBuilders
						//UserRegisterControllerのURL
						.get("/user/register");
				movMvc.perform(request)
				//UserRegisterControllerの26行目のエラー文をテスト
				.andExpect(model().attribute("error", false))
				//UserRegisterControllerの27行目のをテスト
				.andExpect(view().name("/user/user_register.html"));
				
				//登録処理
				//UserRegisterControllerの33行目PostMappingの方
				request = MockMvcRequestBuilders
						.post("/user/register")
						//34行目
						//仕様書データを参考
						.param("username", "Bob")
						.param("userPassword", "bob1234")
						.param("userEmail", "bob@test.com");
				//37行目
				movMvc.perform(request)
				.andExpect(redirectedUrl("/user/login"));
		
				//二度目テスト
				//登録ページを表示させる
				request = MockMvcRequestBuilders
						//UserRegisterControllerのURL
						.get("/user/register");
				movMvc.perform(request)
				//UserRegisterControllerの26行目のエラー文をテスト
				.andExpect(model().attribute("error", false))
				//UserRegisterControllerの27行目のをテスト
				.andExpect(view().name("/user/user_register.html"));
		
				//登録処理
				//UserRegisterControllerの33行目PostMappingの方
				request = MockMvcRequestBuilders
						.post("/user/register")
						//34行目
						//仕様書データを参考
						//UserServiceの登録部分
						.param("username", "Bob2")
						.param("userPassword", "12341234")
						.param("userEmail", "bob@test.com");
				//39~41行目
				movMvc.perform(request)
				.andExpect(model().attribute("error", true))
				.andExpect(view().name("/user/user_register.html"));
	}
}
