package az.developia.marketshop.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDecreaseUpdateRequest {

	@Size(min = 2, max = 80, message = "Produktun adı min 2 max 80 simvoldan ibarət olmalıdır")
	private String name;

	@Min(value = 0, message = "0 ve ya 0dan yuxarı sayda olmalıdır")
	@Max(value = 10000, message = "10000 ve ya 10000den aşağı	 sayda olmalıdır")
	private Integer decreaseAmount;

	@Min(value = 10000000000L, message = "10000000000L ve ya 10000000000L dan yuxarı sayda olmalıdır")
	@Max(value = 99999999999L, message = "99999999999L ve ya 99999999999L aşağı	 sayda olmalıdır")
	private long barcod;
}
