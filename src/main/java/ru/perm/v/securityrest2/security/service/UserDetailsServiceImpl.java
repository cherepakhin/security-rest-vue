package ru.perm.v.securityrest2.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.perm.v.securityrest2.model.User;
import ru.perm.v.securityrest2.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger logger = LoggerFactory
			.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(
			String username) throws UsernameNotFoundException {
		User user =
				userRepository.findByUsername(username).orElseThrow(
						() -> new UsernameNotFoundException(
								"User Not Found with username: " + username));

		logger.warn(user.toString());
		return UserDetailsImpl.build(user);
	}
}
