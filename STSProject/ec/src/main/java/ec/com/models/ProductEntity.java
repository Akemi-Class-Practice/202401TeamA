package ec.com.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class ProductEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;
	
	private String productName;
	private String productPrice;
	private String productDetail;
	private String productImgge;
	private String registerDate;
	
	
	//空　コンストラクタ
	public ProductEntity() {
		
	}
	
	//コンストラクタ
	public ProductEntity(String productName, String productPrice, String productDetail, String productImgge,
			String registerDate) {
		this.productName = productName;
		this.productPrice = productPrice;
		this.productDetail = productDetail;
		this.productImgge = productImgge;
		this.registerDate = registerDate;
	}

	
	//カプセル化
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	public String getProductImgge() {
		return productImgge;
	}

	public void setProductImgge(String productImgge) {
		this.productImgge = productImgge;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	
		
}
