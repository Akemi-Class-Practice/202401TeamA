package ec.com.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ec.com.models.ProductEntity;
import ec.com.models.TransactionItemEntity;
import ec.com.models.UserEntity;
import ec.com.services.ProductService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserPaymentConfirmationController{
	@Autowired
	private ProductService productService;
	
	@Autowired
	private HttpSession session;
	
	//支払い確認画面
	@GetMapping("/payment/confirmation")
	public String getUserPaymentConfirmationPage (Model model) {
		return "/user/userpaymentconfirm.html";
	}
	
	
	//支払い処理
	@PostMapping("/payment/proceed")
	public String proceedPayment(@RequestParam String creditCardNumber,@RequestParam String creditCardName,@RequestParam String expiryDate,@RequestParam String cvvNumber) {
		//取得Cart的信息
		List<ProductEntity> cart = (List<ProductEntity>) session.getAttribute("cart");
		//取得用戶的信息
		UserEntity user = (UserEntity) session.getAttribute("user");
		//測試Cart是否為0，1
		//0的話付款失敗，1的話付款成功
		if (cart == null) {
			return "redirect:/user/payment/confirmation";
		}else {
			//做一個HistoryService
			//把所有的Item創建出來，存在數據庫（History會包含3-4個Item）
			
			return "redirect:/user/payment/succeed";
		}
	}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	

			
	
	

