package com.example.profileservice.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.profileservice.exception.NoProperDataException;
import com.example.profileservice.exception.ProfileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.profileservice.entity.Profile;
import com.example.profileservice.service.ProfileServiceImpl;
import com.example.profileservice.service.SequenceGeneratorService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("api/v2")
public class ProfileController {

    @Autowired
    private ProfileServiceImpl profileserviceimpl;

    @Autowired
    private SequenceGeneratorService service;

    @GetMapping("/allprofiles")
    public ResponseEntity<List<Profile>> getAllProfiles() throws ProfileNotFoundException
    {

        List<Profile> customers=profileserviceimpl.getAllProfiles();
        log.info("starting  of get mapping");

        if(customers.size()>0) {
            log.debug("customers are {}"
                    + customers);
            return new  ResponseEntity<>(customers,HttpStatus.OK);
        }
        else {
            log.debug(" no customers found ");
            return new  ResponseEntity<>(customers,HttpStatus.NO_CONTENT);
        }
    }




    @GetMapping("/profile/{id}")
    public ResponseEntity<Profile> getProfileId(@Valid @PathVariable  int id)
            throws ProfileNotFoundException

    {
        log.info("starting  of get mapping");
        Profile customers=profileserviceimpl.getProfileById(id);

        if(customers.getProfile_id()!=0) {
            log.debug("customers are {}"
                    + customers);
            return new  ResponseEntity<>(customers,HttpStatus.OK);
        }
        else {
            log.debug(" no customers found ");
            return new  ResponseEntity<>(customers,HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/addProfile")
    public ResponseEntity<Profile> addProfile(@Valid @RequestBody Profile profile)  throws NoProperDataException
    {
        if(profile!=null)
        {

            profile.setProfile_id(service.getSequenceNumber(Profile.SEQUENCE_NAME));
            profileserviceimpl.addprofile(profile);
            log.error("added profile");
            return new ResponseEntity<>(profile,HttpStatus.CREATED);

        }
        else
        {
            throw new NoProperDataException("Please fill fields");
        }


    }



    @DeleteMapping(path="/profiles/{id}")
    public ResponseEntity<String> deleteProfile(@PathVariable int id) throws ProfileNotFoundException{
        int count=1;
        for(int i=1;i>=count;count++)
        {
            if(count==1)
            {
                try {
                    profileserviceimpl.deleteProfile(id);
                } catch (ProfileNotFoundException e) {
                    log.error(e.getMessage());
                }
            }
            else
            {
                log.info("id not found");
            }
        }
        return ResponseEntity.ok(" deleted operation done ");

    }

}


