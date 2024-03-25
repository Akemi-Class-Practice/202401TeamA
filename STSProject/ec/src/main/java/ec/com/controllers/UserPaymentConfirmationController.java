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
import ec.com.models.SubscriptionEntity;
import ec.com.models.TransactionItemEntity;
import ec.com.models.UserEntity;
import ec.com.services.ProductService;
import ec.com.services.SubscriptionService;
import ec.com.services.TransactionService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserPaymentConfirmationController {
	@Autowired
	private ProductService productService;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private HttpSession session;

	@Autowired
	private SubscriptionService subscriptionService;

	// 支払い確認画面
	@GetMapping("/payment/confirmation")
	public String getUserPaymentConfirmationPage(Model model) {
		// Sessionからカートの情報を取得する
		List<ProductEntity> cartList = (List<ProductEntity>) session.getAttribute("cart");

		System.out.println(cartList);

		model.addAttribute("cartList", cartList);

		return "/user/userpaymentconfirm.html";
	}

	// 支払い処理
	@PostMapping("/payment/proceed")
	public String proceedPayment(@RequestParam String creditCardNumber, @RequestParam String creditCardName,
			@RequestParam String expiryDate, @RequestParam String cvvNumber) {
		// 取得Cart的信息
		List<ProductEntity> cart = (List<ProductEntity>) session.getAttribute("cart");
		// 取得用戶的信息
		UserEntity user = (UserEntity) session.getAttribute("user");
		// 測試Cart是否為0，1
		// 0的話付款失敗，1的話付款成功
		if (cart == null) {
			return "redirect:/user/payment/confirmation";
		} else {
			// ここでソースコード
			// Serviceを使用して
			// 把history和所有的item的对象都存入数据库
			transactionService.transactionDataStore(cart, "card", user.getUserId());
			return "redirect:/user/payment/succeed";
		}
	}

	@GetMapping("/purchased/history")
	public String getPurchasedHistory(Model model) {
		// カートの情報を入れるためのListの宣言をする
		// List<ProductEntity> cart = (List<ProductEntity>)
		// session.getAttribute("cart");
		UserEntity user = (UserEntity) session.getAttribute("user");
		if (user == null) {
			return login("/user/purchased/history");
		} else {
			model.addAttribute("user", user);
			// 購入履歴を
			List<SubscriptionEntity> history = subscriptionService.getHistory(user.getUserId());
			model.addAttribute("historys", history);
			System.out.println(history);
			return "/user/userpurchasehistoryviewlist.html";		
		}
	}

	// Loginページに飛ばすメソッド
	public String login(String url) {
		session.setAttribute("backLoginpage", url);
		return "redirect:/user/login";

	}
}
