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
import ec.com.models.UserEntity;
import ec.com.services.ProductService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class CartController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private HttpSession session;
	
	//カートに追加
	@PostMapping("/cart/add-product")
	public String addCart(@RequestParam Long productId) {
		//もし、CartのsessionがNullだったら(初めてカートに商品を入れるとき）
		//カートの情報を入れるためのListの宣言をする
		//商品の情報をListに追加する
		//SessionにListの内容をセットする
		if (session.getAttribute("cart")== null) {
			List<ProductEntity> cartList = new ArrayList<ProductEntity>();
			//商品の情報を取得する
			ProductEntity product = productService.userProductDetail(productId);
			cartList.add(product);
			session.setAttribute("cart", cartList);
			return "redirect:/user/shopping-cart";
		}else {//2回目のカート処理
			//Sessionからカートの情報を取得する。
			List<ProductEntity> cartList= (List<ProductEntity>) session.getAttribute("cart");
			//商品の情報を取得する
			ProductEntity product = productService.userProductDetail(productId);
			cartList.add(product);
			return "redirect:/user/payment/confirmation";
		}
	}
	//カート一覧
	@GetMapping("/shopping-cart")
	public String getCartListPage(Model model) {
		//Sessionからカートの情報を取得する
		List<ProductEntity> cartList= (List<ProductEntity>) session.getAttribute("cart");
		model.addAttribute("cartList",cartList);
		//Sessionからユーザーの情報を取得する
		UserEntity user = (UserEntity) session.getAttribute("user");
		model.addAttribute("user",user);
		//今日の日付を取得する
		LocalDate today = LocalDate.now();
		model.addAttribute("today",today);
		return "/user/usershoppingcart.html";
	}
	
	//カートの中身を削除
	@PostMapping("cart/delete-product")
	public String deleteProductCart(@RequestParam Long productId) {
		//Sessionからカートの情報を取得する
		List<ProductEntity> cartList= (List<ProductEntity>) session.getAttribute("cart");
		//for文を使ってカートの中身を1つずつ確認する
		//もし、セッション（カートの中）に入っているproductIdが、@RequestParamで受け取ったproductIdと等しい
		//場合はリストの中から商品の情報を削除する
		for(int i = 0; i<cartList.size(); i++) {
			if(cartList.get(i).getProductId().equals(productId)) {
				cartList.remove(i);
				break;
			}
		}
		return "redirect:/user/shopping-cart";
	}
	
}





















