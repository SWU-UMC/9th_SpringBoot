package com.example.leeseo.domain.member.dto;

public class MyPageDto {
    private Long id;
    private String email;
    private String phoneNumber;
    private Integer points;

    public MyPageDto(Long id, String email, String phoneNumber, Integer points) {
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Integer getPoints() {
        return points;
    }
}
