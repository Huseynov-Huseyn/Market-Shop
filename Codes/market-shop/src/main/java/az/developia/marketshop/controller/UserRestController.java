package az.developia.marketshop.controller;

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

import az.developia.marketshop.entity.UserEntity;
import az.developia.marketshop.exception.OurRuntimeException;
import az.developia.marketshop.request.UserAddRequest;
import az.developia.marketshop.request.UserUpdateRequest;
import az.developia.marketshop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/users")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserRestController {
	private final UserService service;

	@GetMapping
	public ResponseEntity<Object> getUser() {
		ResponseEntity<Object> response = service.findAll();
		return response;
	}

	@GetMapping(path = "/{username}")
	public UserEntity getUserByUsername(@PathVariable String username) {
		if (username == null) {
			throw new OurRuntimeException(null, "Id boş yazmaq olmaz!");
		}
		UserEntity response = service.findByUsername(username);
		return response;
	}

	@PostMapping(path = "/add")
	public ResponseEntity<Object> addUser(@Valid @RequestBody UserAddRequest request, BindingResult br) {
		if (br.hasErrors()) {
			throw new OurRuntimeException(br, "Məlumatın tamlığı pozulub!");
		}
		ResponseEntity<Object> save = service.save(request);
		return save;
	}

	@DeleteMapping(path = "/{username}")
	public UserEntity deleteByUsername(@PathVariable String username) {
		if (username == null) {
			throw new OurRuntimeException(null, "Id boş yazmaq olmaz!");
		}
		UserEntity response = service.deleteByUsername(username);
		return response;
	};

	@PutMapping(path = "/update")
	public boolean updateUser(@Valid @RequestBody UserUpdateRequest request) {
		if (request.getNewUsername() == null || request.getUsername() == null) {
			throw new OurRuntimeException(null, "İstifadəçi adını boş qoymaq olmaz!");
		}
		boolean updateUser = service.updateUser(request);
		return updateUser;
	}

}