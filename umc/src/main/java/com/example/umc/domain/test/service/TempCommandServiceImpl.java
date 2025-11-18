package com.example.umc.domain.test.service;

import com.example.umc.domain.test.exception.TestException;
import com.example.umc.global.apiPayload.code.TestErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TempCommandServiceImpl implements TempQueryService {

    @Override
    public void checkFlag(Long flag) {
        if(flag == 1){
            throw new TestException(TestErrorCode.TEST_EXCEPTION);
        }
    }
}
