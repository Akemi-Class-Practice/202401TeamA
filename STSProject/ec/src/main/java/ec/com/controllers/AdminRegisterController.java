package ec.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ec.com.services.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminRegisterController {
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/register")
		public String getAdminRegisterPage(Model model) {
		//Tymeleafで判断する
		//もし登録がエラー＝FalseでRegisterAdminに止まる
			model.addAttribute("error",false);
			return "/admin/register-admin.html";
		
	}
	
	@PostMapping("/register")
	public String login(@RequestParam String adminName,@RequestParam String adminEmail,
						@RequestParam String adminPassword,Model model) {

//	AdminEntity admin = adminService.adminRegister(adminName,password);
//	If the condition is not null then able to register else return back to register.html
	if (adminService.createAdmin(adminName,adminEmail,adminPassword)) {
		//redirect 是參考想轉去別的Controller
		return "redirect:/admin/login";
	}else {
		model.addAttribute("error",true);
		return "/admin/register-admin.html";
	}
	
	  }
	}
