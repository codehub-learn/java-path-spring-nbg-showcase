package gr.codelearn.spring.showcase.core.component;

import gr.codelearn.spring.showcase.core.base.BaseComponent;
import gr.codelearn.spring.showcase.core.domain.User;
import org.springframework.stereotype.Component;

@Component
public class SampleBean extends BaseComponent {
	public String sayHello() {
		return "Hello from Sample bean";
	}

	public String generateUser() {
		var user = User.builder().firstname("Costas").lastname("Giannacoulis").build();
		return user.toString();
	}
}
