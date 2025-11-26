package com.example.leeseo.domain.member.converter;

import com.example.leeseo.domain.member.entity.Food;
import com.example.leeseo.domain.member.entity.Member;
import com.example.leeseo.domain.member.entity.mapping.MemberFood;

public class MemberFoodConverter {

    public static MemberFood toMemberFood(
            Member member, Food food
    ){
        return MemberFood.builder()
                .member(member)
                .food(food)
                .build();
    }
}
