package com.ggp.wsusers;

import com.ggp.wsusers.model.User;
import com.ggp.wsusers.webservices.UserDetail;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {

    public static UserDetail convertTo(User user) {
        if (user == null) return null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        UserDetail userDetail = new UserDetail();
        userDetail.setUserhash(user.getUserhash());
        if (user.getCreationDate() != null) {
            userDetail.setCreationDate(dateFormat.format(user.getCreationDate()));
        }
        if (user.getModificationDate() != null) {
            userDetail.setModificationDate(dateFormat.format(user.getModificationDate()));
        }
        userDetail.setFirstname(user.getFirstname());
        userDetail.setLastname(user.getLastname());
        userDetail.setPassword(user.getPassword());
        userDetail.setAddress(user.getAddress());
        userDetail.setPhone(user.getPhone());
        userDetail.setEmail(user.getEmail());
        userDetail.setRoles(user.getRoles());
        return userDetail;
    }

    public static User converTo(UserDetail userDetail) {
        if (userDetail == null) return null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        User user = new User();
        user.setUserhash(userDetail.getUserhash());
        try {
            if (userDetail.getCreationDate() != null) {
                user.setCreationDate(dateFormat.parse(userDetail.getCreationDate()));
            }
            if (userDetail.getModificationDate() != null) {
                user.setModificationDate(dateFormat.parse(userDetail.getModificationDate()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setFirstname(userDetail.getFirstname());
        user.setLastname(userDetail.getLastname());
        user.setPassword(userDetail.getPassword());
        user.setAddress(userDetail.getAddress());
        user.setPhone(userDetail.getPhone());
        user.setEmail(userDetail.getEmail());
        user.setRoles(userDetail.getRoles());
        return user;
    }

}
