package gr.codelearn.spring.showcase.lab.bootstrap;

import gr.codelearn.spring.showcase.lab.base.BaseComponent;
import gr.codelearn.spring.showcase.lab.domain.Category;
import gr.codelearn.spring.showcase.lab.domain.Course;
import gr.codelearn.spring.showcase.lab.domain.Student;
import gr.codelearn.spring.showcase.lab.service.CategoryService;
import gr.codelearn.spring.showcase.lab.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Profile("sample-content-creator")
@RequiredArgsConstructor
public class SampleContentCreator extends BaseComponent implements CommandLineRunner {
	private final StudentService studentService;
	private final CategoryService categoryService;

	@Override
	public void run(final String... args) throws Exception {
		studentService.createAll(List.of(
				Student.builder().name("Dionysis").email("dionysis@athtech.gr").build(),
				Student.builder().name("Giannis").email("giannis@athtech.gr").build(),
				Student.builder().name("Nikos").email("nikos@athtech.gr").build(),
				Student.builder().name("Kostas").email("kostas@athtech.gr").build(),
				Student.builder().name("Dimitris").email("dimitris@athtech.gr").build(),
				Student.builder().name("Panagiotis").email("panagiotis@athtech.gr").build()
										));

		categoryService.createAll(List.of(
				Category.builder().name("Software").build(),
				Category.builder().name("Physics").build(),
				Category.builder().name("Math").build()
										 ));

		categoryService.addCourse(1L, Course.builder().name("Java").price(new BigDecimal("62.5")).build());
		categoryService.addCourse(1L, Course.builder().name("C#").price(new BigDecimal("52.5")).build());
		categoryService.addCourse(1L, Course.builder().name("Python").price(new BigDecimal("32.5")).build());

		categoryService.addCourse(2L, Course.builder().name("Nuclear physics").price(new BigDecimal("53.5")).build());
		categoryService.addCourse(2L, Course.builder().name("Relativity theory").price(new BigDecimal("54.5")).build());

		categoryService.addCourse(3L, Course.builder().name("Calculus").price(new BigDecimal("30.5")).build());
		categoryService.addCourse(3L, Course.builder().name("Linear Algebra").price(new BigDecimal("32.5")).build());

		studentService.enroll(1L, 1L, 1L);
		studentService.enroll(1L, 1L, 2L);
		studentService.enroll(1L, 2L, 4L);

		studentService.grade(1L, 1L, 70);
		studentService.grade(1L, 2L, 65);

		studentService.enroll(2L, 2L, 4L);
		studentService.enroll(2L, 3L, 6L);
		studentService.enroll(2L, 3L, 7L);

		studentService.grade(2L, 4L, 45);
		studentService.grade(2L, 6L, 60);
		studentService.grade(2L, 7L, 90);

		studentService.enroll(3L, 1L, 1L);
		studentService.enroll(3L, 1L, 2L);
		studentService.enroll(3L, 1L, 3L);
		studentService.enroll(3L, 2L, 4L);
		studentService.enroll(3L, 2L, 5L);

		studentService.grade(3L, 1L, 45);
		studentService.grade(3L, 2L, 50);
		studentService.grade(3L, 3L, 55);
		studentService.grade(3L, 4L, 35);
		studentService.grade(3L, 5L, 85);
	}
}
