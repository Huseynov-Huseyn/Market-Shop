package az.developia.marketshop.service;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import az.developia.marketshop.entity.UserEntity;
import az.developia.marketshop.exception.OurRuntimeException;
import az.developia.marketshop.repository.UserRepository;
import az.developia.marketshop.request.UserAddRequest;
import az.developia.marketshop.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository repository;
	private final ModelMapper mapper;
//	private final SecurityService securityService;

	public ResponseEntity<Object> findAll() {
		List<UserEntity> allUsers = repository.findAll();

		if (allUsers.isEmpty()) {
			throw new OurRuntimeException(null, "Heç bir Istifadəçi yoxdur!");
		}

		UserResponse response = new UserResponse();

		response.setUsers(allUsers);
//		response.setUsername(securityService.findUsername());
		return ResponseEntity.ok(response);
	}

	public ResponseEntity<Object> save(@Valid UserAddRequest request) {
		if (repository.findById(request.getUsername()).isPresent()) {
			throw new OurRuntimeException(null, "Bu istifadəçi adı mövcuddur");
		} else {
			if (repository.findByEmail(request.getEmail()).isPresent()) {
				throw new OurRuntimeException(null, "Bu Email mövcuddur");
			}
		}

		UserEntity entity = new UserEntity();
		mapper.map(request, entity);
		entity.setEnabled(1);
		entity.setRegisterDate(LocalDate.now());
		System.out.println(entity.getRegisterDate());
		repository.save(entity);
		return null;
	}
}
