package gr.codelearn.spring.showcase.app.service;

import gr.codelearn.spring.showcase.app.model.Customer;
import gr.codelearn.spring.showcase.app.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService extends AbstractBaseService<Customer> {

	private final CustomerRepository customerRepository;

	@Override
	protected JpaRepository<Customer, Long> getRepository() {
		return customerRepository;
	}

	public Customer findByEmail(String email){
		return customerRepository.findByEmail(email).orElseThrow(() -> new RuntimeException());
	}
}
