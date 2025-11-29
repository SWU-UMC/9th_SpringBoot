package com.example.leeseo.domain.member.service;

import com.example.leeseo.domain.member.converter.MemberConverter;
import com.example.leeseo.domain.member.dto.MemberReqDTO;
import com.example.leeseo.domain.member.dto.MemberResDTO;
import com.example.leeseo.domain.member.entity.Member;
import com.example.leeseo.domain.member.entity.mapping.MemberFood;
import com.example.leeseo.domain.member.enums.Role;
import com.example.leeseo.domain.member.exception.FoodException;
import com.example.leeseo.domain.member.exception.code.FoodErrorCode;
import com.example.leeseo.domain.member.repository.FoodRepository;
import com.example.leeseo.domain.member.repository.MemberRepository;
import com.example.leeseo.domain.member.repository.mapping.MemberFoodRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final FoodRepository foodRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public MemberResDTO.JoinDTO signUp(
            MemberReqDTO.JoinDTO dto
    ){
        String salt = passwordEncoder.encode(dto.password());
        Member member = MemberConverter.toMember(dto, salt, Role.ROLE_USER);
        memberRepository.save(member);
        if (!dto.preferCategory().isEmpty()){
            List<MemberFood> memberFoodList = dto.preferCategory().stream()
                    .map(id -> MemberFood.builder()
                            .member(member)
                            .food(foodRepository.findById(id)
                                    .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND)))
                            .build()
                    )
                    .collect(Collectors.toList());
            memberFoodRepository.saveAll(memberFoodList);
        }

        return MemberConverter.toJoinDTO(member);
    }
}