package az.developia.marketshop.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import az.developia.marketshop.entity.ProductEntity;
import az.developia.marketshop.entity.SoldedProductEntity;
import az.developia.marketshop.repository.SoldedProductRepository;
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

}
