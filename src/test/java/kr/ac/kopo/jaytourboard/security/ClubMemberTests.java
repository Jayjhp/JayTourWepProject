package kr.ac.kopo.jaytourboard.security;

import kr.ac.kopo.jaytourboard.entity.ClubMember;
import kr.ac.kopo.jaytourboard.entity.ClubMemberRole;
import kr.ac.kopo.jaytourboard.repository.ClubMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ClubMemberTests {

    @Autowired
    private ClubMemberRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies(){
//        user1 ~ user80: USER
//        user81 ~ user90: USER, MANAGER
//        user91 ~ user100: USER, MANAGER, ADMIN

        IntStream.rangeClosed(1, 100).forEach(i ->{
            ClubMember clubMember = ClubMember.builder()
                    .email("user" + i + "@kopo.ac.kr")
                    .name("사용자" + i)
                    .password(passwordEncoder.encode("1234")) // db에 인코딩된 패스워드 값이 들어감.
                    .fromSocial(false)// 실제 소셜이 아니기때문에 false로 설정
                    .build();

            clubMember.addMemberRole(ClubMemberRole.USER);

            if (i > 80){
                clubMember.addMemberRole(ClubMemberRole.MANAGER);
            }
            if (i > 90){
                clubMember.addMemberRole(ClubMemberRole.ADMIN);
            }

            repository.save(clubMember);
        });
    }

    @Test
    public void testRead(){
        Optional<ClubMember> result = repository.findByEmail("user1@kopo.ac.kr", false); // findByEmail = column 이름임. id면 fingByID로 써야됨. 규칙에 알맞게!!
        ClubMember clubMember = result.get();
        System.out.println("★★★" + clubMember.toString()); // toString()을 생략해도 같은 값으로 출력됨.
    }
}