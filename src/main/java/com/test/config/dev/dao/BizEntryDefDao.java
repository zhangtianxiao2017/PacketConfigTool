package com.test.config.dev.dao;

import com.test.config.dev.pojo.BizEntryDef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BizEntryDefDao extends JpaRepository<BizEntryDef,Long> {
}
