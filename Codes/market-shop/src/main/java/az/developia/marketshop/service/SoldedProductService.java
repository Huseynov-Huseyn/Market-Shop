package az.developia.marketshop.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import az.developia.marketshop.entity.ProductEntity;
import az.developia.marketshop.entity.SoldedProductEntity;
import az.developia.marketshop.entity.TopSoldedProductEntity;
import az.developia.marketshop.exception.OurRuntimeException;
import az.developia.marketshop.repository.SoldedProductRepository;
import az.developia.marketshop.repository.TopSoldedProductRepository;
import az.developia.marketshop.response.SoldedProductDeleteResponse;
import az.developia.marketshop.response.SoldedProductResponse;
import az.developia.marketshop.response.TopSoldedProductResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SoldedProductService {
	private final SecurityService securityService;
	private final SoldedProductRepository repository;
	private final TopSoldedProductRepository topProductRepository;
	private final ModelMapper mapper;

	public void addSoldedProduct(ProductEntity productEntity) {
		SoldedProductEntity soldedProductEntity = new SoldedProductEntity();
		mapper.map(productEntity, soldedProductEntity);
		soldedProductEntity.setSoldedDate(LocalDateTime.now());
		soldedProductEntity.setId(0);
		repository.save(soldedProductEntity);
	}

	public void addTopSoldedProduct(ProductEntity productEntity) {
		TopSoldedProductEntity entity = new TopSoldedProductEntity();
		mapper.map(productEntity, entity);
		if (topProductRepository.findByName(productEntity.getName()).isPresent()) {
			entity.setAmount(entity.getAmount() + topProductRepository.findByName(entity.getName()).get().getAmount());
			topProductRepository.save(entity);
		}
		topProductRepository.save(entity);
	}

	public ResponseEntity<Object> getSoldedProducts() {
		List<SoldedProductEntity> all = repository.findAll();
		if (all.isEmpty()) {
			throw new OurRuntimeException(null, "Heç bir produkt satılmayıbdır!");
		}
		SoldedProductResponse response = new SoldedProductResponse();

		response.setSoldedProducts(all);
		response.setUsername(securityService.findUsername());
		return ResponseEntity.ok(response);

	}

	public ResponseEntity<Object> getSoldedProductsByName(String name) {
		List<SoldedProductEntity> allByName = repository.findAllByName(name);

		if (allByName.isEmpty()) {
			throw new OurRuntimeException(null, name + " adında produkt satılmayıbdır");
		}
		SoldedProductResponse response = new SoldedProductResponse();

		response.setSoldedProducts(allByName);
		response.setUsername(securityService.findUsername());
		return ResponseEntity.ok(response);
	}

	public ResponseEntity<Object> getSoldedProductByCategory(String category) {
		if (repository.findAllByCategory(category).isEmpty()) {
			throw new OurRuntimeException(null, "Category mövcud deyil!");
		}

		List<SoldedProductEntity> allByCategory = repository.findAllByCategory(category);

		SoldedProductResponse response = new SoldedProductResponse();
		response.setSoldedProducts(allByCategory);
		response.setUsername(securityService.findUsername());
		return ResponseEntity.ok(response);

	}

	public SoldedProductDeleteResponse deleteSoldedProductById(Integer id) {
		if (repository.findById(id).isPresent()) {
			SoldedProductDeleteResponse response = new SoldedProductDeleteResponse();
			Optional<SoldedProductEntity> byId = repository.findById(id);
			mapper.map(byId.get(), response);
			repository.deleteById(id);
			return response;
		} else {
			throw new OurRuntimeException(null, "Bu Idli satılmış produkt mövcud deyil!");
		}

	}

	public ResponseEntity<Object> getSoldedProductsByTimeInterval(LocalDate begin, LocalDate end) {
		System.out.println(begin);
		List<SoldedProductEntity> allByTime = repository.findAllByTimeInterval(begin, end);
		if (allByTime.isEmpty()) {
			throw new OurRuntimeException(null, "Bu zaman aralığında produkt satılmayıbdır");
		}
		SoldedProductResponse response = new SoldedProductResponse();

		response.setSoldedProducts(allByTime);

		response.setUsername(securityService.findUsername());
		return ResponseEntity.ok(response);

	}

	public ResponseEntity<Object> getSoldedProductsByCategoryTimeInterval(String category, LocalDate begin,
			LocalDate end) {
		List<SoldedProductEntity> allByTime = repository.findAllByCategoryAndTime(category, begin, end);
		if (allByTime.isEmpty()) {
			throw new OurRuntimeException(null, "Bu zaman aralığında və bu kategoriyada produkt satılmayıbdır");
		}
		SoldedProductResponse response = new SoldedProductResponse();

		response.setSoldedProducts(allByTime);

		response.setUsername(securityService.findUsername());
		return ResponseEntity.ok(response);

	}

	public ResponseEntity<Object> getTopSoldedProducts() {
		List<TopSoldedProductEntity> all = topProductRepository.findAllSoldedProduct();
		System.out.println(all);
		if (all.isEmpty()) {
			throw new OurRuntimeException(null, "Heç bir produkt satılmayıbdır!");
		}
		TopSoldedProductResponse response = new TopSoldedProductResponse();

		response.setSoldedProducts(all);

		response.setUsername(securityService.findUsername());
		return ResponseEntity.ok(response);

	}

}
