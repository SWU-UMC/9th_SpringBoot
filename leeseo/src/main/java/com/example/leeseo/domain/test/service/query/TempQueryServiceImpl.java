package com.example.leeseo.domain.test.service.query;

import com.example.leeseo.domain.test.exception.TestException;
import com.example.leeseo.domain.test.exception.code.TestErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TempQueryServiceImpl implements TestQueryService{

    @Override
    public void checkFlag(Long flag) {
        if (flag == 1){
            throw new TestException(TestErrorCode.TEST_EXCEPTION);
        }
    }
}
