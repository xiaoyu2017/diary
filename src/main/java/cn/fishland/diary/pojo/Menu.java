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
public class Menu extends Pojo {

    private String icon;
    private String link;
    private String title;
    private Integer sort;
    private Long parent;
}
