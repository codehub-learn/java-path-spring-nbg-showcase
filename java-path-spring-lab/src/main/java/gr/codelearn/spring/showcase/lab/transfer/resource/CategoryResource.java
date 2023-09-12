package gr.codelearn.spring.showcase.lab.transfer.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
public class CategoryResource extends BaseResource {
	private String name;
	private List<CourseResource> courses;
}
