package com.yuschool.service.impl;

import com.yuschool.bean.Resource;
import com.yuschool.constants.enums.RetCode;
import com.yuschool.mapper.ResourceMapper;
import net.sf.jmimemagic.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

import static com.yuschool.constants.DefaultValue.PK_NULL;
import static com.yuschool.constants.enums.RetCode.*;

@Service
public class ResourceServiceImpl implements com.yuschool.service.ResourceService {

    public static final Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);
    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public RetCode addResource(Resource prepResource, File file) {
        prepResource.setSectionId(PK_NULL);
        MagicMatch match; // 获取MimeType
        try {
            match = Magic.getMagicMatch(file, true, true);
        } catch (MagicParseException | MagicMatchNotFoundException | MagicException e) {
            logger.error("获取文件MimeType失败", e);
            return MATCH_ERROR;
        }
        assert match != null;
        prepResource.setType(match.getMimeType());
        return SUCCESS;
    }
}
