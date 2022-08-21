package cn.fishland.diary.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Attachment extends Pojo {

    private String attachmentId;
    private String name;
    private String path;
    private Long parent;
    private String category;

}
