package ec.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ec.com.models.ProductEntity;
import ec.com.models.UserEntity;
import ec.com.services.ProductService;
import ec.com.services.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/product")
public class UserProductDetailController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/detail/{productId}")
	private String getUserProductDetailpage(@PathVariable Long productId,Model model ) {
		UserEntity user = (UserEntity)session.getAttribute("user");
		if(user == null) {
			return login("/user/product/detail/"+productId);
		}else {
			ProductEntity product = productService.userProductDetail(productId);
			model.addAttribute("product",product);
			return "/user/UserProductDetail.html";
		}
	}
	
	//Loginページに飛ばすメソッド
	public String login(String url) {
		session.setAttribute("backLoginpage",url);
		return "redirect:/user/login"; 
	}
}
