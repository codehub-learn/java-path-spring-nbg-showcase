package gr.codelearn.spring.showcase.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order extends BaseEntity {
    @ManyToOne(optional = false) // fetch: LAZY, EAGER
    @NotNull
    private Customer customer;
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @PastOrPresent
    private Date submitDate;
    @NotNull
    private PaymentMethod paymentMethod;
    @NotNull
    private BigDecimal totalCost;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<OrderItem> orderItems;
}
