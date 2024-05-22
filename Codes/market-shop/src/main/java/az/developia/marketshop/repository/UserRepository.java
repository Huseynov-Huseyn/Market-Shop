package az.developia.marketshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import az.developia.marketshop.entity.UserEntity;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, String> {

	@Query
	Optional<UserEntity> findByEmail(String email);

	@Query
	void deleteByUsername(String username);

	@Query(value = "update users set username=?2 where username=?1", nativeQuery = true)
	@Modifying
	void updateMyUsername(String username, String newUsername);
}
