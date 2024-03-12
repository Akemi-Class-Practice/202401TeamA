package ec.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/login")
	public String getAdminLoginPage(Model model) {
		model.addAttribute("error",false);
		return "admin/login-admin.html";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String adminName,@RequestParam String adminEmail,
						@RequestParam String password,@RequestParam Integer deleteFlag,Model model) {
		
		AdminEntity admin = adminService.adminLogin(adminName,password);
		if (admin == null) {
			return "admin_login.html";
		}else {
			session.setAttribute("admin", admin);
			return "refirect:/admin/product/register";
		}
		
	}
	
	
}
