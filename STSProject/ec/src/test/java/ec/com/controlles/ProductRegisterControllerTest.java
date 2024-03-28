package ec.com.controlles;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.RequestBuilder;

import ec.com.models.AdminEntity;
import ec.com.models.ProductEntity;
import ec.com.services.ProductService;
import jakarta.servlet.http.HttpSession;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductRegisterControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	private MockHttpSession session;
	
	private MockHttpSession emptySession;

	@BeforeEach
	public void prepareData() {
		// 設一個Admin對象
		AdminEntity admin = new AdminEntity();
		admin.setAdminId(1L);
		admin.setAdminName("123");

		when(productService.productRegisterCheck(any(), any(), any(), any(), any(), any())).thenReturn(true);
		//when(productService.productList(any())).thenReturn(null);
		//when(productService.productRegisterCheck(eq(null), any(), any(), any(), any(), any())).thenReturn(false);
		when(productService.productRegisterCheck(eq("PC1"), any(), any(), any(), any(), any())).thenReturn(false);
		
		//這是空的Session
		emptySession=new MockHttpSession();

		// 存一個Admin對象到Session裏
		session = new MockHttpSession();
		session.setAttribute("admin", admin);
	}

	@Test
	public void testGetProductRegisterPage_Succeed() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/admin/product/register");

		mockMvc.perform(request)

		.andExpect(view().name("/admin/product-login.html"));
	}
	

	// 商品名が正しければ”/admin/product/register”へのPOSTリクエスト作成する
	@Test
	public void testProductRegister_differentProductName_Succeed() throws Exception {
		// 創建照片對象
		String fileName = "test-image.jpg";
		MockMultipartFile productImg = new MockMultipartFile("productImg", fileName, "image/jpeg", new byte[0]);

		RequestBuilder request = MockMvcRequestBuilders.multipart("/admin/product/register").file(productImg)
				.param("productName", "")
				.param("productPrice", "0")
				.param("productDetail", "")
				.param("registerDate", "2024-03-27")
				.session(session);
		mockMvc.perform(request)
		.andExpect(redirectedUrl("/admin/product/listview"));

	}

	// 商品名が正しくなければ”/admin/product/register”へのPOSTリクエスト作成する
	@Test
	public void testProductRegister_sameProductName_Failed()throws Exception{
		
		//創建照片對象
		String fileName = "test-image.jpg";
		MockMultipartFile productImg = new MockMultipartFile("productImg", fileName, "image/jpeg", new byte[0]);
	
		//テスト実行
		RequestBuilder request=MockMvcRequestBuilders.multipart("/admin/product/register")
				.file(productImg)
				.param("productName", "PC1")
				.param("productPrice","0")
				.param("productDetail", "")
				.param("registerDate", "2024-03-27")
				.session(session);
		mockMvc.perform(request)
		.andExpect(view().name("/admin/product-login.html"));
	}
	
	@Test
	public void testProductRegister_nullAdminName_Failed()throws Exception{
		
		String fileName = "test-image.jpg";
		MockMultipartFile productImg = new MockMultipartFile("productImg", fileName, "image/jpeg", new byte[0]);

		RequestBuilder request=MockMvcRequestBuilders.multipart("/admin/product/register")
				.file(productImg)
				.param("productName", "PC1")
				.param("productPrice","0")
				.param("productDetail", "")
				.param("registerDate", "2024-03-27")
				.session(emptySession);
		mockMvc.perform(request)
		.andExpect(redirectedUrl("/admin/login"));
		
	}

}
