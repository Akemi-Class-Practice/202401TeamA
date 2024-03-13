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
@RequestMapping("/user")
public class UserRegisterController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/register")
	public String getUserRegisterPage(Model model) {
		model.addAttribute("error",false);
		return "/user_register.html";
	}
	
	//もしUserEntityでUsernameとPasswordをチェック
	//Nullでしたら、UserRegister画面に進む
	//そうではない、register画面にリダイレクト
	@PostMapping("/register")
	public String registerUserAccount(@RequestParam String userName, @RequestParam String userPassword,@RequestParam String userEmail, Model model) {
		if(userService.registerUserAccount(userName,userPassword,userEmail)) {
			return "user_register.html";
		}else {
			model.addAttribute("error",true);
			return "redirect:/user_register.html";
		}
	}
}
