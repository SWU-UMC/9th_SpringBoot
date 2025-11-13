package com.example.leeseo.domain.member.service;

import com.example.leeseo.domain.member.converter.MemberConverter;
import com.example.leeseo.domain.member.converter.MemberFoodConverter;
import com.example.leeseo.domain.member.dto.MemberReqDTO;
import com.example.leeseo.domain.member.dto.MemberResDTO;
import com.example.leeseo.domain.member.entity.Food;
import com.example.leeseo.domain.member.entity.Member;
import com.example.leeseo.domain.member.entity.mapping.MemberFood;
import com.example.leeseo.domain.member.exception.FoodException;
import com.example.leeseo.domain.member.exception.code.FoodErrorCode;
import com.example.leeseo.domain.member.repository.FoodRepository;
import com.example.leeseo.domain.member.repository.MemberRepository;
import com.example.leeseo.domain.member.repository.mapping.MemberFoodRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final FoodRepository foodRepository;

    @Override
    @Transactional
    public MemberResDTO.JoinDTO signUp(MemberReqDTO.JoinDTO dto)
    {
        Member member = MemberConverter.toMember(dto);
        if (dto.preferCategory().size() > 1){
            List<MemberFood> memberFoodList = new ArrayList<>();

            for (Long id : dto.preferCategory()){
                Food food = foodRepository.findById(id)
                        .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND));

                MemberFood memberFood = MemberFoodConverter.toMemberFood(member, food);

                memberFoodList.add(memberFood);
            }
            memberFoodRepository.saveAll(memberFoodList);
        }

        return MemberConverter.toJoinDTO(member);
    }
}