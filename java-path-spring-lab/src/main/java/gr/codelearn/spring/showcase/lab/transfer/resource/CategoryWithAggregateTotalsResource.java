package gr.codelearn.spring.showcase.lab.transfer.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class CategoryWithAggregateTotalsResource extends BaseResource {
	private String name;
	private Integer totalCourses;
}
