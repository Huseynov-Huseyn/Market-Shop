package az.developia.marketshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.marketshop.exception.OurRuntimeException;
import az.developia.marketshop.service.SoldedProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/solded-products")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SoldedProductRestController {
	private final SoldedProductService service;

	@GetMapping(path = "/all")
	public ResponseEntity<Object> getProducts() {
		ResponseEntity<Object> response = service.getSoldedProducts();
		return response;

	}

	@GetMapping(path = "/{name}")
	public ResponseEntity<Object> getProductById(@PathVariable String name) {
		if (name == null) {
			throw new OurRuntimeException(null, "Id boş yazmaq olmaz!");
		}
		ResponseEntity<Object> soldedProductsByName = service.getSoldedProductsByName(name);
		return soldedProductsByName;

	}
//
//	@GetMapping(path = "/all/{category}")
//	public ResponseEntity<Object> getProductByCategory(@PathVariable String category) {
//		if (category == null) {
//			throw new OurRuntimeException(null, "Category boş qoymaq olmaz!");
//		}
//		ResponseEntity<Object> productByCategory = service.getProductByCategory(category);
//		return productByCategory;
//
//	}
//
//	@DeleteMapping(path = "/{id}")
//	public ProductDeleteResponse deleteProduct(@PathVariable Integer id) {
//		if (id <= 0 || id == null) {
//			throw new OurRuntimeException(null, "Id boş yazmaq olmaz!");
//		}
//
//		ProductDeleteResponse response = service.deleteProductById(id);
//		return response;
//
//	}
//
//	@PutMapping(path = "/update")
//	public boolean updateProduct(@Valid @RequestBody ProductUpdateRequest request, BindingResult br) {
//		if (br.hasErrors()) {
//			throw new OurRuntimeException(br, "Məhsulun məlumatının tamlığı pozulub!");
//		}
//		Integer id = request.getId();
//		if (id <= 0 || id == null) {
//			throw new OurRuntimeException(null, "Id boş yazmaq olmaz!");
//		}
//
//		if (request.getName() == null) {
//			throw new OurRuntimeException(null, "Produktun adını boş qoymaq olmaz!");
//		}
//		boolean updateProduct = service.updateProduct(request);
//		return updateProduct;
//	}

}