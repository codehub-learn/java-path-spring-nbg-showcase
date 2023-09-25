package gr.codelearn.spring.showcase.app.service;

import gr.codelearn.spring.showcase.app.domain.Category;
import gr.codelearn.spring.showcase.app.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@CacheConfig(cacheNames = {"categories"}, keyGenerator = "customCacheKeyGenerator")
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
	@Cacheable
	public List<Category> findAll() {
		logger.trace("Categories do not exist in cache, retrieving from repository.");
		return getRepository().findAll();
	}

	@Override
	@Cacheable
	public Category get(final Long id) {
		logger.trace("Category [{}] do not exist in cache, retrieving from repository.", id);
		return getRepository().findById(id).orElseThrow(NoSuchElementException::new);

	}

	@Override
	@Cacheable
	public Category findByDescription(String description) {
		logger.info("Fetching from repository.");
		return categoryRepository.findByDescription(description);
	}

	@Caching(evict = {@CacheEvict(value = "categories", allEntries = true)})
	public void clearCache() {
		logger.trace("Evicted cache contents.");
	}
}
