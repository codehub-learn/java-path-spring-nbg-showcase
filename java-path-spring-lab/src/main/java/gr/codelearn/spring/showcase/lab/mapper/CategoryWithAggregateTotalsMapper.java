package gr.codelearn.spring.showcase.lab.mapper;

import gr.codelearn.spring.showcase.lab.domain.Category;
import gr.codelearn.spring.showcase.lab.transfer.resource.CategoryWithAggregateTotalsResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface CategoryWithAggregateTotalsMapper extends BaseMapper<Category, CategoryWithAggregateTotalsResource> {

	@Override
	@Mapping(target = "totalCourses", expression = "java(category.getCourses().size())")
	CategoryWithAggregateTotalsResource toResource(Category category);
}
