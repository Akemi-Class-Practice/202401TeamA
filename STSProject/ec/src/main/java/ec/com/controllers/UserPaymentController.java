package ec.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ec.com.services.ProductService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserPaymentController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private HttpSession session;
	
	//お支払い画面
	@PostMapping("/payment/confirmation")
	public String paymentPege(@RequestParam Long userId) {
		if() {
			
		}
	}

}
