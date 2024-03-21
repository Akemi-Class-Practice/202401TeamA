package ec.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ec.com.services.ProductService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserPaymentSucceedController {

	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private HttpSession session;
	
	//支払い成功画面
	@GetMapping("/payment/succeed")
	public String succeedPage(Model model) {
		return"/user/userpaymentfinished.html";
	}
}
