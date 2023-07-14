package gr.codelearn.spring.showcase.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Orders")
@SequenceGenerator(name = "idGenerator", sequenceName = "Orders_Seq", initialValue = 1, allocationSize = 1)
public class Order extends BaseEntity {
    @ManyToOne(optional = false, fetch = FetchType.LAZY) // fetch: LAZY, EAGER
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
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<OrderItem> orderItems = new ArrayList<>();
}
