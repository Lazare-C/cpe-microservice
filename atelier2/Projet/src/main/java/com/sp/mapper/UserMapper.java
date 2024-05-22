package com.sp.mapper;

import com.sp.bo.UserBo;
import com.sp.model.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserBo toBo(UserDto userDto);

    UserDto toDto(UserBo userBo);
}
