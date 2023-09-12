package gr.codelearn.spring.showcase.lab.repository;

import gr.codelearn.spring.showcase.lab.domain.Category;
import gr.codelearn.spring.showcase.lab.domain.Course;
import gr.codelearn.spring.showcase.lab.transfer.KeyValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query("""
			select c
			from Category c
			left join fetch c.courses
			where c.id = :categoryId
			""")
	Category getFullCategory(Long categoryId);


	@Query("""
			select c
			from Category c
			left join fetch c.courses
			""")
	List<Category> findAllCategoriesFull();

	@Query("""
			select new gr.codelearn.spring.showcase.lab.transfer.KeyValue(e.course, count(*))
			from Enrollment e
			group by e.course
			order by count(*) desc
			limit 5
			""")
	List<KeyValue<Course, Long>> getFiveMostPopularCourses();

	@Query("""
			select new gr.codelearn.spring.showcase.lab.transfer.KeyValue(e.course, avg(e.grade))
			from Enrollment e
			group by e.id
			""")
	List<KeyValue<Course, Double>> getCoursesWithAverageGrades();
}
