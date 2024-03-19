package ec.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ec.com.models.AdminEntity;
import ec.com.models.ProductEntity;
import ec.com.services.ProductService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/product")
public class ProductViewListController {

	@Autowired
	private ProductService productService;

	@Autowired
	private HttpSession session;
	
	@GetMapping("/listview")
	public String getAdminProductViewList(Model model) {
		AdminEntity admin = (AdminEntity)session.getAttribute("admin");
		if (admin == null) {
			return "redirect:/admin/login";
		}else {
			List<ProductEntity> productList = productService.productList(admin.getAdminId());
			model.addAttribute("productList",productList);
			return "/admin/product.html";
		}
		
		
	}
	
	
	//
	
}

