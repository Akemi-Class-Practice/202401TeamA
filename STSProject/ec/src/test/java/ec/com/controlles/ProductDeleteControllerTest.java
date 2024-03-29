package ec.com.controlles;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.RequestBuilder;

import ec.com.models.AdminEntity;
import ec.com.services.ProductService;
import jakarta.servlet.http.HttpSession;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductDeleteControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;
	
	private MockHttpSession session;

	
		
	@Test
	public void testGetProductRegisterPage_Succeed()throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.get("/admin/product/delete/1");
		
		mockMvc.perform(request)
		
		.andExpect(view().name("/admin/product-delete.html"));
				
	}
}
