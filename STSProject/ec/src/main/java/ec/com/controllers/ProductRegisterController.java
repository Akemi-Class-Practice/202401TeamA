package ec.com.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ec.com.models.AdminEntity;
import ec.com.services.ProductService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/product")
public class ProductRegisterController {

	@Autowired
	private ProductService productService;

	@Autowired
	private HttpSession session;

	@GetMapping("/register")
	public String getProductRegisterPage() {
		return "/product-login.html";
	}

	
	//セッションからログイン指定している人の情報変数に格納Admin
	//もし、Admin==null
	//ログイン画面にリダイレクトする
	@PostMapping("/register")													//Integer
	public String registerProduct(@RequestParam String productName, @RequestParam String productPrice,
			 @RequestParam String productDetail,@RequestParam MultipartFile productImg, Date registerDate,
			Model model) {
		AdminEntity admin = (AdminEntity)session.getAttribute("admin");
		if (admin == null) {
			return "redirect:/admin/login";
		} else {
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(registerDate)
					+ productImg.getOriginalFilename();
			try {
				Files.copy(productImg.getInputStream(), Path.of("src/main/resources/static/product-img/" + fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			// もし、登録が成功したら、商品登録にリダイレクト
			// そうでない場合は、商品ログイン画面にとどまる
			if (productService.productRegisterCheck(productName,productPrice,productDetail,
												fileName,registerDate,admin)) {
				return "redirect:/admin/product-register";
			}else {
				return "/admin/product-login.html";
			}
		}
	}
}
