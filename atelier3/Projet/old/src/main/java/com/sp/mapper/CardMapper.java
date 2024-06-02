package com.sp.mapper;

import com.sp.bo.CardBo;
import com.sp.model.CardDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CardMapper {
    @Mapping(source = "userBoUsername", target = "owner.username")
    @Mapping(source = "userBoId", target = "owner.id")
    CardBo toEntity(CardDto cardDto);

    @InheritInverseConfiguration(name = "toEntity")
    CardDto toDto(CardBo cardBo);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CardBo partialUpdate(CardDto cardDto, @MappingTarget CardBo cardBo);

    List<CardDto> toDtoList(List<CardBo> cards);
}