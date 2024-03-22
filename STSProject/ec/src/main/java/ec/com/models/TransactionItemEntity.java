package ec.com.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TransactionItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long orderId;
	
	private Long productId;
	
	private Long historyId;
	
	
	//コンストラクタ
	public TransactionItemEntity() {
		
	}

	public TransactionItemEntity(Long productId, Long historyId) {
		this.productId = productId;
		this.historyId = historyId;
	}

	
	//カプセル化　ゲッターとセッター
	public Long getOrderId() {
		return orderId;
	}

	public void setPrderId(Long prderId) {
		this.orderId = prderId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}

	
}
