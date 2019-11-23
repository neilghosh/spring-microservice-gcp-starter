package com.example.profileservice;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;

@RestController
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @Value("${home.image}")
    private String homeImageName;

    @GetMapping("/profiles")
    public String getAllProfiles() {
        return profileService.findAllProfiles();
    }

    @GetMapping(value="/image",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getImage() throws IOException {
        InputStream in = getClass()
        .getResourceAsStream("/static/"+homeImageName);
      return IOUtils.toByteArray(in);
    }

    @GetMapping("/system")
    @ResponseBody
    public String getSysProps() {
        Properties pros = System.getProperties();
        String response = "";
        Enumeration keys = pros.keys();
        while (keys.hasMoreElements()) {
            String key = (String)keys.nextElement();
            String value = (String)pros.get(key);
            response =  response + "\n "+key + ": " + value;
        }
        return response;
    }

    @GetMapping("/profiles/{name}")
    public String getByName(@PathVariable String name) {
        return profileService.findByName(name);
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