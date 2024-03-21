package ec.com.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TransactionHistoryEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long historyId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date peroductPurchaseDetaTimeDate;
	
	private int totalPrice;
	
	private String payment;
	
	private Long userId;
	
	public TransactionHistoryEntity() {

	}

	public TransactionHistoryEntity(Long historyId, Date peroductPurchaseDetaTimeDate, int totalPrice, String payment,
			Long userId) {
		this.historyId = historyId;
		this.peroductPurchaseDetaTimeDate = peroductPurchaseDetaTimeDate;
		this.totalPrice = totalPrice;
		this.payment = payment;
		this.userId = userId;
	}

	public Long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}

	public Date getPeroductPurchaseDetaTimeDate() {
		return peroductPurchaseDetaTimeDate;
	}

	public void setPeroductPurchaseDetaTimeDate(Date peroductPurchaseDetaTimeDate) {
		this.peroductPurchaseDetaTimeDate = peroductPurchaseDetaTimeDate;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
