package ec.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ec.com.models.AdminEntity;
import ecsite.com.services.ProductService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/product")
public class ProductDeleteController {

	@Autowired
	private ProductService productService;

	@Autowired
	private HttpSession session;

	//もしＡｄｍｉｎがＮｕｌｌな場合、ＡｄｍｉｎＬｏｇｉｎに戻る
	//もしＰｒｏｄｕｃｔＩＤが削除した場合、/admin/product-list/viewにもどる
	//そうではない、/admin/product/editに戻る
	@PostMapping("/product/delete")
	public String delete(@RequestParam Long productId) {
		AdminEntity admin = (AdminEntity) session.getAttribute("admin");
		if (admin == null) {
			return "redirect:/admin/login";
		} else {
			if (productService.deleteProduct(productId, admin)) {
				return "redirect/admin/product-list/view";
			} else {
				return "redirect:/admin/product/edit" + productId;
			}
		}
	}
}
