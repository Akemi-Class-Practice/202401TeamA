package ec.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ec.com.models.UserEntity;
import ec.com.services.UserService;
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
		return "/user/user_login.html";

}	
	//UserServicesからUser名とUserPasswordをチェック
	//Nullでしたら、UserRegister画面に戻る
	//そうじゃない場合、UserLogin画面に止まる
	@PostMapping("/login")
	private String login(@RequestParam String username,@RequestParam String password,Model model) {
									//UsernameとPasswordはどっちも間違えたらNullになります。
		UserEntity user = userService.userCheckLogin(username,password);
	     if (user == null) {
	    	 return "/user/user_register.html";
	     }else {
	    	 session.setAttribute("user",user);
	    	 String url = (String) session.getAttribute("backLoginpage");
	    	 if(url == null) {
	    		 return "redirect:/user/product/viewlist";
	    	 }else {
	    		 return"redirect:"+url;
	    	 }
	    	 
	     }
	}
	@GetMapping("/logout")
	public String userLogout() {
		session.invalidate();
		return "redirect:/user/login";
	}


}