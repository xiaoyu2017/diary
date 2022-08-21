package cn.fishland.diary.vo;

import cn.fishland.diary.pojo.Pojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AttachmentVo extends Pojo {

    private String url;
    private String alt;
    private String href;
    private String attachmentId;

}
