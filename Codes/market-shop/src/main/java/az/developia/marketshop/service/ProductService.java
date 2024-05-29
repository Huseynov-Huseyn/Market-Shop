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
import az.developia.marketshop.request.ProductDecreaseUpdateRequest;
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
		if (repository.findByBarcod(request.getBarcod()).isPresent()) {
			ProductEntity oldProduct = repository.findByBarcod(request.getBarcod()).get();
			ProductUpdateRequest update = new ProductUpdateRequest();
			mapper.map(oldProduct, update);
			request.setAmount(request.getAmount() + oldProduct.getAmount());
			update.setAmount(request.getAmount());
			if (updateProduct(update)) {
				ProductAddResponse response = new ProductAddResponse();
				mapper.map(update, response);
				return ResponseEntity.ok(response);

			} else {
				throw new OurRuntimeException(null, "Produktun sayinin artirilmasinda ugursuzluq oldu");
			}
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

		if (repository.findById(request.getId()).isPresent()) {

			ProductEntity entity = new ProductEntity();
			ProductEntity oldProduct = repository.findById(request.getId()).get();
			mapper.map(oldProduct, entity);
			mapper.map(request, entity);
			repository.save(entity);
		} else {
			throw new OurRuntimeException(null, "Bu idli produkt mövcud deyil");
		}

		return true;

	}

	public boolean updateDecreaseProduct(@Valid ProductDecreaseUpdateRequest request) {
		if (repository.findByBarcod(request.getBarcod()).isPresent()) {

			ProductEntity entity = new ProductEntity();
			ProductEntity oldProduct = repository.findByBarcod(request.getBarcod()).get();
			if (oldProduct.getName().equals(request.getName())) {

				if (oldProduct.getAmount() < request.getDecreaseAmount()) {
					throw new OurRuntimeException(null,
							"Bazadakı məhsul sayından çoxdur.Bazadakı məhsul sayı:" + oldProduct.getAmount());
				}

				mapper.map(oldProduct, entity);
				entity.setAmount(oldProduct.getAmount() - request.getDecreaseAmount());
				repository.save(entity);
			} else {
				System.out.println(oldProduct.getName());
				System.out.println(request.getName());
				throw new OurRuntimeException(null, "Ad doğru deyil");
			}
		} else {
			throw new OurRuntimeException(null, "Bu barcodlu produkt mövcud deyil");
		}

		return true;
	}

}
