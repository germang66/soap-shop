package com.ggp.wsusers.endpoint;

import com.ggp.wsusers.Utils;
import com.ggp.wsusers.mapper.UserMapper;
import com.ggp.wsusers.model.User;
import com.ggp.wsusers.webservices.GetUserByEmailRequest;
import com.ggp.wsusers.webservices.GetUserByEmailResponse;
import com.ggp.wsusers.webservices.SaveUserRequest;
import com.ggp.wsusers.webservices.SaveUserResponse;
import com.ggp.wsusers.webservices.UpdateUserRequest;
import com.ggp.wsusers.webservices.UpdateUserResponse;
import com.ggp.wsusers.webservices.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Date;

@Endpoint
public class WsUsersEndpoint {

    private static final String NAMESPACE_URI = "http://webservices.wsusers.ggp.com";

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetUserByEmailRequest")
    public
    @ResponsePayload
    GetUserByEmailResponse GetUserByEmail(@RequestPayload GetUserByEmailRequest request) {
        System.out.println("Get User");//TODO: replace with logger
        GetUserByEmailResponse response = new GetUserByEmailResponse();
        if  (request == null || request.getEmail() == null) {
            System.out.println("ERROR in method parameter"); //TODO: throw exception
            return response;
        }
        User user = userMapper.findByEmail(request.getEmail());
        if (user == null) {
            System.out.println("ERROR NOT FOUND USER"); //TODO: throw exception
            return response;
        }
        response.setUser(Utils.convertTo(user));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SaveUserRequest")
    public
    @ResponsePayload
    SaveUserResponse SaveUser(@RequestPayload SaveUserRequest request) {
        System.out.println("Save user!");
        SaveUserResponse response = new SaveUserResponse();
        //TODO: improve required fields validation
        if  (request == null || request.getUser() == null ||
                request.getUser().getEmail() == null || request.getUser().getPassword() == null ||
                request.getUser().getFirstname() == null || request.getUser().getLastname() == null) {
            System.out.println("ERROR in method parameter"); //TODO: throw exception
            return response;
        }
        User dbUser = userMapper.findByEmail(request.getUser().getEmail());
        if (dbUser != null) {
            System.out.println("User already Exists"); //TODO: throw exception
            return response;
        }
        User user = Utils.converTo(request.getUser());
        user.setCreationDate(new Date());
        user.setModificationDate(new Date());
        user.setUserhash(Utils.generateHash());
        userMapper.save(user);
        dbUser = userMapper.findByEmail(user.getEmail());
        response.setUser(Utils.convertTo(dbUser));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UpdateUserRequest")
    @ResponsePayload
    UpdateUserResponse UpdateUser(@RequestPayload UpdateUserRequest request) {
        System.out.println("update user!");
        UpdateUserResponse response = new UpdateUserResponse();
        UserDetail updatedUser = new UserDetail();
        updatedUser.setEmail(request.getUser().getEmail());
        response.setUser(updatedUser);
        return response;
    }

}
