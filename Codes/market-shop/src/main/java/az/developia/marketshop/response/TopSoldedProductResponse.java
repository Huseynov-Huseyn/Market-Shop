package az.developia.marketshop.response;

import java.util.List;

import az.developia.marketshop.entity.TopSoldedProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopSoldedProductResponse {
	private String username;
	private List<TopSoldedProductEntity> soldedProducts;
}
