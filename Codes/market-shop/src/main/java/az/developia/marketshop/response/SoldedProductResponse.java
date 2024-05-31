package az.developia.marketshop.response;

import java.util.List;

import az.developia.marketshop.entity.SoldedProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SoldedProductResponse {
	private String username;
	private List<SoldedProductEntity> soldedProducts;
}
