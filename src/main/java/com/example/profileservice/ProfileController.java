package com.example.profileservice;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
public class ProfileController {

@Autowired
ProfileService profileService;

    @GetMapping("/profiles")
    public String getAllProfiles() {
        return profileService.findAllProfiles();
    }

    @PostMapping(path = "/profiles", consumes = "application/json", produces = "application/json")
    public String postProfiles(@RequestBody Profile profile) {
        return profileService.saveProfile(profile.name, profile.country, profile.birthYear);
    }

    @DeleteMapping("/profiles")
    public void deleteAllProfiles() {
        profileService.removeAllProfiles();
    }
}