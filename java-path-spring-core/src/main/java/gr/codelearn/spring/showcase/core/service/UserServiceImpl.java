package gr.codelearn.spring.showcase.core.service;

import gr.codelearn.spring.showcase.core.base.BaseComponent;
import gr.codelearn.spring.showcase.core.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends BaseComponent implements UserService {
	@Override
	public boolean register(final User user) {
		logger.debug("Created {}.", user);
		return true;
	}

	@Override
	public List<User> getUsers() {
		return null;
	}

	@Override
	public User getUser(final Long id) {
		return null;
	}

	@Override
	public User getUser(final String email) {
		return null;
	}
}
