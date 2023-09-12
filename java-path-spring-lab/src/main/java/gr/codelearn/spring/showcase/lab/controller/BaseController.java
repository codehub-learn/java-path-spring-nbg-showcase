package gr.codelearn.spring.showcase.lab.controller;

import gr.codelearn.spring.showcase.lab.base.BaseComponent;
import gr.codelearn.spring.showcase.lab.domain.BaseModel;
import gr.codelearn.spring.showcase.lab.mapper.BaseMapper;
import gr.codelearn.spring.showcase.lab.service.BaseService;
import gr.codelearn.spring.showcase.lab.transfer.ApiResponse;
import gr.codelearn.spring.showcase.lab.transfer.resource.BaseResource;
import jakarta.validation.Valid;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class BaseController<T extends BaseModel, R extends BaseResource> extends BaseComponent {
	protected abstract BaseService<T, Long> getBaseService();

	protected abstract BaseMapper<T, R> getMapper();

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<R>> get(@PathVariable("id") final Long id) {
		return ResponseEntity.ok(
				ApiResponse.<R>builder().data(getMapper().toResource(getBaseService().get(id))).build());
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<R>>> findAll() {
		return ResponseEntity.ok(
				ApiResponse.<List<R>>builder().data(getMapper().toResources(getBaseService().findAll())).build());
	}

	@PostMapping
	public ResponseEntity<ApiResponse<R>> create(@Valid @RequestBody final R resource) {
		return new ResponseEntity<>(ApiResponse.<R>builder()
											   .data(getMapper().toResource(
													   getBaseService().create(getMapper().toDomain(resource))))
											   .build(), getNoCacheHeaders(), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable("id") final Long id, @Valid @RequestBody final R resource) {
		getBaseService().update(id, getMapper().toDomain(resource));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") final Long id) {
		getBaseService().deleteById(id);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@Valid @RequestBody final R resource) {
		if (getBaseService().exists(getMapper().toDomain(resource))) {
			getBaseService().delete(getMapper().toDomain(resource));
		}
	}

	protected CacheControl getCacheHeaders(int cacheDuration) {
		// https://www.imperva.com/learn/performance/cache-control/
		// Note: Header "Expires" is ignored in max-age is set.
		// response.addHeader("Cache-Control", "max-age=60, must-revalidate, no-transform");
		return CacheControl.maxAge(cacheDuration, TimeUnit.SECONDS).noTransform().mustRevalidate();
	}

	protected HttpHeaders getNoCacheHeaders() {
		final HttpHeaders headers = new HttpHeaders();
		// HTTP 1.1 cache control header
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		// HTTP 1.0 cache control header
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		return headers;
	}

	protected HttpHeaders getDownloadHeaders(final String filename) {
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=" + filename);
		return headers;
	}
}
