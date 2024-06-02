package az.developia.marketshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import az.developia.marketshop.request.ProductDecreaseUpdateRequest;
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
	@PreAuthorize(value = "hasAuthority('ROLE_GET_PRODUCT')")
	public ResponseEntity<Object> getProducts() {
		ResponseEntity<Object> response = service.getProducts();
		return response;

	}

	@GetMapping(path = "/{id}")
	@PreAuthorize(value = "hasAuthority('ROLE_GET_PRODUCT')")
	public ProductEntity getProductById(@PathVariable Integer id) {
		if (id <= 0 || id == null) {
			throw new OurRuntimeException(null, "Id boş yazmaq olmaz!");
		}

		ProductEntity productById = service.getProductById(id).get();
		return productById;

	}

	@GetMapping(path = "/all/{category}")
	@PreAuthorize(value = "hasAuthority('ROLE_GET_PRODUCT')")
	public ResponseEntity<Object> getProductByCategory(@PathVariable String category) {
		if (category == null) {
			throw new OurRuntimeException(null, "Category boş qoymaq olmaz!");
		}
		ResponseEntity<Object> productByCategory = service.getProductByCategory(category);
		return productByCategory;

	}

	@PostMapping(path = "/add")
	@PreAuthorize(value = "hasAuthority('ROLE_ADD_PRODUCT')")
	public ResponseEntity<ProductAddResponse> addProduct(@Valid @RequestBody ProductAddRequest request,
			BindingResult br) {
		if (br.hasErrors()) {
			throw new OurRuntimeException(br, "Məlumatın tamlığı pozulub");
		}

		ResponseEntity<ProductAddResponse> response = service.addProduct(request);
		return response;

	}

	@DeleteMapping(path = "/{id}")
	@PreAuthorize(value = "hasAuthority('ROLE_DELETE_PRODUCT')")
	public ProductDeleteResponse deleteProduct(@PathVariable Integer id) {
		if (id <= 0 || id == null) {
			throw new OurRuntimeException(null, "Id boş yazmaq olmaz!");
		}

		ProductDeleteResponse response = service.deleteProductById(id);
		return response;

	}

	@PutMapping(path = "/update")
	@PreAuthorize(value = "hasAuthority('ROLE_UPDATE_PRODUCT')")
	public boolean updateProduct(@Valid @RequestBody ProductUpdateRequest request, BindingResult br) {
		if (br.hasErrors()) {
			throw new OurRuntimeException(br, "Məhsulun məlumatının tamlığı pozulub!");
		}
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

	@PutMapping(path = "/decrease")
	@PreAuthorize(value = "hasAuthority('ROLE_SELL_PRODUCT')")
	public boolean updateDecreaseProduct(@Valid @RequestBody ProductDecreaseUpdateRequest request, BindingResult br) {
		if (br.hasErrors()) {
			throw new OurRuntimeException(br, "Məhsulun məlumatının tamlığı pozulub!");
		}
		if (request.getDecreaseAmount() <= 0) {
			throw new OurRuntimeException(null, "ən az 1 mal satılmalıdır!");
		}

		boolean updateProduct = service.updateDecreaseProduct(request);
		return updateProduct;
	}

}