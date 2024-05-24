package az.developia.marketshop.response;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProductAddResponse {
	private String name;
	private String price;
	private Date releaseDate;
	private Date expirationDate;
	private String category;

}
