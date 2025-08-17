package kr.ac.kopo.jaytourboard.repository;

import kr.ac.kopo.jaytourboard.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
