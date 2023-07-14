package gr.codelearn.spring.showcase.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@SequenceGenerator(name = "idGenerator", sequenceName = "OrderItem_Seq", initialValue = 1, allocationSize = 1)
public class OrderItem extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Product product;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Integer quantity;
}
