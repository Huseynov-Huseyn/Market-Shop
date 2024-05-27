package az.developia.marketshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import az.developia.marketshop.entity.ProductEntity;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

	@Query(value = "SELECT * FROM product WHERE category=?1", nativeQuery = true)
	List<ProductEntity> findAllByCategory(String category);

	Optional<ProductEntity> findByBarcod(long barcod);

}