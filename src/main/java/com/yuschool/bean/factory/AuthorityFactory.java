package com.yuschool.bean.factory;

import com.yuschool.bean.Authority;
import com.yuschool.mapper.AuthorityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AuthorityFactory {

    @Autowired
    private AuthorityMapper authorityMapper;

    private static final Map<Integer, Authority> authorityRepo = new HashMap<>();

    @PostConstruct
    public void init() {
        List<Authority> authorities = authorityMapper.selectAll();
        for (Authority authority : authorities)
        {
            authorityRepo.put(authority.getId(), authority);
        }
    }

    public static Authority getInstance(int id) {
        return authorityRepo.get(id);
    }

    public static List<Authority> getInstances(List<Integer> ids) {
        List<Authority> result = new ArrayList<>();
        for (Integer id : ids) {
            result.add(authorityRepo.get(id));
        }
        return result;
    }

    /**
     * 通过权限名返回权限实例
     * @param name 权限名，使用RoleValue中的常量
     * @return 对应的权限实体
     */
    public static Authority getInstanceByName(String name) {
        Authority result = null;
        for (Map.Entry<Integer, Authority> authorityEntry : authorityRepo.entrySet()) {
            if (authorityEntry.getValue().getAuthority().equals(name)) {
                result = authorityEntry.getValue();
                break;
            }
        }
        return result;
    }

}
