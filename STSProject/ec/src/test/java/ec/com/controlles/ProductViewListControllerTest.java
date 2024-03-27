package ec.com.controlles;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import ec.com.models.ProductEntity;
import ec.com.services.ProductService;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductViewListControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductService productService;
	
	@BeforeEach
	public void Data() {
		//listを作成
		ProductEntity productEntity = new ProductEntity();
		//
		productEntity.setProductId(null);
		productEntity.setProductName("");
		productEntity.setProductPrice(123);
		productEntity.setProductImage("");
		productEntity.setRegisterDate(null);
		productEntity.setDeleteFlg(0);
		
		List<ProductEntity>productName = List.of(productEntity);
		when(productService.selectByProductId("")).thenReturn(productEntity);
	}
	
	@Test
	public void testGetProductViewListPage_NotInfo_Failed() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.get("/admin/login")
											.with(null);
	}
}
