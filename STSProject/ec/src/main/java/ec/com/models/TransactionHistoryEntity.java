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
	private Date productpurchaseDateTime;
	
	private int totalPrice;
	
	private String payment;
	
	private Long userId;
	
	public TransactionHistoryEntity() {

	}

	public TransactionHistoryEntity(Date productpurchaseDateTime, int totalPrice, String payment,
			Long userId) {
		this.productpurchaseDateTime = productpurchaseDateTime;
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

	public Date getProductpurchaseDateTime() {
		return productpurchaseDateTime;
	}

	public void setProductpurchaseDateTime(Date productpurchaseDateTime) {
		this.productpurchaseDateTime = productpurchaseDateTime;
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
