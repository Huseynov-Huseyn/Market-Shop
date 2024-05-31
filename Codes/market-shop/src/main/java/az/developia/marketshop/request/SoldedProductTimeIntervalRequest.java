package az.developia.marketshop.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SoldedProductTimeIntervalRequest {
	private LocalDate start;

	private LocalDate stop;
}
