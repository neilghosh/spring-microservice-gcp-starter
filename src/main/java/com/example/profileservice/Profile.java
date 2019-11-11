package com.example.profileservice;

import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;


@Entity(name = "profiles")
public class Profile {
        @Id
        Long id;

        String name;

        String country;

        int birthYear ;

        public Profile(String name, String country, int birthYear ) {
                this.name = name;
                this.country = country;
                this.birthYear  = birthYear ;
        }

        public long getId() {
                return this.id;
        }

        @Override
        public String toString() {
                return "Profile{" +
                                "id=" + this.id +
                                ", name='" + this.name + '\'' +
                                ", country='" + this.country + '\'' +
                                ", birthYear =" + this.birthYear  +
                                '}';
        }
}