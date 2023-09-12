package gr.codelearn.spring.showcase.lab.mapper;

import gr.codelearn.spring.showcase.lab.domain.Category;
import gr.codelearn.spring.showcase.lab.transfer.resource.CategoryResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface CategoryMapper extends BaseMapper<Category, CategoryResource> {

	@Override
	@Mapping(target = "courses", ignore = true)
	CategoryResource toResource(Category category);

	@Override
	default List<CategoryResource> toResources(List<Category> categories) {
		return categories.stream().map(this::toResource).toList();
	}

	CategoryResource toFullResource(Category category);
}
