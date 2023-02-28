package com.chloeCN.V1.service;

import com.chloeCN.V1.common.User;

public interface UserService {
    User getUserByUserId(Integer id);

    Integer insertUserId(User user);
}
