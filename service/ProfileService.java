package com.example.profileservice.service;

import java.util.List;

import com.example.profileservice.entity.Profile;
import com.example.profileservice.exception.NoProperDataException;
import com.example.profileservice.exception.ProfileNotFoundException;


public interface ProfileService {
    public List<Profile> getAllProfiles();
    public Profile getProfileById(int id ) throws ProfileNotFoundException;
    public Profile addprofile( Profile profile) throws NoProperDataException;
    public String deleteProfile(int id) throws ProfileNotFoundException;

}