package ec.com.services;

import java.sql.Date;
import java.util.List;

import org.apache.naming.java.javaURLContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import ec.com.models.ProductEntity;
import ec.com.models.TransactionHistoryEntity;
import ec.com.models.TransactionItemEntity;
import ec.com.repositories.TransactionHistoryRepository;
import ec.com.repositories.TransactionItemRepository;
import ec.com.repositories.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionHistoryRepository transactionHistoryRepo;
	
	@Autowired
	private TransactionItemRepository transactionItemRepo;
	
	//購入履歴のデータ貯蔵
	public boolean transactionDataStore(List<ProductEntity> cart, String payment, Long userId) {
		Date now = new Date(System.currentTimeMillis());
		//商品値段合計算（求和公式或者积）
		int totalPrice = 0;
		for(ProductEntity product : cart) {
			totalPrice += product.getProductPrice();
		}
		TransactionHistoryEntity transactionHistoryEntity = transactionHistoryRepo.save(new TransactionHistoryEntity(
				now, totalPrice, payment, userId
				));

		//对象存入的循环
		for(ProductEntity product : cart) {
			//把商品ID取出来放入一个变量（productID）中
			Long productId = product.getProductId();
			//用id创建新的item并存入数据库
			transactionItemRepo.save(new TransactionItemEntity(productId, transactionHistoryEntity.getHistoryId()));
		}
		
		
		
		return true;
	}
		
}
	
