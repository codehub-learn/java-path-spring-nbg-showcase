package gr.codelearn.spring.showcase.app.service;

import gr.codelearn.spring.showcase.app.model.Product;
import gr.codelearn.spring.showcase.app.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService extends AbstractBaseService<Product> {
	private final ProductRepository productRepository;

	@Override
	protected JpaRepository<Product, Long> getRepository() {
		return productRepository;
	}

	public Product findBySerial(String serial){
		return productRepository.findBySerial(serial);
	}
}
