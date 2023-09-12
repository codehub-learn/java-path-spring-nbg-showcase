package gr.codelearn.spring.showcase.lab.service;

import gr.codelearn.spring.showcase.lab.domain.Student;

public interface StudentService extends BaseService<Student, Long> {

	Student getFullStudent(Long studentId);

	void enroll(Long studentId, Long categoryId, Long courseId);

	void getEnrollment(Long studentId, Long courseId);

	void quit(Long studentId, Long courseId);

	void grade(Long studentId, Long courseId, Integer grade);
}
