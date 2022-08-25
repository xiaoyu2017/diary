package cn.fishland.diary.dao;

import cn.fishland.diary.pojo.ApiBrowse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Mapper
public interface AipBrowseDao {

    @Select("select * from api_browse where status = 1 and time = #{time};")
    List<ApiBrowse> findAllByTime(String time);

    @Insert("insert into api_browse(`time`,`api`,`number`) value(#{time},#{api},#{number});")
    void insert(ApiBrowse apiBrowse);

    @Update("update api_browse set number = #{number} where `id` = #{id};")
    void update(ApiBrowse apiBrowse);

}
