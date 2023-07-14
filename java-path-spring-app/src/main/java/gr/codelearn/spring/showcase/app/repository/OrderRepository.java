package gr.codelearn.spring.showcase.app.repository;

import gr.codelearn.spring.showcase.app.model.Order;
import gr.codelearn.spring.showcase.app.transfer.KeyValue;
import gr.codelearn.spring.showcase.app.transfer.PurchasesPerCustomerCategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query("SELECT o FROM Order o JOIN FETCH o.customer JOIN FETCH o.orderItems oi JOIN FETCH oi.product WHERE o.id = :id")
	Optional<Order> findLazyById(Long id);

	@Query("SELECT o FROM Order o JOIN FETCH o.customer JOIN FETCH o.orderItems oi JOIN FETCH oi.product")
	List<Order> findAllLazy();

	@Query("SELECT NEW gr.codelearn.spring.showcase.app.transfer.KeyValue(concat(c.firstname, ' ', c.lastname), avg(o.totalCost)) "
			+ "FROM Order o JOIN o.customer c GROUP BY c ORDER BY c.lastname, c.firstname DESC")
	List<KeyValue<String, BigDecimal>> findAverageOrderCostPerCustomer();

	@Query(value = "SELECT C.CUSTOMERCATEGORY AS category, COUNT(O.*) AS purchases, SUM(O.TOTALCOST) AS cost " +
			"FROM ORDERS O, CUSTOMER C WHERE O.CUSTOMER_ID = C.ID GROUP BY C.CUSTOMERCATEGORY",
			nativeQuery = true)
	List<PurchasesPerCustomerCategoryDto> findTotalNumberAndCostOfPurchasesPerCustomerCategory();

	@Query(name = "findTotalNumberAndCostOfPurchasesPerCustomerCategory", nativeQuery = true)
	List<PurchasesPerCustomerCategoryDto> findTotalNumberAndCostOfPurchasesPerCustomerCategory2();

	@Query("SELECT o.paymentMethod AS category, o.id AS purchases, o.totalCost AS cost  FROM Order o WHERE o.id = 1")
	PurchasesPerCustomerCategoryDto exampleQuery();
}
