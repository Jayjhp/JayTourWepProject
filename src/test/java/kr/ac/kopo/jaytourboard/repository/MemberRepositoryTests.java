package kr.ac.kopo.jaytourboard.repository;

import kr.ac.kopo.jaytourboard.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertMembers(){
        IntStream.rangeClosed(1, 10).forEach(i ->{ // 10.31 - db에 10개의 멤버 데이터만 저장. 11.01 - 추가로 11~50까지 추가 저장.
            Member member = Member.builder()
                    .email("user"+i+"@gmail.com")
                    .password("1234")
                    .name("user"+i)
                    .build();
            memberRepository.save(member);
        });
    }
}