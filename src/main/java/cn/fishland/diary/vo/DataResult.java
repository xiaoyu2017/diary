package cn.fishland.diary.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 */
@Data
@EqualsAndHashCode
public class DataResult<T> {

    private Integer errno;
    private T data;

    public DataResult() {
    }

    public DataResult(Integer errno, T data) {
        this.errno = errno;
        this.data = data;
    }

    public DataResult(T data) {
        this.data = data;
    }
}
