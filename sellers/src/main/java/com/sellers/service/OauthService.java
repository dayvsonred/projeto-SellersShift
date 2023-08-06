package com.sellers.service;

import com.sellers.dto.UserDto;
import com.sellers.dto.oauth.UserTokenDTO;
import com.sellers.integration.OauthIntegration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class OauthService {

    @Autowired
    private OauthIntegration oauthIntegration;

    public UserTokenDTO getUserData(String token){
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", token);

            return oauthIntegration.findUserByToken(httpHeaders);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR on find user data by token: " + token);
        }
    }

    public UserDto getUserByToken(String token) {
        UserTokenDTO userTokenDTO = this.getUserData(token);
        UserDto user = new UserDto();
        user.setId(userTokenDTO.getId());

        return user;
    }
}
