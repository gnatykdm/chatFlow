package com.connect.connect.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int userId;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "register_date", nullable = false)
    private LocalDate registerDate;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private Set<Message> sentMessages;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private Set<Message> receivedMessages;

    @ManyToMany
    @JoinTable(name = "friendships",
            joinColumns = @JoinColumn(name = "user1_id"),
            inverseJoinColumns = @JoinColumn(name = "user2_id"))
    private Set<User> friends;

    public User(String username, String email,  String password, LocalDate registerDate) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.registerDate = registerDate;
    }
}
