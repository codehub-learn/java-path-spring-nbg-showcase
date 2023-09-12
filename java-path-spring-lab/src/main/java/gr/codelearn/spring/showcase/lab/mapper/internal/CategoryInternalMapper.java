package gr.codelearn.spring.showcase.lab.mapper.internal;

import gr.codelearn.spring.showcase.lab.domain.Category;
import gr.codelearn.spring.showcase.lab.mapper.IgnoreUnmappedMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface CategoryInternalMapper extends BaseInternalMapper<Category> {

	@Override
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "courses", ignore = true)
	Category update(Category category, @MappingTarget Category retrievedCategory);
}
