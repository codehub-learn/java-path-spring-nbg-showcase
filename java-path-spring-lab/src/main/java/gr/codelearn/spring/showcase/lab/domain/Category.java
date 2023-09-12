package gr.codelearn.spring.showcase.lab.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
public class Category extends BaseModel {
	private String name;
	private Set<Course> courses;
}
