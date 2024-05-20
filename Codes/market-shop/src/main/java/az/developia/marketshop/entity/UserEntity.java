package az.developia.marketshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

	@Id
	@Column(columnDefinition = "VARCHAR(20)")
	private String username;

	private String password;

	private Integer enabled;

	private String type;

	@Column(columnDefinition = "VARCHAR(60)")
	private String email;
}