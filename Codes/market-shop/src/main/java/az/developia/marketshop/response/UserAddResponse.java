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
public class UserAddResponse {

	private String username;
	private String type;
	private String email;

	private LocalDate registerDate;

}
