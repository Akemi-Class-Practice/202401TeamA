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
@Table(name="admin")
public class AdminEntity {



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long adminId;
	
	private String adminName;
	private String adminEmail;
	private String adminPassword;
	private Integer deleteFlg;
	
	@OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
	private List<ProductEntity> products; // 一対多の関連
	
	//空のコンストラクタ
	public AdminEntity() {
		
	}
	
	//コンストラクタ
	public AdminEntity(String adminName, String adminEmail, String adminPassword, Integer deleteFlg) {
		this.adminName = adminName;
		this.adminEmail = adminEmail;
		this.adminPassword = adminPassword;
		this.deleteFlg = deleteFlg;
	}

	
	//カプセル化　Get Set
	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public Integer getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(Integer deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	public List<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(List<ProductEntity> products) {
		this.products = products;
	}
	
	

	
}
