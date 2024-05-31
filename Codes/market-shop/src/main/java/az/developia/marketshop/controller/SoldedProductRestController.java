package az.developia.marketshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.marketshop.exception.OurRuntimeException;
import az.developia.marketshop.request.SoldedProductTimeIntervalRequest;
import az.developia.marketshop.response.SoldedProductDeleteResponse;
import az.developia.marketshop.service.SoldedProductService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/solded-products")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SoldedProductRestController {
	private final SoldedProductService service;

	@GetMapping(path = "/all")
	public ResponseEntity<Object> getSoldedProducts() {
		ResponseEntity<Object> response = service.getSoldedProducts();
		return response;

	}

	@GetMapping(path = "/{name}")
	public ResponseEntity<Object> getSoldedProductsByName(@PathVariable String name) {
		if (name == null) {
			throw new OurRuntimeException(null, "Id boş yazmaq olmaz!");
		}
		ResponseEntity<Object> soldedProductsByName = service.getSoldedProductsByName(name);
		return soldedProductsByName;

	}

	@GetMapping(path = "/all/{category}")
	public ResponseEntity<Object> getSoldedProductsByCategory(@PathVariable String category) {
		if (category == null) {
			throw new OurRuntimeException(null, "Category boş qoymaq olmaz!");
		}
		ResponseEntity<Object> productByCategory = service.getSoldedProductByCategory(category);
		return productByCategory;

	}

	@PutMapping(path = "/time-interval")
	public ResponseEntity<Object> getSoldedProductsByTimeInterval(
			@RequestBody SoldedProductTimeIntervalRequest request) {

		System.out.println(request.getStart());
		System.out.println(request.getStop());

		if (request.getStart() == null || request.getStop() == null) {
			throw new OurRuntimeException(null, "Zamanı boş qoymaq olmaz!");
		}

		if (request.getStop().isBefore(request.getStart())) {
			throw new OurRuntimeException(null, "Bitiş vaxtı, başlama vaxtından əvvəl olammaz!");
		}

		ResponseEntity<Object> productByTime = service.getSoldedProductsByTimeInterval(request);
		return productByTime;
	}

	@DeleteMapping(path = "/{id}")
	public SoldedProductDeleteResponse deleteProduct(@PathVariable Integer id) {
		if (id <= 0 || id == null) {
			throw new OurRuntimeException(null, "Id boş yazmaq olmaz!");
		}

		SoldedProductDeleteResponse response = service.deleteSoldedProductById(id);
		return response;

	}

}