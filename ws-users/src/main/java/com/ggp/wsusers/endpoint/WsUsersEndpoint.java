package com.ggp.wsusers.endpoint;

import com.ggp.wsusers.Utils;
import com.ggp.wsusers.exception.InvalidParameterException;
import com.ggp.wsusers.exception.UserAlreadyExistsException;
import com.ggp.wsusers.exception.UserNotFoundException;
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

    /**
     * Get User by email
     *
     * @param request email of user
     * @return user data
     * @throws InvalidParameterException
     * @throws UserNotFoundException
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetUserByEmailRequest")
    public
    @ResponsePayload
    GetUserByEmailResponse GetUserByEmail(@RequestPayload GetUserByEmailRequest request)
            throws InvalidParameterException, UserNotFoundException {
        System.out.println("Get User");//TODO: replace with logger
        GetUserByEmailResponse response = new GetUserByEmailResponse();
        if (request == null || request.getEmail() == null) {
            throw new InvalidParameterException("email is null");
        }
        User user = userMapper.findByEmail(request.getEmail());
        if (user == null) {
            throw new UserNotFoundException("email: " + request.getEmail());
        }
        response.setUser(Utils.convertTo(user));
        return response;
    }

    /**
     * Save new user
     *
     * @param request with user data
     * @return saved user data
     * @throws UserAlreadyExistsException
     * @throws InvalidParameterException
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SaveUserRequest")
    public
    @ResponsePayload
    SaveUserResponse SaveUser(@RequestPayload SaveUserRequest request)
            throws UserAlreadyExistsException, InvalidParameterException {
        System.out.println("Save user!");
        SaveUserResponse response = new SaveUserResponse();
        validateSaveRequest(request);
        User dbUser = userMapper.findByEmail(request.getUser().getEmail());
        if (dbUser != null) {
            throw new UserAlreadyExistsException("email: " + request.getUser().getEmail());
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

    /**
     * Update user. used the userhash to find user and update fields.
     *
     * @param request new user data
     * @return updated user data
     * @throws InvalidParameterException
     * @throws UserNotFoundException
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UpdateUserRequest")
    @ResponsePayload
    UpdateUserResponse UpdateUser(@RequestPayload UpdateUserRequest request)
            throws InvalidParameterException, UserNotFoundException {
        System.out.println("update user!");
        UpdateUserResponse response = new UpdateUserResponse();
        validateUpdateRequest(request);
        User dbUser = userMapper.findByUserhash(request.getUser().getUserhash());
        if (dbUser == null) {
            throw new UserNotFoundException("userhash: " + request.getUser().getUserhash());
        }
        //update user //TODO: move to service class
        User requestUser = Utils.converTo(request.getUser());
        User user = new User();
        //fields cant be updated
        user.setId(dbUser.getId());
        user.setUserhash(dbUser.getUserhash());
        user.setCreationDate(dbUser.getCreationDate());
        user.setModificationDate(new Date());
        //fields can be updated only if arent null
        user.setFirstname((requestUser.getFirstname() != null) ? requestUser.getFirstname() : dbUser.getFirstname());
        user.setLastname((requestUser.getLastname() != null) ? requestUser.getLastname() : dbUser.getLastname());
        user.setPassword((requestUser.getPassword() != null) ? requestUser.getPassword() : dbUser.getPassword());
        user.setEmail((requestUser.getEmail() != null) ? requestUser.getEmail() : dbUser.getEmail());
        user.setRoles((requestUser.getRoles() != null) ? requestUser.getRoles() : dbUser.getRoles());
        //fields can be set to null
        user.setAddress(requestUser.getAddress());
        user.setPhone(requestUser.getPhone());
        userMapper.update(user);
        //---
        dbUser = userMapper.findByEmail(user.getEmail());
        response.setUser(Utils.convertTo(dbUser));
        return response;
    }

    private void validateUpdateRequest(UpdateUserRequest request) throws InvalidParameterException {
        Utils.notNullField(request, "request");
        Utils.notNullField(request.getUser(), "user");
        Utils.notNullField(request.getUser().getUserhash(), "userhash");
    }

    private void validateSaveRequest(SaveUserRequest request) throws InvalidParameterException {
        Utils.notNullField(request, "request");
        Utils.notNullField(request.getUser(), "user");
        Utils.notNullField(request.getUser().getEmail(), "email");
        Utils.notNullField(request.getUser().getPassword(), "password");
        Utils.notNullField(request.getUser().getFirstname(), "firstname");
        Utils.notNullField(request.getUser().getPassword(), "lastname");
    }


}
