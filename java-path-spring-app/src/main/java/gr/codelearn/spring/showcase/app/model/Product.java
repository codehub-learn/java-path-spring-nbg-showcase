package gr.codelearn.spring.showcase.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@SequenceGenerator(name = "idGenerator", sequenceName = "Product_Seq", initialValue = 1, allocationSize = 1)
public class Product extends BaseEntity{
    @Column(nullable = false)
    @NotNull
    private String serial;
    @NotNull
    private String name;
    @NotNull
    private BigDecimal price;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Category category;
}
