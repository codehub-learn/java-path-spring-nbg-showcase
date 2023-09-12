package gr.codelearn.spring.showcase.lab.transfer.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString(callSuper = true)
public class CourseResource extends BaseResource{
	private String name;
	private BigDecimal price;
}
