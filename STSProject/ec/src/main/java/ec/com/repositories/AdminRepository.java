package ec.com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ec.com.models.AdmiEntity;

public interface AdminRepository extends JpaRepository<AdmiEntity, Long> {
	//Register new admi
	//AdmiEntity save(AdmiEntity admiEntity);
	
	//Find by admiID
	AdmiEntity findByAdmiID (Long Id);
}
