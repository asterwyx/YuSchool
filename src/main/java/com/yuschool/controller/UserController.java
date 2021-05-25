package com.yuschool.controller;

import com.yuschool.bean.Account;
import com.yuschool.bean.Course;
import com.yuschool.bean.User;
import com.yuschool.constants.enums.RetCode;
import com.yuschool.service.AccountService;
import com.yuschool.service.CourseService;
import com.yuschool.service.UserService;
import com.yuschool.service.VerifyService;
import com.yuschool.utils.ListUtil;
import com.yuschool.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.yuschool.constants.ParamKey.*;
import static com.yuschool.constants.enums.RetCode.*;
import static com.yuschool.constants.enums.Operation.*;


@RequestMapping("/users")
@RestController
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final AccountService accountService;
    private final VerifyService verifyService;
    private final PasswordEncoder encoder;
    private final CourseService courseService;

    public UserController(UserService userService, AccountService accountService, VerifyService verifyService, PasswordEncoder encoder, CourseService courseService) {
        this.userService = userService;
        this.accountService = accountService;
        this.verifyService = verifyService;
        this.encoder = encoder;
        this.courseService = courseService;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public Result getAllUsers(
            @RequestParam(value = P_PAGE, required = false, defaultValue = "-1") int page,
            @RequestParam(value = P_SIZE, required = false, defaultValue = "-1") int size
    ) {
       if (page == -1 || size == -1) {
           if(page == -1 && size == -1) {
               return Result.withRetCode(SUCCESS)
                       .data(userService.getAllUsers())
                       .build();
           } else {
               return Result.withRetCode(WRONG_OP)
                       .message("参数必须同时有效或者不传参数")
                       .build();
           }
       } else {
           return Result.withRetCode(SUCCESS)
                   .data(userService.getUsersByPage(page, size))
                   .build();
       }
    }

    @PostMapping("/admin")
    public Result registerAdmin(@RequestParam(P_USERNAME) String username, @RequestParam(P_PASSWORD) String password, @RequestParam(P_INVITATION_CODE) String invitationCode) {
        Result result = new Result();
        boolean valid = verifyService.checkInvitationCode(invitationCode);
        if (valid) {
            boolean find = userService.checkExistence(username);
            password = decryptPassword(password);
            if (find) {
                result.setRetCode(DUP_VALUE)
                        .setMessage("该用户名已经被注册");
            } else {
                User user = new User(username);
                boolean status = userService.addUser(user);
                if (status) {
                    status = accountService.addAdminAccount(user, password);
                    if (status) {
                        result.setRetCode(SUCCESS)
                                .setData(user);
                    } else {
                        result.setRetCode(FAIL_OP)
                                .setMessage("添加新用户账号失败");
                    }
                } else {
                    result.setRetCode(FAIL_OP)
                            .setMessage("添加新用户失败");
                }
            }
        } else {
            logger.error("邀请码错误");
            result.setData(WRONG_OP)
                    .setMessage("邀请码错误");
        }
        return result;
    }

    @PostMapping
    public Result register(@Param(P_USERNAME) String username, @Param(P_PASSWORD) String password) {
        Result result = new Result();
        boolean find = userService.checkExistence(username);
        password = decryptPassword(password);
        if (find) {
            result.setRetCode(DUP_VALUE)
                    .setMessage("该用户名已经被注册");
        } else {
            User user = new User(username);
            boolean status = userService.addUser(user);
            if (status) {
                status = accountService.addNormalAccount(user, password);
                if (status) {
                    result.setRetCode(SUCCESS)
                            .setData(user);
                } else {
                    result.setRetCode(FAIL_OP)
                            .setMessage("添加新用户账号失败");
                }
            } else {
                result.setRetCode(FAIL_OP)
                        .setMessage("添加新用户失败");
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

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/{user_id}/follows")
    public Result getAllFollows(@PathVariable(value = "user_id") int userId) {
        return Result.builder()
                .data(userService.getAllFollows(userId))
                .build();
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/{user_id}/fans")
    public Result getAllFans(@PathVariable(value = "user_id") int userId) {
        return Result.builder()
                .data(userService.getAllFans(userId))
                .build();
    }

    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping("/{user_id}/follows/{follow_id}")
    public Result addFollow(@PathVariable(value = "user_id") int userId, @PathVariable(value = "follow_id") int followId) {
        RetCode rc = userService.updateFollow(userId, followId, OP_ADD);
        return Result.withRetCode(rc)
                .build();
    }

    @PreAuthorize("hasAnyRole('USER')")
    @DeleteMapping("/{user_id}/follows")
    public Result cancelFollow(@PathVariable("user_id") int userId, @RequestParam(P_LIST) String list) {
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

    @PreAuthorize("hasAnyRole('USER')")
    @PutMapping("/{user_id}")
    public Result updateUserInfo(
            @PathVariable("user_id") int userId,
            @RequestParam(value = P_USERNAME, required = false) String username,
            @RequestParam(value = P_GENDER, required = false) String gender,
            @RequestParam(value = P_AGE, required = false, defaultValue = "-1") int age,
            @RequestParam(value = P_HEAD_FILE_PATH, required = false) String headFilePath,
            @RequestParam(value = P_DETAIL, required = false) String detail
            ) {
        RetCode rc;
        User user = new User(userId, null, gender, age, headFilePath, detail);
        System.out.println(user);
        if (username != null) {
            if (userService.checkExistence(username)) {
                return Result
                        .withRetCode(DUP_VALUE)
                        .message("用户名已存在")
                        .build();
            } else {
                rc = accountService.changeUsername(userId, username);
                if (rc != SUCCESS) {
                    return Result
                            .withRetCode(rc)
                            .message("更改用户名失败")
                            .build();
                }
            }
        }
        rc = userService.updateUserInfo(user);
        return Result
                .withRetCode(rc)
                .message(rc == SUCCESS ? null : "更新用户基本信息失败")
                .build();
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/{user_id}")
    public Result getUserInfo(@PathVariable("user_id") int userId) {
        User user = userService.getUserInfo(userId);
        if (user == null) {
            return Result.withRetCode(WRONG_OP)
                    .message("用户不存在")
                    .build();
        } else {
            return Result.withRetCode(SUCCESS)
                    .data(user)
                    .build();
        }
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/id")
    public Result getCurrentUserId(@NotNull HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return Result.withRetCode(WRONG_OP)
                    .message("用户未登录")
                    .build();
        } else {
            return Result.withRetCode(SUCCESS)
                    .data(user.getId())
                    .build();
        }
    }

    @PreAuthorize("hasAnyRole('USER')")
    @PutMapping("/{user_id}/account")
    public Result changePassword(
            @PathVariable("user_id") int userId,
            @RequestParam(P_OLD_PASSWORD) String oldPassword,
            @RequestParam(P_PASSWORD) String password
    ) {
        Account account = accountService.getAccount(userId);
        if (account == null) {
            return Result.withRetCode(WRONG_OP)
                    .message("用户不存在")
                    .build();
        } else {
            if (encoder.encode(oldPassword).equals(account.getPassword())) {
                boolean status = accountService.changePassword(userId, password);
                if (status) {
                    return Result.withRetCode(SUCCESS)
                            .build();
                } else {
                    return Result.withRetCode(FAIL_OP)
                            .message("修改密码失败")
                            .build();
                }
            } else {
                return Result.withRetCode(WRONG_OP)
                        .message("密码验证错误")
                        .build();
            }
        }
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/{user_id}/courses")
    public Result getPubCourses(
            @PathVariable("user_id") Integer userId,
            @RequestParam(P_PAGE) Integer page,
            @RequestParam(P_SIZE) Integer size
    ) {
        List<Integer> courseIds = userService.getPubCourses(userId, page, size);
        List<Course> courses = courseService.getCoursesByIds(courseIds);
        return Result.withRetCode(SUCCESS)
                .data(courses)
                .build();
    }

    @DeleteMapping("/{user_id}/courses")
    public Result deletePubCourses(
            @PathVariable("user_id") Integer userId,
            @RequestParam(P_LIST) List<Integer> list
    ) {
        // 删除所属关系
        Runnable deleteOwn = () -> {
            for (Integer courseId : list) {
                courseService.updateOwnCourse(userId, courseId, OP_DEL);
            }
        };
        Thread del = new Thread(deleteOwn);
        del.start();
        // 删除课程

        return Result.withRetCode(SUCCESS).build();
    }
}
