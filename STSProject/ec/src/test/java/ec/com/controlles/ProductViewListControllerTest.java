package ec.com.controlles;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.Matchers.*;
import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import ec.com.models.AdminEntity;
import ec.com.models.ProductEntity;
import ec.com.services.ProductService;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductViewListControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductService productService;
	
	private MockHttpSession session;
	private MockHttpSession emptySession;
	
	@BeforeEach
	public void Data() {
		//listを作成
		ProductEntity productEntity = new ProductEntity();
		//
		productEntity.setProductId(1L);
		productEntity.setProductName("PC1");
		productEntity.setProductPrice(1234);
		productEntity.setProductImage("PC1");
		productEntity.setRegisterDate(Date.valueOf("2024-03-28"));//"2024-03-28"
		productEntity.setDeleteFlg(0);
						//商品のリスト
		List<ProductEntity>productList = List.of(productEntity);
		when(productService.productList(1L)).thenReturn(productList);
		
		emptySession = new MockHttpSession();
		
		AdminEntity admin = new AdminEntity();
		admin.setAdminId(1L);
		admin.setAdminName("123");
		
		session = new MockHttpSession();
		session.setAttribute("admin", admin);
	}
	
	@Test
	public void testGetProductViewListPage_NotInfo_Failed() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.get("/admin/product/listview")
											.session(emptySession);
		
		mockMvc.perform(request)
		.andExpect(redirectedUrl("/admin/login"));
	}
	
	@Test
	public void testGetProductViewListPage_Info_Succeed() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.get("/admin/product/listview")
				.session(session);
				mockMvc.perform(request).andExpect(view().name("/admin/product.html"))
				.andExpect(model().attribute("productList", hasSize(1)));
	}
}
