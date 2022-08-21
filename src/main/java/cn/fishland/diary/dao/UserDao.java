package cn.fishland.diary.dao;

import cn.fishland.diary.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Mapper
public interface UserDao {

    @Select("SELECT * FROM user where name = #{name};")
    User findByName(String name);

    @Insert("INSERT INTO user(`name`,`nickName`,`password`,`iconLink`,`email`) value(#{name},#{nickName},#{password},#{iconLink},#{email});")
    void insert(User user);

    @Select("select * from user where `name` = #{name} and `password` = #{password};")
    User findByNameAndPassword(User user);
}
