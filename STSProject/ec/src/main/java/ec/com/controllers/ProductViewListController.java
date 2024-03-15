package ec.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ec.com.services.ProductService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/product")
public class ProductViewListController {

	@Autowired
	private ProductService productService;

	
	@GetMapping("/listview")
	public String getAdminProductViewList() {
		return "/admin/product.html";
	}
	
	
}

