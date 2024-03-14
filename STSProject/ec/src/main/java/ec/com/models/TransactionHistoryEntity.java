package ec.com.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TransactionHistoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long historyId;
	
	private Long userId;
	
	public TransactionHistoryEntity() {
		super();
	}

	public TransactionHistoryEntity(Long historyId, Long userId) {
		super();
		this.historyId = historyId;
		this.userId = userId;
	}


	public Long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
