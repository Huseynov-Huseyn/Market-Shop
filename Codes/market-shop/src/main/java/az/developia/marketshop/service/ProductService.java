package az.developia.marketshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import az.developia.marketshop.entity.ProductEntity;
import az.developia.marketshop.exception.OurRuntimeException;
import az.developia.marketshop.repository.ProductRepository;
import az.developia.marketshop.request.ProductAddRequest;
import az.developia.marketshop.request.ProductUpdateRequest;
import az.developia.marketshop.response.ProductAddResponse;
import az.developia.marketshop.response.ProductCategoryResponse;
import az.developia.marketshop.response.ProductDeleteResponse;
import az.developia.marketshop.response.ProductGetByCategoryResponse;
import az.developia.marketshop.response.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository repository;
	private final ModelMapper mapper;

	public ResponseEntity<Object> getProducts() {
		List<ProductEntity> allProduct = repository.findAll();

		if (repository.findAll().isEmpty()) {
			throw new OurRuntimeException(null, "Heç bir mal tapılmadı");
		}
		ProductResponse response = new ProductResponse();

		response.setProducts(allProduct);
//		response.setUsername(securityService.findUsername());
		return ResponseEntity.ok(response);
	}

	public Optional<ProductEntity> getProductById(Integer id) {
		if (repository.findById(id).isPresent()) {
			Optional<ProductEntity> byId = repository.findById(id);
			return byId;
		} else {
			throw new OurRuntimeException(null, "Bu IDli produkt mövcud deyil!");
		}
	}

	public ResponseEntity<Object> getProductByCategory(String category) {
		if (repository.findAllByCategory(category) != null) {

			List<ProductEntity> allByCategory = repository.findAllByCategory(category);
			ProductCategoryResponse response = new ProductCategoryResponse();
			List<ProductGetByCategoryResponse> responseList = new ArrayList<ProductGetByCategoryResponse>();
			for (ProductEntity productEntity : allByCategory) {
				ProductGetByCategoryResponse responseMini = new ProductGetByCategoryResponse();
				mapper.map(productEntity, responseMini);
				responseList.add(responseMini);
			}
			response.setProducts(responseList);
			// response.setUsername(securityService.findUsername());
			return ResponseEntity.ok(response);
		} else {
			throw new OurRuntimeException(null, "İstifadəçi mövcud deyil!");
		}
	}

	public ResponseEntity<ProductAddResponse> addProduct(ProductAddRequest request) {
		if (repository.findByName(request.getName()).isPresent()) {
			throw new OurRuntimeException(null, "Bu adda Məhsul var!");
		}
		ProductEntity entity = new ProductEntity();
		mapper.map(request, entity);
		repository.save(entity);

		ProductAddResponse response = new ProductAddResponse();
		mapper.map(entity, response);
		return ResponseEntity.ok(response);

	}

	public ProductDeleteResponse deleteProductById(Integer id) {
		if (repository.findById(id).isPresent()) {
			ProductDeleteResponse response = new ProductDeleteResponse();
			Optional<ProductEntity> byId = repository.findById(id);
			mapper.map(byId.get(), response);
			repository.deleteById(id);
			return response;
		} else {
			throw new OurRuntimeException(null, "Bu Idli produkt mövcud deyil!");
		}
	}

	public boolean updateProduct(@Valid ProductUpdateRequest request) {
		Optional<ProductEntity> byId = repository.findById(request.getId());

		if (byId != null) {

			ProductEntity entity = new ProductEntity();
			ProductEntity oldProduct = byId.get();
			mapper.map(oldProduct, entity);
			mapper.map(request, entity);
			repository.save(entity);
		} else {
			throw new OurRuntimeException(null, "Bu idli produkt mövcud deyil");
		}

		return true;

	}

}
