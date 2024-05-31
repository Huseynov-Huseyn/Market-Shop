package az.developia.marketshop.service;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import az.developia.marketshop.entity.ProductEntity;
import az.developia.marketshop.entity.SoldedProductEntity;
import az.developia.marketshop.exception.OurRuntimeException;
import az.developia.marketshop.repository.SoldedProductRepository;
import az.developia.marketshop.response.SoldedProductResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SoldedProductService {

	private final SoldedProductRepository repository;
	private final ModelMapper mapper;

	public void addSoldedProduct(ProductEntity productEntity) {
		SoldedProductEntity soldedProductEntity = new SoldedProductEntity();
		mapper.map(productEntity, soldedProductEntity);
		soldedProductEntity.setSoldedDate(LocalDateTime.now());
		soldedProductEntity.setId(0);
		repository.save(soldedProductEntity);
	}

	public ResponseEntity<Object> getSoldedProducts() {
		List<SoldedProductEntity> all = repository.findAll();
		if (all.isEmpty()) {
			throw new OurRuntimeException(null, "Heç bir produkt satılmayıbdır!");
		}
		SoldedProductResponse response = new SoldedProductResponse();

		response.setSoldedProducts(all);
		return ResponseEntity.ok(response);

	}

	public ResponseEntity<Object> getSoldedProductsByName(String name) {
		List<SoldedProductEntity> allByName = repository.findAllByName(name);

		if (allByName.isEmpty()) {
			throw new OurRuntimeException(null, name + " adında produkt satılmayıbdır");
		}
		SoldedProductResponse response = new SoldedProductResponse();

		response.setSoldedProducts(allByName);
		return ResponseEntity.ok(response);
	}

}
