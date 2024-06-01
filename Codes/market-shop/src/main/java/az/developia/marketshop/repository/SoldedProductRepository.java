package az.developia.marketshop.repository;

import java.time.LocalDate;
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

	@Query(value = "SELECT * FROM `solded-product`  WHERE category=?1", nativeQuery = true)
	List<SoldedProductEntity> findAllByCategory(String category);

	Optional<ProductEntity> findByBarcod(long barcod);

	@Query(value = "SELECT * FROM `market-shop`.`solded-product` WHERE solded_date BETWEEN ?1 AND ?2", nativeQuery = true)
	List<SoldedProductEntity> findAllByTimeInterval(LocalDate begin, LocalDate end);

	@Query(value = "SELECT * FROM `market-shop`.`solded-product` WHERE category=?1 and solded_date BETWEEN ?2 AND ?3", nativeQuery = true)
	List<SoldedProductEntity> findAllByCategoryAndTime(String category, LocalDate begin, LocalDate end);

}