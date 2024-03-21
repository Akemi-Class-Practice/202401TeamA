package ec.com.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TransactionItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long prderId;
	
	private Long productId;
	
	private Long historyId;
	
	
	//コンストラクタ
	public TransactionItemEntity() {
		
	}

	public TransactionItemEntity(Long prderId, Long productId, Long historyId) {
		this.prderId = prderId;
		this.productId = productId;
		this.historyId = historyId;
	}

	
	//カプセル化　ゲッターとセッター
	public Long getPrderId() {
		return prderId;
	}

	public void setPrderId(Long prderId) {
		this.prderId = prderId;
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
