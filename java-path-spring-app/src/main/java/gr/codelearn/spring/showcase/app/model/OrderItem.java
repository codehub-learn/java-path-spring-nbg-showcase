package gr.codelearn.spring.showcase.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
public class OrderItem extends BaseEntity {
    @ManyToOne
    @NotNull
    private Order order;
    @ManyToOne
    @NotNull
    private Product product;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Integer quantity;
}
