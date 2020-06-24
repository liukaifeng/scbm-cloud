package com.scbm.aop.jdkproxy;

import org.springframework.stereotype.Service;

/**
 * 用户管理实现类,实现用户管理接口
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
@Service
public class UserManagerImpl implements UserManager {

    /**
     * 实现新增用户方法
     *
     * @param userName 用户名
     * @param password 密码
     * @return void
     * @author 刘凯峰
     * @date 2020/4/9 15:36
     */
    public void addUser(String userName, String password) {

        System.out.println("调用了新增的方法！");
        System.out.println("传入参数为 userName: " + userName + " password: " + password);
    }

    /**
     * 实现删除用户方法
     *
     * @param userName 用户名
     * @return void
     * @author 刘凯峰
     * @date 2020/4/9 15:36
     */
    public void delUser(String userName) {
        System.out.println("调用了删除的方法！");
        System.out.println("传入参数为 userName: " + userName);
    }
}

