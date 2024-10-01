package com.proteinduo.domain.matchingManage.repository;

import com.proteinduo.domain.matchingManage.entity.MatchingRegister;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchMakingRepository extends JpaRepository<MatchingRegister, Long> {
}
