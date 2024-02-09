package com.example.profileservice.service;

import java.util.List;

import com.example.profileservice.exception.NoProperDataException;
import com.example.profileservice.exception.ProfileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.profileservice.entity.Profile;
import com.example.profileservice.repository.ProfileRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProfileServiceImpl implements ProfileService{

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public List<Profile> getAllProfiles() {
        log.info("get all profiles");
        return profileRepository.findAll();
    }

    @Override
    public Profile getProfileById(int id) throws ProfileNotFoundException {
        Profile profile=profileRepository.findById(id).orElseThrow(()-> new ProfileNotFoundException("profile Not Found"+id));
        log.debug("profiles getbyid {}",profile);
        return profile;
    }

    @Override
    public Profile addprofile(Profile profile) throws NoProperDataException {
        if(profile!=null)
        {
            profileRepository.save(profile);
            log.debug("profile added {}",profile);

        }
        else
        {
            throw new NoProperDataException("Please fill  the fields");
        }
        return profile;
    }

    @Override
    public String deleteProfile(int id) throws ProfileNotFoundException {
        log.info("Start delete");
        var isRemoved =profileRepository.findById(id);
        if(isRemoved.isPresent())
        {
            profileRepository.deleteById(id);
            log.debug("deleted successfully {}",isRemoved.get());
        }
        else
        {
            throw new ProfileNotFoundException("Id Not Available");
        }
        log.info(" deleted End");
        return " deleted successfully";
    }


}
