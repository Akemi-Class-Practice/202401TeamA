package ec.com.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ec.com.models.KeyEntity;
import ec.com.models.SubscriptionEntity;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, KeyEntity> {
	
	@Query(value="select h.history_id,h.user_id,p.product_Id,p.product_name,p.product_price,p.product_image from transaction_history_entity h join transaction_item_entity i on h.history_id=i.history_id join product p on i.product_id=p.product_id where user_id=?1",nativeQuery = true)
	List<SubscriptionEntity>findByUserId(Long userId);
}
