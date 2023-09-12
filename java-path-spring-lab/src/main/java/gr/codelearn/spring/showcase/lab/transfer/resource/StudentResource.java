package gr.codelearn.spring.showcase.lab.transfer.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
public class StudentResource extends BaseResource {
	private String name;
	private String email;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	private List<EnrollmentResource> enrollments;
}
