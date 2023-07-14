package gr.codelearn.spring.showcase.app.repository;

import gr.codelearn.spring.showcase.app.model.Customer;
import gr.codelearn.spring.showcase.app.model.CustomerCategory;
import gr.codelearn.spring.showcase.app.transfer.KeyValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Optional<Customer> findByEmail(String email);

	@Query(name = "customersByCustomerCategories")
	List<Customer> customersByCustomerCategories(List<CustomerCategory> customerCategories);

	@Query(name = "purchasedMostExpensiveProduct", nativeQuery = true)
	List<KeyValue<String, Long>> purchasedMostExpensiveProduct();
}
