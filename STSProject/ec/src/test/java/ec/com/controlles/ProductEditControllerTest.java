package ec.com.controlles;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.queryParam;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import ec.com.models.AdminEntity;
import ec.com.models.ProductEntity;
import ec.com.services.ProductService;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductEditControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	private MockHttpSession session;
	
	private MockHttpSession emptySession;
	
	private ProductEntity product;
	
	@BeforeEach
	public void prepareData() {
		AdminEntity admin = new AdminEntity();
		admin.setAdminId(1L);
		
		product = new ProductEntity();
		product.setProductId(1L);
		product.setProductName("PC1");
		product.setProductPrice(1234);
		product.setProductDetail("aaaa");
		product.setProductImage("PC1");
		product.setRegisterDate(Date.valueOf("2024-03-28"));
		product.setAdmin(admin);
		
		emptySession=new MockHttpSession();
		session = new MockHttpSession();
		session.setAttribute("admin", admin);
	
		when(productService.editPageCheck(any(), any())).thenReturn(null);
		when(productService.editPageCheck(any(), eq(1L))).thenReturn(product);
		
		when(productService.productUpdateCheck(any(), any(), any(), any(), any(), any(), any())).thenReturn(false);
		when(productService.productUpdateCheck(eq(1L), any(), any(), any(), any(), any(), any())).thenReturn(true);
		
		//when(productService.editPageCheck(eq(1L), eq(2L))).thenReturn(null);
		//when(productService.editPageCheck(eq(1L), eq(1L))).thenReturn(product);
	}
	
	
	
	@Test
	public void testGetProductEditPage_suceed()throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.get("/admin/edit/1")
			.session(session);
		mockMvc.perform(request)
		.andExpect(model().attribute("product", product))
		.andExpect(view().name("/admin/product-delete.html"));
	}
	
	@Test
	public void testGetProductEditPage_failed()throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/admin/edit/1")
				.session(emptySession);
		mockMvc.perform(request)		
		.andExpect(redirectedUrl("/login-admin"));
		
	}
	
	@Test
	public void testEditProduct_adminNull_failed()throws Exception{
		// 創建照片對象
		String fileName = "test-image.jpg";
		MockMultipartFile productImg = new MockMultipartFile("productImg", fileName, "image/jpeg", new byte[0]);
				
		RequestBuilder request = MockMvcRequestBuilders.multipart("/admin/edit")
				//參考COntroller的參數
				.file(productImg)
				.param("productId", "1")
				.param("productName", "PC1")
				.param("productPrice", "1234")
				.param("productDetail", "aaaa")
				.param("registerDate", "2024-03-28")
				.session(emptySession);
		mockMvc.perform(request)
		.andExpect(redirectedUrl("/login-admin"));
	
	}
	
	@Test
	public void testEditProduct_productIDNotExist_Failed()throws Exception{
		String fileName ="test-image.jpg";
		MockMultipartFile productImg = new MockMultipartFile("productImg", fileName, "image/jpeg", new byte[0]);
	
		RequestBuilder request = MockMvcRequestBuilders.multipart("/admin/edit")
				.file(productImg)
				.param("productId", "2")
				.param("productName", "PC1")
				.param("productPrice", "1234")
				.param("productDetail", "aaaa")
				.param("registerDate", "2024-03-28")
				.session(session);
		mockMvc.perform(request)
		.andExpect(view().name("/admin/product-login.html"));
	
	}
	
	@Test 
	public void testEditProduct_productIDExist_Succeed()throws Exception{
		String fileName ="test-image.jpg";
		MockMultipartFile productImg = new MockMultipartFile("productImg", fileName, "image/jpeg", new byte[0]);
		
		RequestBuilder request = MockMvcRequestBuilders.multipart("/admin/edit")
				.file(productImg)
				.param("productId", "1")
				.param("productName", "PC1")
				.param("productPrice", "1234")
				.param("productDetail", "aaaa")
				.param("registerDate", "2024-03-28")
				.session(session);
		
		mockMvc.perform(request)
		.andExpect(redirectedUrl("/admin/product/listview"));
	
	
	}
}
