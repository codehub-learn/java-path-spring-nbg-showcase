package gr.codelearn.spring.showcase.lab.mapper.internal;

import org.mapstruct.MappingTarget;

public interface BaseInternalMapper<D> {
	D update(D domain, @MappingTarget D retrievedDomain);
}
