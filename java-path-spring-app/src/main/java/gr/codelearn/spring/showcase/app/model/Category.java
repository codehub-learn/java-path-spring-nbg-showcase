package gr.codelearn.spring.showcase.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@SequenceGenerator(name = "idGenerator", sequenceName = "Category_Seq", initialValue = 1, allocationSize = 1)
public class Category extends BaseEntity{
    @NotNull
    private String description;
}
