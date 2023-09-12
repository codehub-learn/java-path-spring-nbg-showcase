package gr.codelearn.spring.showcase.lab.mapper.internal;

import gr.codelearn.spring.showcase.lab.domain.Enrollment;
import gr.codelearn.spring.showcase.lab.domain.Student;
import gr.codelearn.spring.showcase.lab.mapper.IgnoreUnmappedMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface EnrollmentInternalMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "student", ignore = true)
	@Mapping(target = "course", ignore = true)
	Enrollment update(Enrollment enrollment, @MappingTarget Enrollment retrievedEnrollment);
}
