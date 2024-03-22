package ec.com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.models.TransactionHistoryEntity;
import ec.com.models.TransactionItemEntity;

public interface TransactionRepository extends JpaRepository<TransactionHistoryEntity, Long> {

	

	
	
}
