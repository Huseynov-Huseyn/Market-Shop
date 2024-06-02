package az.developia.marketshop.response;

import java.math.BigDecimal;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopSoldedProducts {
	@Id
	private String name;
	private Integer amount;
	private BigDecimal cost;
	private BigDecimal price;
	private Long barcod;
	private String category;
}
