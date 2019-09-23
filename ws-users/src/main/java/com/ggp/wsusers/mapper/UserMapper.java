package com.ggp.wsusers.mapper;

import com.ggp.wsusers.model.User;

public interface UserMapper {

    User findByEmail(String email);

    User findByUserhash(String userhash);

    void save(User user);

    void update(User user);


}
