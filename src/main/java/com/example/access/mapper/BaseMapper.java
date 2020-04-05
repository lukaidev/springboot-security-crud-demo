package com.example.access.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface BaseMapper<E, D> {

	boolean isEntityValid(E entity);
	
	boolean isDtoValid(D dto);
	
	E mapEntity(D dto);

	D mapDto(E entity);

	default List<E> mapEntityList(List<D> dtoList) {
		if (dtoList == null) {
			return Collections.emptyList();
		}
		return dtoList.stream().map(this::mapEntity).collect(Collectors.toList());
	}

	default List<D> mapDtoList(List<E> entityList) {
		if (entityList == null) {
			return Collections.emptyList();
		}
		return entityList.stream().map(this::mapDto).collect(Collectors.toList());
	}
}
