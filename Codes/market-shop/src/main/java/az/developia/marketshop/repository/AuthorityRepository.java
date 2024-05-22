package az.developia.marketshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import az.developia.marketshop.entity.AuthorityEntity;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Integer> {

	@Query(value = "insert into authorities (username,authority) select ?1,authority from authority_list where admin=1", nativeQuery = true)
	@Modifying
	void addAdminAut(String username);

	@Query(value = "insert into authorities (username,authority) select ?1,authority from authority_list where customer=1", nativeQuery = true)
	@Modifying
	void addCustomerAut(String username);

	@Query(value = "delete from authorities where username=?1", nativeQuery = true)
	@Modifying
	void deleteUserAuthorities(String username);

	@Query(value = "update authorities set username=?1 where username=?2", nativeQuery = true)
	@Modifying
	void updateUserUsername(String newU, String oldU);

	@Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM authorities WHERE username = :username", nativeQuery = true)
	int countByUsername(@Param("username") String username);

}