package ec.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class AdminRegisterController {
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/register")
		public String getAdminRegisterPage(Model model) {
			model.addAttribute("error",false);
			return "/admin/register-admin.html";
		
	}
	
	@PostMapping("/register")
	public String login(@RequestParam String adminName,@RequestParam String adminEmail,
						@RequestParam String password,@RequestParam Integer deleteFlag,Model model) {

//	AdminEntity admin = adminService.adminRegister(adminName,password);
//	If the condition is not null then able to register else return back to register.html
	if (adminService.createAdminAccount(adminName,adminEmail,password) == null) {
		return "admin_register.html";
	}else {
		model.addAttribute("error",true);
		return "redirect:/admin/register-admin.html";
	}
	
	  }
	}
