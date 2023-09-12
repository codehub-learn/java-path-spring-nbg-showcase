package gr.codelearn.spring.showcase.lab.service;

import gr.codelearn.spring.showcase.lab.domain.Course;
import gr.codelearn.spring.showcase.lab.domain.Enrollment;
import gr.codelearn.spring.showcase.lab.domain.Student;
import gr.codelearn.spring.showcase.lab.domain.composite.EnrollmentId;
import gr.codelearn.spring.showcase.lab.mapper.internal.BaseInternalMapper;
import gr.codelearn.spring.showcase.lab.mapper.internal.StudentInternalMapper;
import gr.codelearn.spring.showcase.lab.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
public class StudentServiceImpl extends BaseServiceImpl<Student> implements StudentService{
	private final StudentRepository studentRepository;
	private final StudentInternalMapper studentInternalMapper;
	private final CategoryService categoryService;

	@Override
	public JpaRepository<Student, Long> getRepository() {
		return studentRepository;
	}

	@Override
	public BaseInternalMapper<Student> getInternalMapper() {
		return studentInternalMapper;
	}

	@Override
	@Transactional(readOnly = true)
	public Student getFullStudent(final Long studentId) {
		return studentRepository.getFullStudent(studentId);
	}

	@Override
	public void enroll(final Long studentId, final Long categoryId, final Long courseId) {
		var student = get(studentId);
		var course  = categoryService.getCourse(categoryId, courseId);
		enroll(student, course, new EnrollmentId(studentId, courseId));
	}

	private void enroll(final Student student, final Course course, final EnrollmentId enrollmentId) {
		var enrollment = Enrollment.builder()
								   .id(enrollmentId)
								   .student(student)
								   .course(course)
								   .build();
		student.getEnrollments().add(enrollment);
	}

	@Override
	@Transactional(readOnly = true)
	public void getEnrollment(final Long studentId, final Long courseId) {
		var student = get(studentId);
	}

	private Enrollment getEnrollment(final Student student, final Long courseId) {
		return student.getEnrollments()
					  .stream()
					  .filter(e -> e.getId().getCourseId().equals(courseId))
					  .findFirst()
					  .orElseThrow(() -> new NoSuchElementException(String.format("%s is not enrolled in course with id %d",
																				  student.getName(),
																				  courseId)));
	}

	@Override
	public void quit(final Long studentId, final Long courseId) {
		var student = get(studentId);
		quit(student, courseId);
	}

	private void quit(final Student student, final Long courseId) {
		var enrollment = getEnrollment(student, courseId);
		student.getEnrollments().remove(enrollment);
		enrollment.setCourse(null);
		enrollment.setStudent(null);
	}

	@Override
	public void grade(final Long studentId, final Long courseId, final Integer grade) {
		var student = get(studentId);
		getEnrollment(student, courseId).setGrade(grade);
	}
}
