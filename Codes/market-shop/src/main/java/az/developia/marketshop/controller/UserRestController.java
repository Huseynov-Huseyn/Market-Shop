package az.developia.marketshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.marketshop.entity.UserEntity;
import az.developia.marketshop.repository.UserRepository;
import az.developia.marketshop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/users")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserRestController {

	private final UserRepository repository;
	private final UserService service;

	@GetMapping
	public ResponseEntity<Object> getUser() {
		ResponseEntity<Object> response = service.findAll();
		return response;
	}

	@PostMapping(path = "/add")
	public UserEntity addUser(@Valid @RequestBody UserEntity entity) {
		repository.save(entity);
		return entity;
	}
}
