package com.yuschool.service;

import com.yuschool.bean.Resource;
import com.yuschool.constants.enums.RetCode;

import java.io.File;

public interface ResourceService {

    /**
     * 添加资源的函数，需要传入一个预先准备好的对象，该对象会被填充
     * @param prepResource 预先准备好的对象
     *        以下域要预先填充好
     *        resourceName
     *        fullName
     *        size
     * @param file 资源文件对象
     * @return 返回码
     */
    RetCode addResource(Resource prepResource, File file);

}
