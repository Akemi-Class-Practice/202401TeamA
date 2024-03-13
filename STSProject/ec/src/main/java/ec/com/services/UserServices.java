package ec.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.models.UserEntity;
import ec.com.repositories.UserRepository;

@Service
public class UserServices {

	@Autowired
	private UserRepository userRepo;
	
	//登録の部分
	public boolean createUser(String userName,String userPassword,String userEmail) {
		if(userRepo.findByUserEmail(userEmail) == null) {
		userRepo.save(new UserEntity(userName,userPassword,userEmail,0, null));
		return true;
		}else {
			return false;
		}
	}
	
	//ログインチェック
	public UserEntity userCheckLogin(String userName,String userPassword) {
		UserEntity userEntity = userRepo.findByUserNameAndUserPassword(userName,userPassword);
		if(userEntity == null) {
			return null;
		}else {
			return userEntity;
		}
	}
	
}
