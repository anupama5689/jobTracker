package com.jobTracker.entity;
import jakarta.validation.constraints.NotBlank;



import jakarta.persistence.*;

    @Entity
    @Table(name = "companies")
    public class Company {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank(message = "Company name is required")
        private String name;
        @NotBlank(message = "Company website is required")
        private String website;
        @NotBlank(message = "Company location is required")
        private String location;
        @NotBlank(message = "Company description is required")
        private String description;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

