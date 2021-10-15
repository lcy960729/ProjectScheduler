package com.lcy.ps.contents.repository;

import com.lcy.ps.contents.domain.Contents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentsRepository extends JpaRepository<Contents, Long> {

}
