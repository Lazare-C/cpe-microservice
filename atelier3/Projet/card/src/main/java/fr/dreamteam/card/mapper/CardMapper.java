package fr.dreamteam.card.mapper;

import dto.CardDto;
import fr.dreamteam.card.bo.CardBo;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CardMapper {
    @Mapping(source = "userBoId", target = "ownerId")
    CardBo toEntity(CardDto cardDto);

    @InheritInverseConfiguration(name = "toEntity")
    CardDto toDto(CardBo cardBo);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CardBo partialUpdate(CardDto cardDto, @MappingTarget CardBo cardBo);

    List<CardDto> toDtoList(List<CardBo> cards);
}