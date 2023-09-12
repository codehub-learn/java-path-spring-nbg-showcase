package gr.codelearn.spring.showcase.lab.domain;

import gr.codelearn.spring.showcase.lab.domain.composite.EnrollmentId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Enrollment implements Serializable {
	@EmbeddedId
	private EnrollmentId id;

	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("studentId")
	private Student student;

	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("courseId")
	private Course course;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date enrolledAt;

	private Integer grade;
}
