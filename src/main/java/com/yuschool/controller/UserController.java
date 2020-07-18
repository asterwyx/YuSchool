package com.yuschool.controller;

import com.yuschool.bean.User;
import com.yuschool.constants.ParamKey;
import com.yuschool.constants.enums.RetCode;
import com.yuschool.service.UserService;
import com.yuschool.service.impl.AccountServiceImpl;
import com.yuschool.utils.ListUtil;
import com.yuschool.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.yuschool.constants.ParamKey.*;
import static com.yuschool.constants.enums.RetCode.*;
import static com.yuschool.constants.enums.Operation.*;


@RequestMapping("/users")
@RestController
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
    @PostMapping
    public Result register(@RequestParam(name = ParamKey.P_USERNAME) String username, @RequestParam(name = ParamKey.P_PASSWORD) String password, @RequestParam(name = ParamKey.P_ADMIN) boolean admin) {
        Result result = new Result();
        boolean find = userService.checkExistence(username);
        password = decryptPassword(password);
        if (find) {
            result.setRetCode(DUP_VALUE);
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
                    result.setRetCode(SUCCESS);
                    result.setData(user);
                } else {
                    result.setRetCode(FAIL_OP);
                    result.setMessage("添加新用户账号失败");
                }
            } else {
                result.setRetCode(FAIL_OP);
                result.setMessage("添加新用户失败");
            }
        }
        return result;
    }

    /**
     * 这是密码的解密函数，可能会加入RSA非对称加密，目前先使用不加密的方法
     */
    public String decryptPassword(String password) {
        return password;
    }

    @GetMapping("/{user_id}/follows")
    public Result getAllFollows(@PathVariable(value = "user_id") int userId) {
        return Result.builder()
                .data(userService.getAllFollows(userId))
                .build();
    }

    @GetMapping("/{user_id}/fans")
    public Result getAllFans(@PathVariable(value = "user_id") int userId) {
        return Result.builder()
                .data(userService.getAllFans(userId))
                .build();
    }

    @PostMapping("/{user_id}/follows/{follow_id}")
    public Result addFollow(@PathVariable(value = "user_id") int userId, @PathVariable(value = "follow_id") int followId) {
        RetCode rc = userService.updateFollow(userId, followId, OP_ADD);
        return Result.withRetCode(rc)
                .build();
    }

    @DeleteMapping("/{user_id}/follows")
    public Result cancelFollow(@PathVariable(value = "user_id") int userId, @RequestParam(value = P_LIST) String list) {
        Result result = new Result();
        List<Integer> idList = ListUtil.parseIntList(list);
        int succeedNum = 0;
        for (int followId : idList) {
            if (userService.updateFollow(userId, followId, OP_DEL) == SUCCESS) {
                succeedNum++;
            }
        }
        if (succeedNum == idList.size()) {
            result.setRetCode(SUCCESS);
        } else {
            result.setRetCode(INCOMPLETE_OP);
        }
        return result;
    }

    @PutMapping("/{user_id}")
    public Result updateUserInfo(
            @PathVariable("user_id") int userId,
            @RequestParam(value = P_USERNAME, required = false) String username,
            @RequestParam(value = P_GENDER, required = false) String gender,
            @RequestParam(value = P_AGE, required = false, defaultValue = "-1") int age,
            @RequestParam(value = P_HEAD_FILE_PATH, required = false) String headFilePath,
            @RequestParam(value = P_DETAIL, required = false) String detail
            ) {
        User user = new User(userId, username, gender, age, headFilePath, detail);
        System.out.println(user);
        RetCode rc = userService.updateUserInfo(user);
        return Result.withRetCode(rc)
                .message(rc == SUCCESS ? "" : "更新用户信息失败")
                .build();

    }
}
