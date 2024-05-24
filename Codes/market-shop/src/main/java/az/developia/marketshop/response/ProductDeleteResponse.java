package az.developia.marketshop.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDeleteResponse {
	private Integer id;
	private String name;
	private String amount;
	private String category;
}
