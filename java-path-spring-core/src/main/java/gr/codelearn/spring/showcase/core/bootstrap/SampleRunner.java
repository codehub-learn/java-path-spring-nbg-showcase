package gr.codelearn.spring.showcase.core.bootstrap;

import gr.codelearn.spring.showcase.core.base.BaseComponent;
import gr.codelearn.spring.showcase.core.component.SampleBean;
import gr.codelearn.spring.showcase.core.domain.User;
import gr.codelearn.spring.showcase.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SampleRunner extends BaseComponent implements CommandLineRunner {
	private final SampleBean sampleBean;
	private final UserService userService;

	@Override
	public void run(final String... args) throws Exception {
		var newUser = User.builder().id(1L).email("test@gmail.com").build();
		userService.register(newUser);

		logger.info(sampleBean.sayHello());
		logger.info(sampleBean.generateUser());
	}
}
