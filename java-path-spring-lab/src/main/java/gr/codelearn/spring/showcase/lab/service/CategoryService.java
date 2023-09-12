package gr.codelearn.spring.showcase.lab.service;

import gr.codelearn.spring.showcase.lab.domain.Category;
import gr.codelearn.spring.showcase.lab.domain.Course;
import gr.codelearn.spring.showcase.lab.transfer.KeyValue;

import java.util.List;

public interface CategoryService extends BaseService<Category, Long> {

	List<Category> findAllCategoriesFull();

	Category getFullCategory(Long categoryId);

	void addCourse(Long categoryId, Course course);

	Course getCourse(Long categoryId, Long courseId);

	void updateCourse(Long categoryId, Long courseId, Course course);

	void deleteCourse(Long categoryId, Long courseId);

	List<KeyValue<Course, Long>> getFiveMostPopularCourses();

	List<KeyValue<Course, Double>> getCoursesWithAverageGrades();
}
