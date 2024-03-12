package ec.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.models.AdminEntity;
import ec.com.repositories.AdminRepository;

@Service
public class AdminServices {

	@Autowired
	private AdminRepository adminRepo;
	
	//管理者番号登録の部分
	public boolean createAdmin(String adminName, String adminEmail,String adminPassword ) {
	if(adminRepo.findByAminName(adminName) == null) {
		adminRepo.save(new AdminEntity(adminName,adminEmail,adminPassword,0));
		return true;
	}else {
		return false;
	}
  }
	//管理者のログインチェック
	public AdminEntity adminCheckLogin(String adminName,String adminPassword) {
		AdminEntity adminEntity = adminRepo.findByAminNameAndAdminPassword(adminName,adminPassword);
	}
	
}
