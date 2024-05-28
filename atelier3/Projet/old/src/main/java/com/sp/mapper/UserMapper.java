package com.sp.mapper;

import com.sp.bo.UserBo;
import com.sp.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserBo toBo(UserDto userDto);

    UserDto toDto(UserBo userBo);
}
