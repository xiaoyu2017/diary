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
public class ApiBrowse extends Pojo {

    public ApiBrowse() {
    }

    public ApiBrowse(String time, String api, Long number) {
        this.time = time;
        this.api = api;
        this.number = number;
    }

    /** 年月日拼接 */
    private String time;
    private String api;
    private Long number;

}
