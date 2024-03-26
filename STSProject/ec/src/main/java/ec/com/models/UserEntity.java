package ec.com.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class UserEntity {



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	
	private String userName;
	private String userPassword;
	private String userEmail;
	private Integer deleteFlg;
	
	@OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
	private List<TransactionHistoryEntity> transactionHistory; // 一対多の関連

	//空　コンストラクタ
	public UserEntity() {

	}
	
	//コンストラクタ
	public UserEntity(String userName, String userPassword, String userEmail, Integer deleteFlg,
			List<TransactionHistoryEntity> transactionHistory) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.deleteFlg = deleteFlg;
		this.transactionHistory = transactionHistory;
	}
	
	public UserEntity(String userName, String userPassword, String userEmail, Integer deleteFlg) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.deleteFlg = deleteFlg;
	}
	
	

	//カプセル化
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Integer getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(Integer deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	public List<TransactionHistoryEntity> getTransactionHistory() {
		return transactionHistory;
	}

	public void setTransactionHistory(List<TransactionHistoryEntity> transactionHistory) {
		this.transactionHistory = transactionHistory;
	}



	
}
