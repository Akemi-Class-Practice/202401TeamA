package ec.com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.models.TransactionItemEntity;

public interface TransactionItemRepository extends JpaRepository<TransactionItemEntity, Long> {

	TransactionItemEntity save(TransactionItemEntity transactionItemEntity);
}
