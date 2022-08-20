package cn.fishland.diary.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Article extends Pojo implements Serializable {

    private static final long serialVersionUID = -5355363419930765325L;

    private String title;
    private String tag;
    private String content;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.length() > 200 ? description.substring(0, 200) : description;
    }
}
