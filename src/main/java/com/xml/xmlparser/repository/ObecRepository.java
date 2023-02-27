package com.xml.xmlparser.repository;

import com.xml.xmlparser.model.Obec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObecRepository extends JpaRepository<Obec, Integer> {
}
