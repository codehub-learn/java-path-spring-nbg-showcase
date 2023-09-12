package gr.codelearn.spring.showcase.lab.controller;

import gr.codelearn.spring.showcase.lab.domain.Student;
import gr.codelearn.spring.showcase.lab.mapper.BaseMapper;
import gr.codelearn.spring.showcase.lab.mapper.StudentMapper;
import gr.codelearn.spring.showcase.lab.service.BaseService;
import gr.codelearn.spring.showcase.lab.service.StudentService;
import gr.codelearn.spring.showcase.lab.transfer.ApiResponse;
import gr.codelearn.spring.showcase.lab.transfer.resource.StudentResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("students")
public class StudentController extends BaseController<Student, StudentResource> {
	private final StudentService studentService;
	private final StudentMapper studentMapper;


	@Override
	protected BaseService<Student, Long> getBaseService() {
		return studentService;
	}

	@Override
	protected BaseMapper<Student, StudentResource> getMapper() {
		return studentMapper;
	}

	@Override
	@GetMapping("/{studentId}")
	public ResponseEntity<ApiResponse<StudentResource>> get(@PathVariable("studentId") final Long studentId) {
		return ResponseEntity.ok(
				ApiResponse.<StudentResource>builder()
						   .data(studentMapper.toFullResource(studentService.getFullStudent(studentId)))
						   .build());
	}

	@PostMapping("/{studentId}/categories/{categoryId}/courses/{courseId}")
	public ResponseEntity<ApiResponse<StudentResource>> enroll(@PathVariable("studentId") final Long studentId,
															   @PathVariable("categoryId") final Long categoryId,
															   @PathVariable("courseId") final Long courseId) {
		studentService.enroll(studentId, categoryId, courseId);
		return ResponseEntity.ok(
				ApiResponse.<StudentResource>builder()
						   .data(studentMapper.toFullResource(studentService.getFullStudent(studentId)))
						   .build());
	}

	@DeleteMapping("/{studentId}/categories/{categoryId}/courses/{courseId}")
	public ResponseEntity<ApiResponse<StudentResource>> quit(@PathVariable("studentId") final Long studentId,
															 @PathVariable("courseId") final Long courseId) {
		studentService.quit(studentId, courseId);
		return ResponseEntity.ok(
				ApiResponse.<StudentResource>builder()
						   .data(studentMapper.toFullResource(studentService.getFullStudent(studentId)))
						   .build());
	}

	@PatchMapping("/{studentId}/categories/{categoryId}/courses/{courseId}")
	public ResponseEntity<ApiResponse<StudentResource>> grade(@PathVariable("studentId") final Long studentId,
															 @PathVariable("courseId") final Long courseId,
															 @RequestParam("grade") final Integer grade) {
		studentService.grade(studentId, courseId, grade);
		return ResponseEntity.ok(
				ApiResponse.<StudentResource>builder()
						   .data(studentMapper.toFullResource(studentService.getFullStudent(studentId)))
						   .build());
	}
}
