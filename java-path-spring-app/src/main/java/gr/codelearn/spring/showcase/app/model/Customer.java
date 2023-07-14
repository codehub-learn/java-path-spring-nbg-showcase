package gr.codelearn.spring.showcase.app.model;

import gr.codelearn.spring.showcase.app.transfer.KeyValue;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@NamedQuery(name = "customersByCustomerCategories", query = "SELECT c FROM Customer c WHERE c.customerCategory IN :customerCategories")
@NamedNativeQuery(
		name = "purchasedMostExpensiveProduct",
		query =
				"SELECT C.FIRSTNAME || ' ' || C.LASTNAME as fullname, COUNT(*) as purchases " +
						"FROM ORDERS O, ORDERITEM OI, CUSTOMER C " +
						"WHERE OI.ORDER_ID = O.ID " +
						"AND O.CUSTOMER_ID = C.ID " +
						"AND OI.PRODUCT_ID = (SELECT TOP 1 ID FROM PRODUCT ORDER BY PRICE DESC) " +
						"GROUP BY O.CUSTOMER_ID " +
						"ORDER BY purchases, c.lastname, c.firstname",
		resultSetMapping = "purchasedMostExpensiveProductResultSetMapping"
)
@SqlResultSetMapping(
		name = "purchasedMostExpensiveProductResultSetMapping",
		classes = @ConstructorResult(
				targetClass = KeyValue.class,
				columns = {
						@ColumnResult(name = "fullname", type = String.class),
						@ColumnResult(name = "purchases", type = Long.class)
				}
		)
)
@Table(indexes = {@Index(columnList = "email")})
@SequenceGenerator(name = "idGenerator", sequenceName = "Customer_Seq", initialValue = 1, allocationSize = 1)
public class Customer extends BaseEntity{
    @NotNull(message = "{email.null}")
    @Email(message = "{email.format}")
	@Column(unique = true)
    private String email;
    @NotNull(message = "{firstname.null}")
    //@Pattern(regexp = ".")
    @NotBlank // cannot save if: null, "", "    "
    private String firstname;
    @NotNull(message = "{lastname.null}")
    private String lastname;
    //@Transient
    @Min(value = 18, message = "{age.min}")
    @Max(value = 110, message = "{age.max}")
    private Integer age;
    @Size(max = 50, message = "{address.length}")
    private String address;
    @Enumerated(EnumType.STRING)
    private CustomerCategory customerCategory;
}
