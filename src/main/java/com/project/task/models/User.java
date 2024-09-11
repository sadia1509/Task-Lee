package com.project.task.models;

import com.project.task.enums.ProviderType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 100)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    @Column(length = 1000)
    private String about;
    private String profilePictureLink;
    private String phoneNumber;

    // additional information about the user
    @Builder.Default
    private boolean enabled = false;
    @Builder.Default
    private boolean emailVerified = false;
    @Builder.Default
    private boolean phoneNumberVerified = false;

    // sign up provider type
    @Builder.Default
    @Enumerated(value = EnumType.STRING)
    private ProviderType provider = ProviderType.SELF;
    private String providerUserId;

    // add contacts
    /*
     * it will not create another table as it oneToMany for using mappedBy user table
     * cascadeAll actually deletes every contact when user gets deleted
     * fetch type lazy means it will not load from the database until they are explicitly accessed
     * orphan removal ensures that child entities that are no longer referenced by the parent entity are automatically deleted from the database.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contactList = new ArrayList<>();
}