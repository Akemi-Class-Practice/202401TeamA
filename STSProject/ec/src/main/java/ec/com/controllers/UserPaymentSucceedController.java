package ec.com.controllers;

import java.time.LocalDate;
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
@RequestMapping("/user")
public class UserPaymentSucceedController {

	@Autowired
	private ProductService productService;

	@Autowired
	private HttpSession session;

	// 支払い成功画面
	@GetMapping("/payment/succeed")
	public String succeedPage(Model model) {
		// Sessionからカートの情報を取得する
		List<ProductEntity> cartList = (List<ProductEntity>) session.getAttribute("cart");

		model.addAttribute("cartList", cartList);

		//清空購物車
		session.removeAttribute("cart");
		
		return "/user/userpaymentfinished.html";
	}

}
