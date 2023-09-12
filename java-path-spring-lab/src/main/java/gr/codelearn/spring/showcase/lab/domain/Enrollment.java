package gr.codelearn.spring.showcase.lab.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
public class Enrollment {
	private Course course;
	private Date enrolledAt;
	private Integer grade;
}
