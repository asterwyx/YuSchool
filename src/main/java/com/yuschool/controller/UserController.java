package com.yuschool.controller;

import com.yuschool.bean.User;
import com.yuschool.constant.ParamKey;
import com.yuschool.service.UserService;
import com.yuschool.service.impl.AccountServiceImpl;
import com.yuschool.utils.Result;
import static com.yuschool.utils.Result.Code.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AccountServiceImpl accountService;

    /**
     * 根据传入的参数
     * @param username 用户名
     * @param password 密码，可能经过加密
     * @param admin 是否是管理员
     * @return json数据串
     */
    @ResponseBody
    @PostMapping("/users")
    public Result register(@RequestParam(name = ParamKey.P_USERNAME) String username, @RequestParam(name = ParamKey.P_PASSWORD) String password, @RequestParam(name = ParamKey.P_ADMIN) boolean admin) {
        Result result = new Result();
        boolean find = userService.checkExistence(username);
        password = decryptPassword(password);
        if (find) {
            result.setCode(DUP_VALUE);
            result.setMessage("该用户名已经被注册");
        } else {
            User user = new User(username);
            boolean status = userService.addUser(user);
            if (status) {
                if (admin) {
                    status = accountService.addAdminAccount(user, password);
                } else {
                    status = accountService.addNormalAccount(user, password);
                }
                if (status) {
                    result.setCode(SUCCESS);
                    result.setData(user);
                } else {
                    result.setCode(FAIL_OP);
                    result.setMessage("添加新用户账号失败");
                }
            } else {
                result.setCode(FAIL_OP);
                result.setMessage("添加新用户失败");
            }
        }
        return result;
    }

    /**
     * 这里密码的解密函数，可能会加入RSA非对称加密，目前先使用不加密的方法
     * @param password 需要解密的密码
     * @return 加密后的密码
     */
    public String decryptPassword(String password) {
        return password;
    }

    @GetMapping("/login")
    public String index() {
        return "/login.html";
    }

}
