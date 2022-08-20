package cn.fishland.diary.dao;

import cn.fishland.diary.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 */
@Mapper
public interface ArticleDao {

    @Insert("INSERT INTO article (`title`, `tag`, `content`, `description`) values (#{title},#{tag},#{content},#{description});")
    void insert(Article article);

    @Select("SELECT * from article where status = 1;")
    List<Article> selectAll();

    @Select("SELECT * from article where status = 1 and id = #{id};")
    Article findById(Long id);

    @Update("update article set `title`=#{title},`tag`=#{tag},`content`=#{content},`description`=#{description} where id=#{id};")
    void update(Article article);

    @Update("update article set status = 0 where id = #{id};")
    void deleteById(Long id);
}
