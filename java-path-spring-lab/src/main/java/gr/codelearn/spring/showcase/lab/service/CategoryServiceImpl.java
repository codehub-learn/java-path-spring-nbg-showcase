package gr.codelearn.spring.showcase.lab.service;

import gr.codelearn.spring.showcase.lab.domain.Category;
import gr.codelearn.spring.showcase.lab.domain.Course;
import gr.codelearn.spring.showcase.lab.mapper.internal.BaseInternalMapper;
import gr.codelearn.spring.showcase.lab.mapper.internal.CategoryInternalMapper;
import gr.codelearn.spring.showcase.lab.mapper.internal.CourseInternalMapper;
import gr.codelearn.spring.showcase.lab.repository.CategoryRepository;
import gr.codelearn.spring.showcase.lab.transfer.KeyValue;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {
	private final CategoryRepository categoryRepository;
	private final CategoryInternalMapper categoryInternalMapper;
	private final CourseInternalMapper courseInternalMapper;

	@Override
	public JpaRepository<Category, Long> getRepository() {
		return categoryRepository;
	}

	@Override
	public BaseInternalMapper<Category> getInternalMapper() {
		return categoryInternalMapper;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Category> findAllCategoriesFull() {
		return categoryRepository.findAllCategoriesFull();
	}

	@Override
	@Transactional(readOnly = true)
	public Category getFullCategory(final Long categoryId) {
		return categoryRepository.getFullCategory(categoryId);
	}

	@Override
	public void addCourse(final Long categoryId, final Course course) {
		var category = get(categoryId);
		addCourse(category, course);
	}

	private void addCourse(final Category category, final Course course) {
		category.getCourses().add(course);
		course.setCategory(category);
	}

	@Override
	@Transactional(readOnly = true)
	public Course getCourse(final Long categoryId, final Long courseId) {
		var category = get(categoryId);
		return getCourse(category, courseId);
	}

	private Course getCourse(final Category category, final Long courseId) {
		return category.getCourses()
					   .stream()
					   .filter(course -> course.getId().equals(courseId)).findFirst()
					   .orElseThrow(() -> new NoSuchElementException(String.format("Course with id %d does not exist",
																					 courseId)));
	}

	@Override
	public void updateCourse(final Long categoryId, final Long courseId, final Course course) {
		var category = get(categoryId);
		updateCourse(category, courseId, course);
	}

	private void updateCourse(final Category category, final Long courseId, final Course course) {
		var retrievedCourse = getCourse(category, courseId);
		courseInternalMapper.update(course, retrievedCourse);
	}

	@Override
	public void deleteCourse(final Long categoryId, final Long courseId) {
		var category = get(categoryId);
		deleteCourse(category, courseId);
	}

	private void deleteCourse(final Category category, final Long courseId) {
		var course = getCourse(category, courseId);
		category.getCourses().remove(course);
		course.setCategory(null);
	}

	@Override
	public List<KeyValue<Course, Long>> getFiveMostPopularCourses() {
		return categoryRepository.getFiveMostPopularCourses();
	}

	@Override
	public List<KeyValue<Course, Double>> getCoursesWithAverageGrades() {
		return categoryRepository.getCoursesWithAverageGrades();
	}
}
