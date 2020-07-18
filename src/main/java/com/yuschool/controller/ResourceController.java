package com.yuschool.controller;

import com.yuschool.bean.Resource;
import com.yuschool.constants.enums.RetCode;
import com.yuschool.service.ResourceService;
import com.yuschool.utils.FileUtil;
import com.yuschool.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.yuschool.constants.ParamKey.P_FILE;
import static com.yuschool.constants.enums.RetCode.*;

@RequestMapping("/resources")
@RestController
public class ResourceController {

    public static final Logger logger = LoggerFactory.getLogger(ResourceController.class);
    @Autowired
    ResourceService resourceService;

    @PostMapping
    public Result uploadResource(@RequestParam(value = P_FILE) MultipartFile[] files) {
        Result result = new Result();
        int failNum = 0;
        List<Integer> ids = new ArrayList<>();
        for (MultipartFile file : files) {
            Resource prepResource = new Resource();
            prepResource.setFullName(FileUtil.getAppropriateFilePath(file.getOriginalFilename()));
            File resourceFile = new File(prepResource.getFullName());
            try {
                file.transferTo(resourceFile);
            } catch (IOException e) {
                logger.error("存储文件失败", e);
                failNum++;
                continue;
            }
            prepResource.setResourceName(file.getOriginalFilename());
            prepResource.setType(file.getContentType());
            prepResource.setSize(file.getSize());
            RetCode retCode = resourceService.addResource(prepResource, resourceFile);
            if (retCode != SUCCESS) {
                logger.error("添加资源记录失败");
                failNum++;
            } else {
                ids.add(prepResource.getId());
            }
        }
        if (failNum != 0) {
            result.setRetCode(INCOMPLETE_OP);
            result.setMessage("部分资源未成功上传");
        } else {
            result.setRetCode(SUCCESS);
            result.setData(ids);
        }
        return result;
    }
}
