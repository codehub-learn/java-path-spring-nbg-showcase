package gr.codelearn.spring.showcase.lab.controller;

import gr.codelearn.spring.showcase.lab.domain.Category;
import gr.codelearn.spring.showcase.lab.mapper.BaseMapper;
import gr.codelearn.spring.showcase.lab.mapper.CategoryMapper;
import gr.codelearn.spring.showcase.lab.mapper.CategoryWithAggregateTotalsMapper;
import gr.codelearn.spring.showcase.lab.mapper.CourseMapper;
import gr.codelearn.spring.showcase.lab.service.BaseService;
import gr.codelearn.spring.showcase.lab.service.CategoryService;
import gr.codelearn.spring.showcase.lab.transfer.ApiResponse;
import gr.codelearn.spring.showcase.lab.transfer.KeyValue;
import gr.codelearn.spring.showcase.lab.transfer.resource.CategoryResource;
import gr.codelearn.spring.showcase.lab.transfer.resource.CategoryWithAggregateTotalsResource;
import gr.codelearn.spring.showcase.lab.transfer.resource.CourseResource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("categories")
public class CategoryController extends BaseController<Category, CategoryResource> {
	private final CategoryService categoryService;
	private final CategoryMapper categoryMapper;
	private final CourseMapper courseMapper;
	private final CategoryWithAggregateTotalsMapper categoryWithAggregateTotalsMapper;

	@Override
	protected BaseService<Category, Long> getBaseService() {
		return categoryService;
	}

	@Override
	protected BaseMapper<Category, CategoryResource> getMapper() {
		return categoryMapper;
	}

	@GetMapping(headers = {"Action=findAllWithAggregateTotals"})
	public ResponseEntity<ApiResponse<List<CategoryWithAggregateTotalsResource>>> findAllWithAggregateTotals() {
		return ResponseEntity.ok(
				ApiResponse.<List<CategoryWithAggregateTotalsResource>>builder()
						   .data(categoryWithAggregateTotalsMapper.toResources(categoryService.findAllCategoriesFull()))
						   .build());
	}

	@Override
	@GetMapping("/{categoryId}")
	public ResponseEntity<ApiResponse<CategoryResource>> get(@PathVariable("categoryId") final Long categoryId) {
		return ResponseEntity.ok(
				ApiResponse.<CategoryResource>builder()
						   .data(categoryMapper.toFullResource(categoryService.getFullCategory(categoryId)))
						   .build());
	}

	@PostMapping("/{categoryId}/courses")
	public ResponseEntity<ApiResponse<CategoryResource>> createCourse(@PathVariable("categoryId") final Long categoryId,
																	  @Valid @RequestBody final CourseResource courseResource) {
		categoryService.addCourse(categoryId, courseMapper.toDomain(courseResource));
		return ResponseEntity.ok(
				ApiResponse.<CategoryResource>builder()
						   .data(categoryMapper.toFullResource(categoryService.getFullCategory(categoryId)))
						   .build());
	}

	@GetMapping("/{categoryId}/courses/{courseId}")
	public ResponseEntity<ApiResponse<CourseResource>> getCourse(@PathVariable("categoryId") final Long categoryId,
																 @PathVariable("courseId") final Long courseId) {
		return ResponseEntity.ok(
				ApiResponse.<CourseResource>builder()
						   .data(courseMapper.toResource(categoryService.getCourse(categoryId, courseId)))
						   .build());
	}

	@PutMapping("/{categoryId}/courses/{courseId}")
	public ResponseEntity<ApiResponse<CategoryResource>> updateCourse(@PathVariable("categoryId") final Long categoryId,
																	  @PathVariable("courseId") final Long courseId,
																	  @Valid @RequestBody final CourseResource courseResource) {
		categoryService.updateCourse(categoryId, courseId, courseMapper.toDomain(courseResource));
		return ResponseEntity.ok(
				ApiResponse.<CategoryResource>builder()
						   .data(categoryMapper.toFullResource(categoryService.getFullCategory(categoryId)))
						   .build());
	}

	@DeleteMapping("/{categoryId}/courses/{courseId}")
	public ResponseEntity<ApiResponse<CategoryResource>> deleteCourse(@PathVariable("categoryId") final Long categoryId,
																	  @PathVariable("courseId") final Long courseId) {
		categoryService.deleteCourse(categoryId, courseId);
		return ResponseEntity.ok(
				ApiResponse.<CategoryResource>builder()
						   .data(categoryMapper.toFullResource(categoryService.getFullCategory(categoryId)))
						   .build());
	}

	@GetMapping(headers = {"Action=getMostPopularCourses"})
	public ResponseEntity<ApiResponse<List<KeyValue<CourseResource, Long>>>> getMostPopularCourses() {
		return ResponseEntity.ok(
				ApiResponse.<List<KeyValue<CourseResource, Long>>>builder()
						   .data(categoryService.getFiveMostPopularCourses()
												.stream()
												.map(kv -> new KeyValue<>(courseMapper.toResource(kv.getKey()),
																		  kv.getValue()))
												.toList())
						   .build());
	}

	@GetMapping(headers = {"Action=getCoursesWithAverageGrades"})
	public ResponseEntity<ApiResponse<List<KeyValue<CourseResource, Double>>>> getCoursesWithAverageGrades() {
		return ResponseEntity.ok(
				ApiResponse.<List<KeyValue<CourseResource, Double>>>builder()
						   .data(categoryService.getCoursesWithAverageGrades()
												.stream()
												.map(kv -> new KeyValue<>(courseMapper.toResource(kv.getKey()),
																		  kv.getValue()))
												.toList())
						   .build());
	}
}
