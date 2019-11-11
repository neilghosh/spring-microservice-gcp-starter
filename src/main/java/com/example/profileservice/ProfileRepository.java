package com.example.profileservice;

import java.util.List;

import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;


public interface ProfileRepository extends DatastoreRepository<Profile, Long> {

  List<Profile> findByName(String name);

  List<Profile> findByBirthYearGreaterThan(int birthYear);

  List<Profile> findByNameAndBirthYear(String name, int birthYear);

}