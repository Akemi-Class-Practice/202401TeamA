package ec.com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.models.AdminEntity;
import ec.com.models.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
	//Save new user
	UserEntity save(UserEntity userEntity);
	
	//Find by userId
	UserEntity findByUserId(Long userId);
	
	//Find by userEmail
	UserEntity findByUserEmail(String userEmail);
	
	//Find by userName and Email
	UserEntity findByUserNameAndUserPassword(String userName,String userPassword);
	
}
