package az.developia.marketshop.response;

import java.time.LocalDate;

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
	private Integer amount;
	private LocalDate releaseDate;
	private LocalDate expirationDate;
	private String category;
	private long barcod;

}
