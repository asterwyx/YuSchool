package com.shareknowledge.utils;

public class MyConstants {
    public static final String PROJECT_PATH = "/YuSchool";

    public static final int PK_NULL = -1;
    public static final int NUM_NULL = -1;
    public static final String DEFAULT_USER_HEAD_PATH = "./static/image/head_black.png";
    public static final int FLAG_NULL = -1;

    // 错误码
    public static final int SUCCESS = 0;
    public static final int IO_ERROR = -1;
    public static final int WRONG_OP = -2;
    public static final int FAIL_OP = -3;



    public static final int MSG_BACKLOG = 10000;
    public static final int READ = 0;
    public static final int UNREAD = 1;
    public static final int LESS = -1;
    public static final int EQUAL = 0;
    public static final int GREATER = 1;


    public static final int OP_ADD = 0;
    public static final int OP_DEL = 1;

    public static final int ACCESS_PUB = 0; // 公开访问权限
    public static final int ACCESS_PRI = 1; // 私人访问权限


    public static final int THREAD_POOL_SIZE = 10; // 线程池的大小
    public static final int MSG_HANDLER_NUM = 5; // 消息处理器的线程数

    // 评论区的类型
    public static final int TYPE_ASSESS = 0;
    public static final int TYPE_DISCUSSION = 1;


    // 存在request域中引用的key

    public static final String ALL_COURSE_IN_MAIN_PAGE = "all_course";
    public static final String RANK_COURSE_IN_MAIN_PAGE = "rank_course";
    public static final String RECOMMEND_COURSES_IN_MAIN_PAGE = "recommend_course";
    public static final String ALL_USER_COURSE_IN_USER_PAGE = "all_user_course";
    public static final String ALL_USER_STAR_COURSE_IN_USER_PAGE = "all_user_star_course";
    public static final String ALL_USER_MANAGER_COURSE_IN_USER_PAGE = "all_user_manager_course";
    public static final String COURSE_IN_OVER_VIEW_PAGE = "course_in_over_view";
    public static final String COLLABORATOR_IN_OVER_VIEW_PAGE = "collaborator_in_over_view";
    public static final String COLLABORATOR_NUM_IN_OVER_VIEW_PAGE = "collaborator_num_in_over_view";
    public static final String OWNER_IN_OVER_VIEW_PAGE = "owner_in_over_view";
    public static final String STAR_NUM_IN_OVER_VIEW_PAGE = "star_num";
    public static final String IS_STAR_IN_OVER_VIEW_PAGE = "is_star";
    public static final String COMMENT_NUM_IN_OVER_VIEW_PAGE = "comment_num";
    public static final String COMMENTS_IN_OVER_VIEW_PAGE = "comments";
    public static final String COMMENT_AREA_ID_IN_OVER_VIEW_PAGE = "comment_area_id";
    public static final String OTHER_USER_IN_OTHER_PAGE = "other_user";
    public static final String OTHER_USER_MASSAGE_IN_OTHER_PAGE = "other_massage";
    public static final String IS_FOLLOW_IN_OTHER_PAGE = "is_follow";
    public static final String FOLLOW_NUM = "follow_num";
    public static final String FOLLOWER_NUM = "follower_num";

    //性别
    public static final String GENDER_1 = "gender_1";
    public static final String GENDER_2 = "gender_2";
    public static final String GENDER_3 = "gender_3";

    public static final String RSA_KEY_PAIR = "RSA_KEY_PAIR";

    public static final String ID = "ID"; // URL中的主键被识别后放在req的属性中的Key前缀，第一个id就是ID1，第二个就是ID2
    public static final String ID_1 = ID + 1;
    public static final String ID_2 = ID + 2;

    public static final String P_SIZE = "max";
    public static final String P_PAGE = "page";
    public static final String P_USERNAME = "user_name";
    public static final String P_GENDER = "gender";
    public static final String P_AGE = "age";
    public static final String P_HEAD_FILE_PATH = "head_file_path";
    public static final String P_PHONE_NUM = "phone_num";
    public static final String P_DETAIL = "detail";
    public static final String P_ACCOUNT = "account";
    public static final String P_PASSWORD = "password";
    public static final String P_OLD_PASSWORD = "old_password";
    public static final String P_COURSE_NAME = "course_name";
    public static final String P_COURSE_INTRODUCTION = "course_introduction";
    public static final String P_COURSE_COVER_PATH = "course_cover_path";
    public static final String P_SENDER = "sender";
    public static final String P_RECEIVER = "receiver";
    public static final String P_RANGE = "range";
    public static final String P_LIMIT = "limit";
    public static final String P_METHOD = "method";
    public static final String P_USER_ID= "user_id";


    public static final long ONE_DAY_MILLIS = 86400000;

    //权限标识符
    public static final int AUTHORITY_USER= 0;
    public static final int AUTHORITY_ADMIN= 1;


    public static final String RESOURCE_ROOT = "C:\\Repositories\\IdeaProjects\\YuSchool\\web\\Resources\\";

}
