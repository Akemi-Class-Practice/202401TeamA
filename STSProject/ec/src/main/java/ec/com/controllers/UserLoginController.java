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
public class UserLoginController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/login")
	private String getUserLoginPage(Model model) {
		model.addAttribute("error",false);
		return "/user_login.html";

}	
	//UserServicesからUser名とUserPasswordをチェック
	//Nullでしたら、UserRegister画面に戻る
	//そうじゃない場合、UserLogin画面に止まる
	@PostMapping("/login")
	private String login(@RequestParam String username,@RequestParam String password,Model model) {
		
		UserEntity user = userService.userLogin(userName,userPassword));
	     if (user == null) {
	    	 return "/user_register.html";
	     }else {
	    	 session.setAttribute("user",user);
	    	 return "redirect:/user_login.html";
	     }
	}
}