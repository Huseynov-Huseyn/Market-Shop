package az.developia.marketshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.marketshop.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {

}
