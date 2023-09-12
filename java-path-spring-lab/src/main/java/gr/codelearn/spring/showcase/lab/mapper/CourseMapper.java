package gr.codelearn.spring.showcase.lab.mapper;

import gr.codelearn.spring.showcase.lab.domain.Course;
import gr.codelearn.spring.showcase.lab.transfer.resource.CourseResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface CourseMapper extends BaseMapper<Course, CourseResource> {
}
