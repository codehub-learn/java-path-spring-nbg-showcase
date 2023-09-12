package gr.codelearn.spring.showcase.lab.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
public class Course extends BaseModel {
	private String name;
	private BigDecimal price;
}
