package fr.dreamteam.account.mapper;


import dto.UserDto;
import fr.dreamteam.account.bo.UserBo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserBo toBo(UserDto userDto);

    UserDto toDto(UserBo userBo);
}
