package ec.com.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class KeyEntity implements Serializable{
private Long productId;
private Long historyId;
}
