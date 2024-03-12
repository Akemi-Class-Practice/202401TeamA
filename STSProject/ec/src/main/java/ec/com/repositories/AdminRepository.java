package ec.com.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.models.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
	//Register new admin
	AdminEntity save(AdminEntity adminEntity);
	
	//Find by admiID
	AdminEntity findByAdminId (Long Id);
	
	//Find by adminName
	AdminEntity findByAminName(String adminName);
}
