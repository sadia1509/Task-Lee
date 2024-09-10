package com.project.task.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Contact {
    @Id // it will be custom id
    private String id;
    private String profileId;
    private String name;
    private String email;
    private String phoneNumber;
    @Column(length = 1000)
    private String details;
    private String websiteLink;
    private boolean favorite = false;

    @ManyToOne
    private User user;
    /*
     * fetch type eager means it will load from the database when contact will be accessed
     */
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<SocialLink> socialLinksList = new ArrayList<>();
}
