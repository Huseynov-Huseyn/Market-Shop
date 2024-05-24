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
public class UserGetResponse {

	private String username;
	private Integer enabled;
	private String type;
	private String email;
	private LocalDate registerDate;

}
