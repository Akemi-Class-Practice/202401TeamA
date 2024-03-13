package ec.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ec.com.models.AdminEntity;
import ec.com.services.AdminServices;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {
	
	@Autowired
	private AdminServices adminService;
	
	//To save userdata
	@Autowired
	private HttpSession session;
	
	@GetMapping("/login")
	public String getAdminLoginPage(Model model) {
		model.addAttribute("error",false);
		return "/admin/login-admin.html";
	}
	
	//AdminServiceからAdmin名とパスワードをチェック
	//Nullでしたら、AdminLogin画面に止まる
	//そうではない、ProductRegister画面にリダイレクト
	@PostMapping("/login")
	public String login(@RequestParam String adminName,@RequestParam String adminEmail,
						@RequestParam String adminPassword,@RequestParam Integer deleteFlag,Model model) {
		
		AdminEntity admin = adminService.adminCheckLogin(adminName,adminPassword);
		if (admin == null) {
			return "/admin/login-admin.html";
		}else {
			session.setAttribute("admin", admin);
			return "redirect:/admin/product/register";
		}
		
	}
	
	
}
