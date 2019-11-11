package com.example.profileservice;

import java.util.List;

import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class ProfileService {
  @Autowired
  ProfileRepository profileRepository;

  public String saveProfile(String name, String country, int birthYear) {
     Profile savedProfile = this.profileRepository.save(new Profile(name, country, birthYear));
     return savedProfile.toString();
  }

  public String findAllProfiles() {
     Iterable<Profile> Profiles = this.profileRepository.findAll();
     return Lists.newArrayList(Profiles).toString();
  }

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