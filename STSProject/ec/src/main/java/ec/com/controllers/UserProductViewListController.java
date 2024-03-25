package ec.com.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ec.com.models.ProductEntity;
import ec.com.models.UserEntity;
import ec.com.services.ProductService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/product")
public class UserProductViewListController {
	@Autowired
	private ProductService productService;

	//商品一覧
	@GetMapping("/viewlist")
	public String getUserProductViewList(Model model) {
			List<ProductEntity> userProductList = productService.userProductList();
			model.addAttribute("userProductList",userProductList);
			return "homepage.html";
		
	}
		
}
