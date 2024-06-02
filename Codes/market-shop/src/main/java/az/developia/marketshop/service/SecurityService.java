package az.developia.marketshop.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
	public String findUsername() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return username;
	}
}