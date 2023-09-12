package gr.codelearn.spring.showcase.lab.mapper;

import gr.codelearn.spring.showcase.lab.domain.Enrollment;
import gr.codelearn.spring.showcase.lab.transfer.resource.EnrollmentResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface EnrollmentMapper extends BaseMapper<Enrollment, EnrollmentResource> {
}
