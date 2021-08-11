package com.idus.homework.member.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "idus_member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String password;

    @Column(length = 20)
    private String name;

    @Column(length = 30)
    private String alias;

    @Column(length = 100)
    private String email;

    @Column(length = 20)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column
    private Gender gender = Gender.UNKNOWN;

    @Column
    private LocalDateTime lastLoginDate;

    @Builder
    public Member(String email, String password, String name, String alias, String phone,
                  Role role, LocalDateTime lastLoginDate, Gender gender) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.alias = alias;
        this.phone = phone;
        this.role = role;
        this.lastLoginDate = lastLoginDate;
        this.gender = gender;
    }

    public void lastLoginDateUpdate() {
        this.lastLoginDate = LocalDateTime.now();
    }
}