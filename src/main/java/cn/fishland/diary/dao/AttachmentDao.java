package cn.fishland.diary.dao;

import cn.fishland.diary.pojo.Attachment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 */
@Mapper
public interface AttachmentDao {

    @Insert("insert into attachment (`category`, `parent`, `path`, `name`,`attachmentId`) values (#{category},#{parent},#{path},#{name},#{attachmentId});")
    void insert(Attachment attachment);

    @Select("SELECT * from attachment where status = 1 and `attachmentId` = #{attachmentId}")
    Attachment findAttachmentByAttachmentId(String attachmentId);
}
