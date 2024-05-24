package az.developia.marketshop.response;

import java.util.List;

import az.developia.marketshop.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
	private List<ProductEntity> Products;
	private String username;

}
