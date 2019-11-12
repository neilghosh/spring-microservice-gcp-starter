package com.example.profileservice;

import java.util.List;

import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;

@Service
public class ProfileService {
   @Autowired
   ProfileRepository profileRepository;

   @Autowired
   private StringRedisTemplate template;

   @CachePut(value = "name", key = "#name")
   public String saveProfile(String name, String country, int birthYear) {
      return saveProfile(new Profile(name, country, birthYear));
   }

   private String saveProfile(Profile profile) {
      Profile savedProfile = this.profileRepository.save(profile);
      return savedProfile.toString();
   }

   public String findAllProfiles() {
      Iterable<Profile> Profiles = this.profileRepository.findAll();
      return Lists.newArrayList(Profiles).toString();
   }

   @Cacheable("name")
   public String findByName(String name) {
      List<Profile> Profiles = this.profileRepository.findByName(name);
      return Profiles.toString();
   }

   public String findByBirthYearAfter(int birthYear) {
      List<Profile> Profiles = this.profileRepository.findByBirthYearGreaterThan(birthYear);
      return Profiles.toString();
   }

   public String findByNameBirthYear(String name, int birthYear) {
      List<Profile> Profiles = this.profileRepository.findByNameAndBirthYear(name, birthYear);
      return Profiles.toString();
   }

   public void removeAllProfiles() {
      this.profileRepository.deleteAll();
   }
}