package gr.codelearn.spring.showcase.app.service;

import gr.codelearn.spring.showcase.app.model.Customer;
import gr.codelearn.spring.showcase.app.model.Order;
import gr.codelearn.spring.showcase.app.repository.CustomerRepository;
import gr.codelearn.spring.showcase.app.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService extends AbstractBaseService<Order> {

	private final OrderRepository orderRepository;

	@Override
	protected JpaRepository<Order, Long> getRepository() {
		return orderRepository;
	}
}
