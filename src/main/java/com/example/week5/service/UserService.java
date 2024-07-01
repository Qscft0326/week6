package com.example.week5.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private Map<String, String> users = new HashMap<>();

    public UserService() {
        // 预置的用户名和密码，生产环境中应从数据库读取
        users.put("admin", "password");
        users.put("user", "password");
    }

    public String getPasswordByUsername(String username) {
        return users.get(username);
    }
}
