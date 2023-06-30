package gr.codelearn.spring.showcase.core.bootstrap;

import gr.codelearn.spring.showcase.core.base.BaseComponent;
import gr.codelearn.spring.showcase.core.component.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnBean(gr.codelearn.spring.showcase.core.component.DataSource.class)
@Order(2)
@RequiredArgsConstructor
public class DatabaseInformationRunner extends BaseComponent implements CommandLineRunner {
	private final DataSource dataSource;

	@Override
	public void run(String... args) throws Exception {
		dataSource.createConnectionPool();
	}
}
