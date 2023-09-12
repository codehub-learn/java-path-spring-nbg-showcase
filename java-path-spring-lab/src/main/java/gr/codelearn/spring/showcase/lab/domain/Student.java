package gr.codelearn.spring.showcase.lab.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
public class Student extends BaseModel {
	private String name;
	private String email;
	private Date dateOfBirth;
	private Set<Enrollment> enrollments;
}
