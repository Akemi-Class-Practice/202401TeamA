package ec.com.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.models.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
	//Save New product
	ProductEntity save (ProductEntity productEntity);
	
	//Find by productId
	ProductEntity findByProductId (Long productId); 
	
	//Find by productName
	ProductEntity findByProductName (String productName);
	
	List<ProductEntity>findByDeleteFlg(Integer deleteFlg);

}
