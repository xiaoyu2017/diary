package cn.fishland.diary.service.impl;

import cn.fishland.diary.dao.AipBrowseDao;
import cn.fishland.diary.pojo.ApiBrowse;
import cn.fishland.diary.service.ApiBrowseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Slf4j
@Service
public class ApiBrowseServiceImpl implements ApiBrowseService {

    @Autowired
    AipBrowseDao aipBrowseDao;

    @Override
    public Boolean saveApiBrowse(List<ApiBrowse> apiBrowses) {
        if (apiBrowses == null || apiBrowses.isEmpty()) {
            return false;
        }

        // 获得当天所有Api访问内容
        List<ApiBrowse> allApiBrowse = aipBrowseDao.findAllByTime(apiBrowses.get(0).getTime());
        // list 2 map
        Map<String, Long> map = allApiBrowse.stream().collect(Collectors.toMap(ApiBrowse::getApi, ApiBrowse::getNumber));

        // 无历史记录直接存储
        if (allApiBrowse.isEmpty()) {
            for (ApiBrowse apiBrows : apiBrowses) {
                try {
                    aipBrowseDao.insert(apiBrows);
                } catch (Exception e) {
                    log.error(String.format("insert api browse error:[%s] ApiBrowse:[%s]", e.getMessage(), apiBrows));
                }
            }
            return true;
        }

        // 比较变化进行修改
        for (ApiBrowse apiBrows : apiBrowses) {
            if (apiBrows.getNumber() != map.get(apiBrows.getApi())) {
                try {
                    aipBrowseDao.update(apiBrows);
                } catch (Exception e) {
                    log.error(String.format("update api browse error:[%s] ApiBrowse:[%s]", e.getMessage(), apiBrows));
                }
            }
        }
        return true;
    }

}
