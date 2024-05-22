package az.developia.marketshop.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
	@Size(min = 2, max = 20, message = "İstifadəçi adı min 2 max 20 simvoldan ibarət olmalıdır")
	private String username;

	@Size(min = 2, max = 20, message = "İstifadəçi adı min 2 max 20 simvoldan ibarət olmalıdır")
	private String newUsername;

	@Size(min = 2, max = 100, message = "Şifrə min 2 max 100 simvoldan ibarət olmalıdır")
	private String password;

	@Size(min = 2, max = 60, message = "Email min 2 max 60 simvoldan ibarət olmalıdır")
	private String email;

}
