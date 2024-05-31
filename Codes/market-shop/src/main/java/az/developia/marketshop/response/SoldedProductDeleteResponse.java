package az.developia.marketshop.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SoldedProductDeleteResponse {
	private String name;
	private Integer amount;
	private LocalDate releaseDate;
	private LocalDate expirationDate;
	private LocalDateTime soldedDate;
	private String category;
	private Long barcod;

}
