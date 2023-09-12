package gr.codelearn.spring.showcase.lab.mapper.internal;

import gr.codelearn.spring.showcase.lab.domain.Course;
import gr.codelearn.spring.showcase.lab.mapper.IgnoreUnmappedMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface CourseInternalMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "category", ignore = true)
	Course update(Course course, @MappingTarget Course retrievedCourse);
}
