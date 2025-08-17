package kr.ac.kopo.jaytourboard.service;

import kr.ac.kopo.jaytourboard.dto.ClubMemberDTO;
import kr.ac.kopo.jaytourboard.entity.ClubMember;
import kr.ac.kopo.jaytourboard.repository.ClubMemberRepository;
import kr.ac.kopo.jaytourboard.security.service.ClubUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface ClubMemberService extends UserDetailsService {

    ClubMember findByEmail(String email);

    ClubMember save(ClubMemberDTO clubMemberDTO);
}
