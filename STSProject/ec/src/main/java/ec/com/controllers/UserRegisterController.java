package ec.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ec.com.services.UserService;
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
		return "/user/user_register.html";
	}
	
	//もしUserEntityでUsernameとPasswordをチェック
	//Nullでしたら、userlogin画面へ
	//そうではない、userRegister画面に止まる
	@PostMapping("/register")
	public String registerUserAccount(@RequestParam String username, @RequestParam String userPassword,@RequestParam String userEmail, Model model) {
		if(userService.createUser(username,userPassword,userEmail)) {
			//Domain
			return "redirect:/user/login";
		}else {
			model.addAttribute("error",true);
			//文件需要Html
			return "/user/user_register.html";
		}
	}
}
