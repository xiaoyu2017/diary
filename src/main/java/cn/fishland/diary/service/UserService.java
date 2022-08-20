package cn.fishland.diary.service;

import cn.fishland.diary.pojo.User;

import javax.servlet.http.HttpSession;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 */
public interface UserService {

    /**
     * 注册用户
     *
     * @param user 用户信息
     * @return 是否成功
     */
    Boolean register(User user);

    /**
     * 根据用户名获得用户信息
     *
     * @param name 用户名
     * @return 用户信息
     */
    User getUserByName(String name);

    /**
     * 根据用户名称和密码获得用户信息
     *
     * @param user    用户查找信息
     * @param session 会话对象
     * @return 查询用户信息
     */
    Boolean userCheck(User user, HttpSession session);

}
