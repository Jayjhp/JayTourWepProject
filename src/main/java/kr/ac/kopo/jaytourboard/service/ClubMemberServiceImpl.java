package kr.ac.kopo.jaytourboard.service;

import kr.ac.kopo.jaytourboard.dto.ClubMemberDTO;
import kr.ac.kopo.jaytourboard.entity.ClubMember;
import kr.ac.kopo.jaytourboard.entity.ClubMemberRole;
import kr.ac.kopo.jaytourboard.repository.ClubMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClubMemberServiceImpl implements ClubMemberService {

    private final ClubMemberRepository clubMemberRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public ClubMember findByEmail(String email) {
        return clubMemberRepository.findByEmail(email);
    }

    @Override
    public ClubMember save(ClubMemberDTO clubMemberDTO) {
        ClubMember clubMember = ClubMember.builder()
                .email(clubMemberDTO.getEmail())
                .password(passwordEncoder.encode(clubMemberDTO.getPassword()))
                .name(clubMemberDTO.getName())
                .fromSocial(clubMemberDTO.isFromSocial())
                .build();

        Set<ClubMemberRole> roles = new HashSet<>();
        roles.add(ClubMemberRole.USER); // 기본적으로 USER 권한 추가

        return clubMemberRepository.save(clubMember);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ClubMember user = clubMemberRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoleSet())
        );
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<ClubMemberRole> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }
}
