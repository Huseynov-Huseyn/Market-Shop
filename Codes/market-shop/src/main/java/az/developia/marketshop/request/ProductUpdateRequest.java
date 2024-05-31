package az.developia.marketshop.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateRequest {
	private Integer id;

	@Size(min = 2, max = 80, message = "Produktun adı min 2 max 80 simvoldan ibarət olmalıdır")
	private String name;

	@Size(min = 2, max = 6, message = "Produktun maya qiyməti min 0 max 10000 olur")
	private String cost;

	@Size(min = 2, max = 6, message = "Produktun satış qiyməti min 0 max 10000 olur")
	private String price;

	@Min(value = 0, message = "0 ve ya 0dan yuxarı sayda olmalıdır")
	@Max(value = 10000, message = "10000 ve ya 10000den aşağı	 sayda olmalıdır")
	private Integer amount;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Past
	private LocalDate releaseDate;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate expirationDate;

	@Size(min = 2, max = 30, message = "Produktun kategoriyası min 2 max 30 olur")
	@Column(columnDefinition = "VARCHAR(30)")
	private String category;

	@Min(value = 10000000000L, message = "10000000000L ve ya 10000000000L dan yuxarı sayda olmalıdır")
	@Max(value = 99999999999L, message = "99999999999L ve ya 99999999999L aşağı	 sayda olmalıdır")
	private long barcod;
}
