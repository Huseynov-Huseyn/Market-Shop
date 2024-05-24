package az.developia.marketshop.response;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductGetByCategoryResponse {
	private String name;
	private String price;
	private Date releaseDate;
	private Date expirationDate;
	private String category;
}
