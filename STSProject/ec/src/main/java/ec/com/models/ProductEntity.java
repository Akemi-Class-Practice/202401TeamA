package ec.com.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class ProductEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
}
