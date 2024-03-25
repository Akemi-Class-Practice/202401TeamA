package ec.com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.models.ProductEntity;
import ec.com.models.TransactionHistoryEntity;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistoryEntity, Long> {

	TransactionHistoryEntity save (TransactionHistoryEntity transactionHistoryEntity);
	
	TransactionHistoryEntity findByHistoryId (TransactionHistoryEntity transactionHistoryEntity); 
}
