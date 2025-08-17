package kr.ac.kopo.jaytourboard.repository;

import kr.ac.kopo.jaytourboard.entity.ClubMember;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClubMemberRepository extends JpaRepository<ClubMember, String> {

    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from ClubMember m where m.email = :email and m.fromSocial = :social") // social과 email 사이에 공백은 존재하면 안됨.
    Optional<ClubMember> findByEmail(String email, boolean social);

    ClubMember findByEmail(String email);
}