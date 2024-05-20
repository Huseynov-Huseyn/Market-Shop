package az.developia.marketshop.response;

import java.util.List;

import az.developia.marketshop.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

	private List<UserEntity> Users;
	private String username;

}
