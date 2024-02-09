package com.example.profileservice.repository;

import com.example.profileservice.entity.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends MongoRepository<Profile, Integer> {
    Optional<Profile> getProfileDataByEmail(String email);

}
