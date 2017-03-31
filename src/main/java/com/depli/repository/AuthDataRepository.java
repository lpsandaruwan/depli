package com.depli.repository;

import com.depli.entity.AuthData;
import com.depli.entity.JMXNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lpsandaruwan on 3/31/17.
 */

@Transactional
public interface AuthDataRepository extends Repository<JMXNode, Long> {

    // Return all auth data objects
    public List<AuthData> findAll();

    // Select by auth data Id
    public AuthData findByAuthId(Long authID);

    // Remove auth data by id
    public long removeByAuthId(Long authId);


    // Save auth data
    public AuthData save(AuthData authData);
}
