package com.depli.service;

import com.depli.entity.AuthData;
import com.depli.repository.AuthDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lpsandaruwan on 3/31/17.
 */

@Service
public class AuthDataService {

    @Autowired
    private AuthDataRepository authDataRepository;

    // Get all the auth data objects
    public List<AuthData> findAll() {
        return authDataRepository.findAll();
    }

    // Get auth data object by auth data id
    public AuthData findByAuthId(Long authId) {
        return authDataRepository.findByAuthId(authId);
    }

    // Delete by auth data id
    public long removeByAuthId(Long authId) {
        return authDataRepository.removeByAuthId(authId);
    }

    // Save auth data
    public AuthData save(AuthData authData) {
        return authDataRepository.save(authData);
    }
}
