package gr.codelearn.spring.showcase.app.bootstrap;

import gr.codelearn.spring.showcase.app.base.BaseComponent;
import gr.codelearn.spring.showcase.app.model.Category;
import gr.codelearn.spring.showcase.app.service.CategoryService;
import gr.codelearn.spring.showcase.app.service.CustomerService;
import gr.codelearn.spring.showcase.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TestRunner extends BaseComponent implements CommandLineRunner {

	private final ProductService productService;
	private final CategoryService categoryService;
	private final CustomerService customerService;

	@Override
	public void run(final String... args) throws Exception {
		final List<Category> mobilePhones = categoryService.findAllByDescription("Mobile Phones");
		logger.info("{}", mobilePhones);
	}
}
