package com.idus.homework.member.infra.repository;


import com.idus.homework.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, MemberCustomRepository {
    Member findByEmail(String userEmail);
}
