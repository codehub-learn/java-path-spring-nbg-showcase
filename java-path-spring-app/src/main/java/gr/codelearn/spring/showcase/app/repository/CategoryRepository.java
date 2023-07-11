package gr.codelearn.spring.showcase.app.repository;

import gr.codelearn.spring.showcase.app.model.Category;
import gr.codelearn.spring.showcase.app.model.Customer;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>  {
	List<Category> findAllByDescription(String description);
}
