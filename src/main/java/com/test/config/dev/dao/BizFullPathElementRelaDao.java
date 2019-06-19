package com.test.config.dev.dao;

import com.test.config.dev.pojo.BizFactorDef;
import com.test.config.dev.pojo.BizFullpathElementRela;
import com.test.config.dev.pojo.FullpathElementDef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BizFullPathElementRelaDao extends JpaRepository<BizFullpathElementRela,Long> , CrudRepository<BizFullpathElementRela,Long> {
}
