package gr.codelearn.spring.showcase.lab.mapper.internal;

import gr.codelearn.spring.showcase.lab.domain.Student;
import gr.codelearn.spring.showcase.lab.mapper.IgnoreUnmappedMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface StudentInternalMapper extends BaseInternalMapper<Student> {

	@Override
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "enrollments", ignore = true)
	Student update(Student student, @MappingTarget Student retrievedStudent);
}
