package com.example.leeseo.domain.member.dto;

public class MyPageDto {
    private Long id;
    private String email;
    private String phone_number;
    private Integer points;

    public MyPageDto(Long id, String email, String phone_number, Integer points) {
        this.id = id;
        this.email = email;
        this.phone_number = phone_number;
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public Integer getPoints() {
        return points;
    }
}
