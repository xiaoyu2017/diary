package cn.fishland.diary.service;

import cn.fishland.diary.pojo.ApiBrowse;

import java.util.List;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
public interface ApiBrowseService {

    /**
     * 保存api访问次数，如果存在则更改
     *
     * @param apiBrowses 访问数信息
     * @return 是否成功
     */
    Boolean saveApiBrowse(List<ApiBrowse> apiBrowses);

}
