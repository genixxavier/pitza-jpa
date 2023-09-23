package com.gnxcode.pitzza.persitence.repository;

import com.gnxcode.pitzza.persitence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {
}
