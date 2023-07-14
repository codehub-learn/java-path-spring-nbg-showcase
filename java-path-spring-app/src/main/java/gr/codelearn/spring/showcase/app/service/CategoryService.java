package gr.codelearn.spring.showcase.app.service;

import gr.codelearn.spring.showcase.app.model.Category;
import gr.codelearn.spring.showcase.app.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService extends AbstractBaseService<Category> {

	private final CategoryRepository categoryRepository;

	@Override
	public void delete(Category category){
		throw new UnsupportedOperationException("You are not allowed to perform this operation");
	}

	@Override
	protected JpaRepository<Category, Long> getRepository() {
		return categoryRepository;
	}

	public List<Category> findAllByDescription(String description) {
		return categoryRepository.findAllByDescription(description);
	}
}
