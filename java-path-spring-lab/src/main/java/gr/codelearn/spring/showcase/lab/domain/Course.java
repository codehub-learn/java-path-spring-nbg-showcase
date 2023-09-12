package gr.codelearn.spring.showcase.lab.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "idGenerator", sequenceName = "COURSES_SEQ", allocationSize = 1)
public class Course extends BaseModel {
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private BigDecimal price;

	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Category category;
}
