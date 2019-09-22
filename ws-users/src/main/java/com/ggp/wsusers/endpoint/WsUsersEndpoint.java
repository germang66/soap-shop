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
        GetUserByEmailResponse response = new GetUserByEmailResponse();
        if  (request == null || request.getEmail() == null) {
            System.out.println("ERROR in method parameter"); //TODO: throw exception
            return response;
        }
        System.out.println("Get User");//TODO: replace with logger
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
        UserDetail savedUser = new UserDetail();
        savedUser.setEmail(request.getUser().getEmail());
        response.setUser(savedUser);
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
