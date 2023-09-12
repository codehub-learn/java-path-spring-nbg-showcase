package gr.codelearn.spring.showcase.lab.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "idGenerator", sequenceName = "CATEGORIES_SEQ", allocationSize = 1)
public class Category extends BaseModel {
	@Column(nullable = false)
	private String name;

	@ToString.Exclude
	@Builder.Default
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Course> courses = new HashSet<>();
}
