package cn.fishland.diary.vo;

import cn.fishland.diary.pojo.Pojo;
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
public class ArticleVo extends Pojo {

    private String title;
    private String tag;
    private String description;
    private String iconLink;
}
