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
@EqualsAndHashCode(callSuper = true)
public class User extends Pojo implements Serializable {

    private static final long serialVersionUID = 8805341488477491117L;

    private String name;
    private String nickName;
    private String password;
    private String iconLink;
    private String email;
}
