package az.developia.marketshop.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.marketshop.exception.OurRuntimeException;
import az.developia.marketshop.response.SoldedProductDeleteResponse;
import az.developia.marketshop.service.SoldedProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/solded-products")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SoldedProductRestController {
	private final SoldedProductService service;

	@GetMapping(path = "/all")
	@PreAuthorize(value = "hasAuthority('ROLE_GET_SOLDED_PRODUCT')")
	public ResponseEntity<Object> getSoldedProducts() {
		ResponseEntity<Object> response = service.getSoldedProducts();
		return response;

	}

	@GetMapping(path = "/{name}")
	@PreAuthorize(value = "hasAuthority('ROLE_GET_SOLDED_PRODUCT')")
	public ResponseEntity<Object> getSoldedProductsByName(@PathVariable String name) {
		if (name == null) {
			throw new OurRuntimeException(null, "Id boş yazmaq olmaz!");
		}
		ResponseEntity<Object> soldedProductsByName = service.getSoldedProductsByName(name);
		return soldedProductsByName;

	}

	@GetMapping(path = "/all/{category}")
	@PreAuthorize(value = "hasAuthority('ROLE_GET_SOLDED_PRODUCT')")
	public ResponseEntity<Object> getSoldedProductsByCategory(@PathVariable String category) {
		if (category == null) {
			throw new OurRuntimeException(null, "Category boş qoymaq olmaz!");
		}
		ResponseEntity<Object> productByCategory = service.getSoldedProductByCategory(category);
		return productByCategory;

	}

	@GetMapping(path = "/top-solded")
	@PreAuthorize(value = "hasAuthority('ROLE_GET_SOLDED_PRODUCT')")
	public ResponseEntity<Object> getTopSoldedProducts() {
		ResponseEntity<Object> response = service.getTopSoldedProducts();
		return response;
	}

	@DeleteMapping(path = "/{id}")
	@PreAuthorize(value = "hasAuthority('ROLE_DELETE_SOLDED_PRODUCT')")
	public SoldedProductDeleteResponse deleteProduct(@PathVariable Integer id) {
		if (id <= 0 || id == null) {
			throw new OurRuntimeException(null, "Id boş yazmaq olmaz!");
		}

		SoldedProductDeleteResponse response = service.deleteSoldedProductById(id);
		return response;

	}

	@PostMapping(path = "/begin/{begin}/end/{end}")
	@PreAuthorize(value = "hasAuthority('ROLE_GET_SOLDED_PRODUCT')")
	public ResponseEntity<Object> getByTimeInterval(@PathVariable LocalDate begin, @PathVariable LocalDate end) {
		if (begin == null || end == null) {
			throw new OurRuntimeException(null, "Zamanı boş qoymaq olmaz!");
		}

		if (end.isBefore(begin)) {
			throw new OurRuntimeException(null, "Bitiş vaxtı, başlama vaxtından əvvəl olammaz!");
		}

		ResponseEntity<Object> productByTime = service.getSoldedProductsByTimeInterval(begin, end);
		return productByTime;
	}

	@PostMapping(path = "/category/{category}/begin/{begin}/end/{end}")
	@PreAuthorize(value = "hasAuthority('ROLE_GET_SOLDED_PRODUCT')")
	public ResponseEntity<Object> getByTimeInterval(@PathVariable String category, @PathVariable LocalDate begin,
			@PathVariable LocalDate end) {
		if (category == null) {
			throw new OurRuntimeException(null, "Kategoriyanı boş qoymaq olmaz!");
		}
		if (begin == null || end == null) {
			throw new OurRuntimeException(null, "Zamanı boş qoymaq olmaz!");
		}

		if (end.isBefore(begin)) {
			throw new OurRuntimeException(null, "Bitiş vaxtı, başlama vaxtından əvvəl olammaz!");
		}

		ResponseEntity<Object> productByTime = service.getSoldedProductsByCategoryTimeInterval(category, begin, end);
		return productByTime;
	}

}