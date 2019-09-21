package com.clay.mybatisdemo.mapper;

import com.clay.mybatisdemo.model.Coffee;
import org.apache.ibatis.annotations.*;

/**
 * @author clay
 */
@Mapper
public interface CoffeeMapper {

    @Insert(value = "insert into t_coffee (name, price, create_time, update_time) values (#{name}, #{price}, now(), now())")
    @Options(useGeneratedKeys = true)
    int save(Coffee coffee);

    @Select("SELECT * from t_coffee where id = #{id}")
//    @Results({
//            @Result(id = true, column = "id", property = "id"),
//            @Result(column = "create_time", property = "createTime"),
//    })
    Coffee findById(@Param("id") Long id);
}

