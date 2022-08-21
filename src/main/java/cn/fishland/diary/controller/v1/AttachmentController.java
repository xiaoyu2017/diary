package cn.fishland.diary.controller.v1;

import cn.fishland.diary.controller.VersionController;
import cn.fishland.diary.pojo.Attachment;
import cn.fishland.diary.service.AttachmentService;
import cn.fishland.diary.vo.AttachmentVo;
import cn.fishland.diary.vo.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Slf4j
@Controller
public class AttachmentController extends VersionController {

    @Autowired
    AttachmentService attachmentService;

    @ResponseBody
    @PostMapping("/home/attachment")
    public DataResult<AttachmentVo> imgAttachment(@RequestParam MultipartFile attachment) {
        AttachmentVo attachmentVo = attachmentService.saveAttachment(attachment);
        DataResult<AttachmentVo> dataResult = new DataResult<>(0, attachmentVo);
        return dataResult;
    }

    @GetMapping("/img/{attachmentId}")
    public void download(@PathVariable String attachmentId, HttpServletResponse response) {
        Attachment attachment = attachmentService.findAttachmentByUid(attachmentId);
        if (attachment != null) {
            String path = attachment.getPath();
            File file = new File(path);
            if (!file.exists()) {
                log.error("local path attachment not exists");
                return;
            }
            response.setContentType(attachment.getCategory());
            // 下载
            // response.addHeader("Content-Disposition", "attachment;fileName=" + attachment.getName());
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");

            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
