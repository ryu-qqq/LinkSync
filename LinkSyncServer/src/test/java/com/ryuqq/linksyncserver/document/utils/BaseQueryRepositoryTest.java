package com.ryuqq.linksyncserver.document.utils;

import com.ryuqq.linksyncserver.infra.config.TestQueryDslConfig;
import com.ryuqq.linksyncserver.module.brand.repository.query.BrandQueryRepositoryImpl;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@Import({

        TestQueryDslConfig.class,
        BrandQueryRepositoryImpl.class,

})
@Transactional
public abstract class BaseQueryRepositoryTest {

    @Autowired
    private EntityManager em;

    protected EntityManager getEntityManager(){
        return em;
    }


    protected void flushAndClear() {
        flush();
        clear();
    }

    protected void flush(){
        em.flush();
    }

    protected void clear(){
        em.clear();
    }

}
