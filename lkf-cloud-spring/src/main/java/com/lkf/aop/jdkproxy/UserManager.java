package com.lkf.aop.jdkproxy;

/**
 * 用户管理接口
 *
 * @author 刘凯峰
 * @version V1.0
 * update-logs:方法变更说明
 * ****************************************************
 * name:
 * date:
 * description:
 * *****************************************************
 * @date 2020-04-09 15:25
 */
public interface UserManager {
    /**
     * 新增用户抽象方法
     *
     * @param userName 用户名
     * @param password 密码
     * @author 刘凯峰
     * @date 2020/4/9 15:3
     * @version V1.0
     */
    void addUser(String userName, String password);

    /**
     * 删除用户抽象方法
     *
     * @param userName 用户名
     * @author 刘凯峰
     * @date 2020/4/9 15:3
     * @version V1.0
     */
    void delUser(String userName);

}
