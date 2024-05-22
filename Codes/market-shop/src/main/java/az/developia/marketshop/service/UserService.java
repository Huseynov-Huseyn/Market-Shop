package az.developia.marketshop.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import az.developia.marketshop.entity.UserEntity;
import az.developia.marketshop.exception.OurRuntimeException;
import az.developia.marketshop.repository.UserRepository;
import az.developia.marketshop.request.UserAddRequest;
import az.developia.marketshop.request.UserUpdateRequest;
import az.developia.marketshop.response.UserAddResponse;
import az.developia.marketshop.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository repository;
	private final ModelMapper mapper;
	private final AuthorityService authorityService;
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

	public UserEntity findByUsername(String username) {
		if (repository.findById(username).isPresent()) {
			Optional<UserEntity> byId = repository.findById(username);
			return byId.get();
		} else {
			throw new OurRuntimeException(null, "İstifadəçi mövcud deyil!");
		}
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
		authorityService.saveAuthority(request.getUsername());
		repository.save(entity);

		UserAddResponse response = new UserAddResponse();
		mapper.map(entity, response);
		return ResponseEntity.ok(response);
	}

	public UserEntity deleteByUsername(String username) {
		if (repository.findById(username).isPresent()) {
			Optional<UserEntity> byId = repository.findById(username);
			repository.deleteById(username);
			authorityService.deleteAuthorities(username);
			return byId.get();
		} else {
			throw new OurRuntimeException(null, "Bu istifadəçi adı mövcud deyildir");
		}
	}

	public boolean updateUser(UserUpdateRequest request) {
		String oldUsername = request.getUsername();
		String newUsername = request.getNewUsername();
		if (repository.findById(oldUsername).isPresent()) {
			if (repository.findById(newUsername).isPresent() == true) {
				throw new OurRuntimeException(null, "Yeni istifadəçi adı mövcuddur!");
			}
			UserEntity entity = new UserEntity();
			UserEntity oldUser = repository.findById(oldUsername).get();
			mapper.map(oldUser, entity);
			mapper.map(request, entity);

			repository.updateMyUsername(oldUsername, newUsername);
			entity.setUsername(newUsername);
			repository.save(entity);
			authorityService.updateUserUsername(newUsername, oldUsername);
		} else {
			throw new OurRuntimeException(null, "Belə username mövcud deyil");
		}

		return true;
	}

}
