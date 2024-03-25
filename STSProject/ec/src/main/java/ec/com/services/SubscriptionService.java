package ec.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.models.SubscriptionEntity;
import ec.com.repositories.SubscriptionRepository;

@Service
public class SubscriptionService {
@Autowired
private SubscriptionRepository subscriptionRepo;

//購入履歴の取得チェック
public List<SubscriptionEntity> getHistory(Long userId){
	if(userId==null) {
		return null;
	}else {
		return subscriptionRepo.findByUserId(userId);
	}
}
}
