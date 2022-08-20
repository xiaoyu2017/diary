package cn.fishland.diary.service;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 */
public interface RedisService {

    /**
     * 存储字符串
     *
     * @param key  键
     * @param vals 值
     * @return 是否成功
     */
    Boolean setList(String key, String... vals);
}
