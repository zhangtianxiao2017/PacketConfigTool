package com.test.config.dev.dao;

import com.test.config.dev.pojo.FullpathElementDef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


public interface FullPathElementDefDao extends JpaRepository<FullpathElementDef,Long> {
}
