package gr.codelearn.spring.showcase.lab.repository;

import gr.codelearn.spring.showcase.lab.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	@Query("""
			select s
			from Student s
			left join fetch s.enrollments e
			left join fetch e.course
			where s.id = :studentId
			""")
	Student getFullStudent(Long studentId);
}
