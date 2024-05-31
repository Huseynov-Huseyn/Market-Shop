package az.developia.marketshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import az.developia.marketshop.entity.ProductEntity;
import az.developia.marketshop.entity.SoldedProductEntity;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface SoldedProductRepository extends JpaRepository<SoldedProductEntity, Integer> {

	@Query(value = "SELECT * FROM `solded-product`  WHERE name=?1", nativeQuery = true)
	List<SoldedProductEntity> findAllByName(String name);

	Optional<ProductEntity> findByBarcod(long barcod);

}