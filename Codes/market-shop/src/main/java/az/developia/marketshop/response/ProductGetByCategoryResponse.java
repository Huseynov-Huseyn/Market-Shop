package az.developia.marketshop.response;

import java.time.LocalDate;

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
	private LocalDate releaseDate;
	private LocalDate expirationDate;
	private String category;
	private long barcod;
}
