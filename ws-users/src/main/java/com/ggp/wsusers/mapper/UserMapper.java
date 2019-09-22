package com.ggp.wsusers.mapper;

import com.ggp.wsusers.model.User;

public interface UserMapper {

    User findByEmail(String email);

    User save(User user);

    User update(User user);


}
