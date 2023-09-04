package gr.codelearn.spring.showcase.app.service;

import gr.codelearn.spring.showcase.app.model.Category;
import gr.codelearn.spring.showcase.app.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {
	private final CategoryRepository categoryRepository;

	@Override
	public JpaRepository<Category, Long> getRepository() {
		return categoryRepository;
	}

	@Override
	public Category getReference(final Long id) {
		return categoryRepository.getReferenceById(id);
	}

	@Override
	public Category findByDescription(String description) {
		logger.info("Fetching from repository.");
		return categoryRepository.findByDescription(description);
	}
}
