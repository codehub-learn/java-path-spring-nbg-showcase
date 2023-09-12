package gr.codelearn.spring.showcase.lab.mapper;

import gr.codelearn.spring.showcase.lab.domain.Student;
import gr.codelearn.spring.showcase.lab.transfer.resource.StudentResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface StudentMapper extends BaseMapper<Student, StudentResource> {

	@Override
	@Mapping(target = "enrollments", ignore = true)
	StudentResource toResource(Student student);

	@Override
	default List<StudentResource> toResources(List<Student> students) {
		return students.stream().map(this::toResource).toList();
	}

	StudentResource toFullResource(Student student);
}
