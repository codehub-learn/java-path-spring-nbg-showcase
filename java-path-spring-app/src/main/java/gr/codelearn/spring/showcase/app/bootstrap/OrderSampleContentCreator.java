package gr.codelearn.spring.showcase.app.bootstrap;

import gr.codelearn.spring.showcase.app.base.BaseComponent;
import gr.codelearn.spring.showcase.app.model.Customer;
import gr.codelearn.spring.showcase.app.model.Order;
import gr.codelearn.spring.showcase.app.model.PaymentMethod;
import gr.codelearn.spring.showcase.app.service.CustomerService;
import gr.codelearn.spring.showcase.app.service.OrderService;
import gr.codelearn.spring.showcase.app.service.ProductService;
import gr.codelearn.spring.showcase.app.transfer.KeyValue;
import gr.codelearn.spring.showcase.app.transfer.PurchasesPerCustomerCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Profile("generate-orders")
@RequiredArgsConstructor
public class OrderSampleContentCreator extends BaseComponent implements CommandLineRunner {

	private final CustomerService customerService;
	private final OrderService orderService;
	private final ProductService productService;

	@Override
	public void run(final String... args) throws Exception {
		// Get all customers
		customerService.findAll().forEach(c -> logger.info("{}", c));

		// We don't mind if a "find" method returns a null
		logger.info("Does customer exist? {}.", (customerService.findByEmail("c.giannacoulis@codehub.gr") != null));
		logger.info("Does customer exist? {}.", (customerService.findByEmail("non-existing@gmail.com") != null));

		// Load customer and create an order by adding/updating/removing content before checking it out
		Customer firstCustomer = customerService.findByEmail("c.giannacoulis@codehub.gr");
		Order firstOrder = orderService.initiateOrder(firstCustomer);

		// Add item(s) both existing and non-existing
		orderService.addItem(firstOrder, productService.findBySerial("SN1000-0001"), 2);
		orderService.addItem(firstOrder, productService.findBySerial("SN1100-0001"), 1);
		orderService.addItem(firstOrder, productService.findBySerial("SN1000-0004"), 1);
		orderService.addItem(firstOrder, productService.findBySerial("SN1000-0008"), 1);
		// Update item(s)
		orderService.addItem(firstOrder, productService.findBySerial("SN1000-0001"), 1);
		orderService.updateItem(firstOrder, productService.findBySerial("SN1000-0004"), 2);
		// Remove item(s)
		orderService.removeItem(firstOrder, productService.findBySerial("SN1100-0001"));
		// Add some more item(s)
		orderService.addItem(firstOrder, productService.findBySerial("SN1300-0001"), 2);
		// Checkout order
		orderService.checkout(firstOrder, PaymentMethod.CREDIT_CARD);

		// Second customer and order
		Customer secondCustomer = customerService.findByEmail("john.porter@gmailx.com");
		Order secondOrder = orderService.initiateOrder(secondCustomer);
		// Add item(s) to second order
		orderService.addItem(secondOrder, productService.findBySerial("SN1000-0002"), 1);
		orderService.addItem(secondOrder, productService.findBySerial("SN1200-0001"), 1);
		orderService.addItem(secondOrder, productService.findBySerial("SN1200-0001"), 1);
		orderService.addItem(secondOrder, productService.findBySerial("SN1299-0001"), 1);
		// Checkout 2nd order
		orderService.checkout(secondOrder, PaymentMethod.WIRE_TRANSFER);

		// Third customer and order
		Customer thirdCustomer = customerService.findByEmail("malcolm.paker@gmailx.com");
		Order thirdOrder = orderService.initiateOrder(thirdCustomer);
		orderService.addItem(thirdOrder, productService.findBySerial("SN1000-0001"), 3);
		orderService.addItem(thirdOrder, productService.findBySerial("SN1000-0002"), 2);
		orderService.addItem(thirdOrder, productService.findBySerial("SN1000-0003"), 1);
		// Checkout 3rd order
		orderService.checkout(thirdOrder, PaymentMethod.CREDIT_CARD);

		// Fourth customer and order
		Customer fourthCustomer = customerService.findByEmail("terry.jones@gmailx.com");
		Order fourthOrder = orderService.initiateOrder(fourthCustomer);
		orderService.addItem(fourthOrder, productService.findBySerial("SN1300-0001"), 1);
		orderService.addItem(fourthOrder, productService.findBySerial("SN1400-0001"), 2);
		orderService.addItem(fourthOrder, productService.findBySerial("SN1500-0001"), 1);
		orderService.addItem(fourthOrder, productService.findBySerial("SN1000-0003"), 1);
		orderService.addItem(fourthOrder, productService.findBySerial("SN1000-0004"), 1);
		// Checkout 4th order
		orderService.checkout(fourthOrder, PaymentMethod.CREDIT_CARD);

		final Order order = orderService.getLazyById(2L);
		logger.info("testtest{}", order.getOrderItems());

		final List<KeyValue<String, BigDecimal>> averageOrderCostPerCustomer = orderService.findAverageOrderCostPerCustomer();
		for (final KeyValue<String, BigDecimal> stringBigDecimalKeyValue : averageOrderCostPerCustomer) {
			logger.info("Customer/Average Cost: {}", stringBigDecimalKeyValue);
		}

		final List<PurchasesPerCustomerCategoryDto> totalNumberAndCostOfPurchasesPerCustomerCategory = orderService.findTotalNumberAndCostOfPurchasesPerCustomerCategory();
		for (final PurchasesPerCustomerCategoryDto purchasesPerCustomerCategoryDto : totalNumberAndCostOfPurchasesPerCustomerCategory) {
			logger.info("category: {}, purchases: {}, cost {}", purchasesPerCustomerCategoryDto.getCategory(),
						purchasesPerCustomerCategoryDto.getPurchases(), purchasesPerCustomerCategoryDto.getCost());
		}

		final PurchasesPerCustomerCategoryDto exampleQuery = orderService.exampleQuery();
		logger.info("category: {}, purchases: {}, cost {}", exampleQuery.getCategory(),
					exampleQuery.getPurchases(), exampleQuery.getCost());

		final List<KeyValue<String, Long>> mostExpensiveProductPerCustomer =
				customerService.purchasedMostExpensiveProduct();
		for (final KeyValue<String, Long> stringLongKeyValue : mostExpensiveProductPerCustomer) {
			logger.info("{}", stringLongKeyValue);
		}
	}
}
