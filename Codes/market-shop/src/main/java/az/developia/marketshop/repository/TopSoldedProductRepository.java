package az.developia.marketshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import az.developia.marketshop.entity.TopSoldedProductEntity;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface TopSoldedProductRepository extends JpaRepository<TopSoldedProductEntity, String> {
	@Query
	Optional<TopSoldedProductEntity> findByName(String name);

	@Query(value = "SELECT * FROM `market-shop`.`top-solded-product` ORDER BY `amount` DESC;", nativeQuery = true)
	List<TopSoldedProductEntity> findAllSoldedProduct();

}
