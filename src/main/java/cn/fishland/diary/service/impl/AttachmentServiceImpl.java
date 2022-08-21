package cn.fishland.diary.service.impl;

import cn.fishland.diary.dao.AttachmentDao;
import cn.fishland.diary.pojo.Attachment;
import cn.fishland.diary.service.AttachmentService;
import cn.fishland.diary.vo.AttachmentVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Slf4j
@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    AttachmentDao attachmentDao;

    @Value("${attachment.localPath}")
    private String localPath;

    @Override
    public AttachmentVo saveAttachment(MultipartFile attachment) {
        try {
            String pathName;
            Attachment attachment1 = null;
            if (!attachment.isEmpty()) {
                pathName = localPath + "/" + attachment.getOriginalFilename();

                File file = new File(pathName);
                attachment.transferTo(file);

                attachment1 = new Attachment();
                attachment1.setName(attachment.getOriginalFilename());
                attachment1.setParent(0L);
                attachment1.setCategory(attachment.getContentType());
                attachment1.setPath(pathName);
                attachment1.setAttachmentId(UUID.randomUUID().toString().replaceAll("-", ""));
                attachmentDao.insert(attachment1);
            }

            AttachmentVo attachmentVo = new AttachmentVo();
            attachmentVo.setAlt(attachment1.getName());
            String uri = "http://localhost:1204/v1/img/" + attachment1.getAttachmentId();
            attachmentVo.setHref(uri);
            attachmentVo.setUrl(uri);
            attachmentVo.setAttachmentId(attachment1.getAttachmentId());
            return attachmentVo;
        } catch (Exception e) {
            log.error(String.format("save attachment error:[%s]", e.getMessage()));
            return null;
        }
    }

    @Override
    public Attachment findAttachmentByUid(String attachmentId) {
        try {
            Attachment attachment = attachmentDao.findAttachmentByAttachmentId(attachmentId);
            if (attachment != null) {
                return attachment;
            }
            return null;
        } catch (Exception e) {
            log.error(String.format("find attachment by attachmentId error:[%s]", e.getMessage()));
            return null;
        }
    }

}
