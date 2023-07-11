package gr.codelearn.spring.showcase.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.NamedQuery;
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
public class Customer extends BaseEntity{
    @NotNull(message = "Cannot be null.")
    @Email
    private String email;
    @NotNull
    //@Pattern(regexp = ".")
    @NotBlank // cannot save if: null, "", "    "
    private String firstname;
    @NotNull
    private String lastname;
    //@Transient
    @Min(18)
    @Max(110)
    private Integer age;
    @Size(max = 50)
    private String address;
    @Enumerated(EnumType.STRING)
    private CustomerCategory customerCategory;
}
