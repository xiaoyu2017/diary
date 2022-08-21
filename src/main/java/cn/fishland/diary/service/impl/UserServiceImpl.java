package cn.fishland.diary.service.impl;

import cn.fishland.diary.dao.UserDao;
import cn.fishland.diary.pojo.User;
import cn.fishland.diary.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public Boolean register(User user) {
        userDao.insert(user);
        return true;
    }

    @Override
    public User getUserByName(String name) {
        try {
            return userDao.findByName(name);
        } catch (Exception e) {
            log.error(String.format("select user by name error:[%s]", e.getMessage()));
            return null;
        }
    }

    @Override
    public Boolean userCheck(User user, HttpSession session) {
        try {
            User getUseer = userDao.findByNameAndPassword(user);
            if (getUseer != null && getUseer.getId() != null) {
                session.setAttribute("user", getUseer);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error(String.format("select user by name and password error:[%s]", e.getMessage()));
            return false;
        }
    }

}
