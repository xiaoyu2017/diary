package cn.fishland.diary.service;

import cn.fishland.diary.pojo.Attachment;
import cn.fishland.diary.vo.AttachmentVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
public interface AttachmentService {

    /**
     * 保存单个文件
     *
     * @param attachment 附件内容
     * @return 是否保存成功
     */
    AttachmentVo saveAttachment(MultipartFile attachment);

    /**
     * 通过attachmentId获得文件内容
     *
     * @param attachmentId 唯一id
     * @return 附件内容对象
     */
    Attachment findAttachmentByUid(String attachmentId);
}
