package az.developia.marketshop.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.marketshop.entity.ProductEntity;
import az.developia.marketshop.exception.OurRuntimeException;
import az.developia.marketshop.request.ProductAddRequest;
import az.developia.marketshop.request.ProductUpdateRequest;
import az.developia.marketshop.response.ProductAddResponse;
import az.developia.marketshop.response.ProductDeleteResponse;
import az.developia.marketshop.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/products")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProductRestController {
	private final ProductService service;

	@GetMapping(path = "/all")
	public ResponseEntity<Object> getProducts() {
		ResponseEntity<Object> response = service.getProducts();
		return response;

	}

	@GetMapping(path = "/{id}")
	public Optional<ProductEntity> getProductById(@PathVariable Integer id) {
		if (id <= 0 || id == null) {
			throw new OurRuntimeException(null, "Id boş yazmaq olmaz!");
		}

		Optional<ProductEntity> productById = service.getProductById(id);
		return productById;

	}

	@GetMapping(path = "/all/{category}")
	public ResponseEntity<Object> getProductByCategory(@PathVariable String category) {
		if (category == null) {
			throw new OurRuntimeException(null, "Category boş qoymaq olmaz!");
		}
		ResponseEntity<Object> productByCategory = service.getProductByCategory(category);
		return productByCategory;

	}

	@PostMapping(path = "/add")
	public ResponseEntity<ProductAddResponse> addProduct(@Valid @RequestBody ProductAddRequest request,
			BindingResult br) {
		if (br.hasErrors()) {
			throw new OurRuntimeException(br, "Məlumatın tamlığı pozulub");
		}

		ResponseEntity<ProductAddResponse> response = service.addProduct(request);
		return response;

	}

	@DeleteMapping(path = "/{id}")
	public ProductDeleteResponse deleteProduct(@PathVariable Integer id) {
		if (id <= 0 || id == null) {
			throw new OurRuntimeException(null, "Id boş yazmaq olmaz!");
		}

		ProductDeleteResponse response = service.deleteProductById(id);
		return response;

	}

	@PutMapping(path = "/update")
	public boolean updateProduct(@Valid @RequestBody ProductUpdateRequest request) {
		Integer id = request.getId();
		if (id <= 0 || id == null) {
			throw new OurRuntimeException(null, "Id boş yazmaq olmaz!");
		}

		if (request.getName() == null) {
			throw new OurRuntimeException(null, "Produktun adını boş qoymaq olmaz!");
		}
		boolean updateProduct = service.updateProduct(request);
		return updateProduct;
	}

}