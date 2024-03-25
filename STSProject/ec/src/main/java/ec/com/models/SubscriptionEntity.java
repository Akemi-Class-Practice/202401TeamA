package ec.com.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(value=KeyEntity.class)
public class SubscriptionEntity {
@Id
private Long productId;
@Id
private Long historyId;
//purchasehistoryviewlistの属性
private String productName;

private Integer productPrice;

private String productImage;
//ユーザーのIDを取得してDataを引き出す
private Long userId;
}
