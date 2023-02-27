package com.xml.xmlparser.repository;

import com.xml.xmlparser.model.CastObce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CastObceRepository extends JpaRepository<CastObce, Integer> {
}
