package com.shareknowledge.service;

import com.shareknowledge.bean.Resource;
import com.shareknowledge.mapper.ResourceMapper;
import net.sf.jmimemagic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    public Resource addResource(String resourceName, String fullName, File file, int size) throws MagicParseException, MagicException, MagicMatchNotFoundException {
        Resource resource = new Resource();
        resource.setResourceName(resourceName);
        resource.setFullName(fullName);
        resource.setSize(size);
        resource.setSectionId(0);
        MagicMatch match = Magic.getMagicMatch(file, true, true); // 获取MimeType
        resource.setType(match.getMimeType());
        return resource;
    }


}
