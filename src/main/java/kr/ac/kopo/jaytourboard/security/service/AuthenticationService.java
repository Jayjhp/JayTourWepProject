package kr.ac.kopo.jaytourboard.security.service;

import kr.ac.kopo.jaytourboard.entity.ClubMember;
import kr.ac.kopo.jaytourboard.repository.ClubMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final ClubMemberRepository clubMemberRepository; // ClubMemberRepository를 주입받음

    public boolean authenticate(String username, String password) {
        // DB에서 사용자 정보 조회
        ClubMember clubMember = clubMemberRepository.findByEmail(username, false).orElse(null);

        // 사용자 정보가 없거나 비밀번호가 일치하지 않으면 로그인 실패
        if (clubMember == null || !clubMember.getPassword().equals(password)) {
            return false; // 로그인 실패
        }

        return true; // 로그인 성공
    }
}
