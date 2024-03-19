package ec.com.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ec.com.models.AdminEntity;
import ec.com.models.ProductEntity;
import ec.com.services.ProductService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class ProductEditController {
	
	@Autowired
	private ProductService productService;

	@Autowired
	private HttpSession session;

	@GetMapping("/edit/{productId}")
										//URL中のデータを所得すること
	private String getProductEditPage(@PathVariable Long productId,Model model) {
		AdminEntity admin=(AdminEntity)session.getAttribute("admin");
		if(admin == null) {
			return "redirect:/login-admin"; 
		}else {
			ProductEntity product = productService.editPageCheck(admin.getAdminId(),productId);
			model.addAttribute("product",product);
			return "/admin/product-delete.html";
		}
		
	}
	
	//AdminセッションでログインしてNullでしたらLogin画面に止まる
	//そうではない場合、商品の写真編集
	@PostMapping("/edit")
	public String editProduct(@RequestParam Long productId,@RequestParam String productName,@RequestParam String productPrice, 
						@RequestParam String productDetail, @RequestParam MultipartFile productImg,
						@RequestParam Date registerDate,Model model){
		AdminEntity admin=(AdminEntity)session.getAttribute("admin");

		if (admin == null) {
			return "redirect:/login-admin";
		}else {
			//写真編集
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(registerDate)
					+ productImg.getOriginalFilename();
			try {
				Files.copy(productImg.getInputStream(), Path.of("src/main/resources/static/product-img/" + fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			//もし登録が成功でしたら、商品変更にリダイレクト
			//そうではない、商品ログイン画面に止まる
			if(productService.productUpdateCheck(productId,productName,productPrice,productDetail,
											fileName,registerDate,admin)) {
				return "redirect:/admin/product/listview";
			}else {
				return "/admin/product-login.html";
			}
		}
		
						
	}
}
