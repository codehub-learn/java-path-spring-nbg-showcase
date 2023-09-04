package gr.codelearn.spring.showcase.app.service;

import gr.codelearn.spring.showcase.app.model.Category;

public interface CategoryService extends BaseService<Category, Long> {
	Category getReference(Long id);

	Category findByDescription(String description);
}
