package az.developia.marketshop.service;

import org.springframework.stereotype.Service;

import az.developia.marketshop.exception.OurRuntimeException;
import az.developia.marketshop.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorityService {
	private final AuthorityRepository repository;

	public void updateUserUsername(String newUsername, String oldUsername) {
		if (repository.countByUsername(newUsername) > 0) {
			throw new OurRuntimeException(null, "Yeni Istifadəçi adı Hüquqlar bölməsində mövcuddur!");
		} else {
			repository.updateUserUsername(newUsername, oldUsername);
		}
	}

	public void saveAdminAuthority(String username) {
		if (username != null) {
			repository.addAdminAut(username);
		} else {
			throw new OurRuntimeException(null, "Istifadəçi adını boş qoymaq olmaz!");
		}
	};

	public void saveCustomerAuthority(String username) {
		if (username != null) {
			repository.addCustomerAut(username);
		} else {
			throw new OurRuntimeException(null, "Istifadəçi adını boş qoymaq olmaz!");
		}

	}

	public void deleteAuthorities(String username) {
		if (username != null) {
			repository.deleteUserAuthorities(username);
		} else {
			throw new OurRuntimeException(null, "Istifadəçi adını boş qoymaq olmaz!");
		}
	}

}
