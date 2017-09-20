package com.ukar.mapper;

import com.ukar.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by jyou on 2017/9/15.
 */
@Mapper
public interface UserMapper {
    User selectByName(@Param("name") String name);
}
