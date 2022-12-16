package co.dzone.ecm.group.entity;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Transactional
@Slf4j
class ONEGroupTest {
    @PersistenceContext
    EntityManager entityManager;

    @BeforeEach
    @Test
    public void initData() throws Exception {
        ONEGroup parentGroup = ONEGroup.builder().parentUID("ROOT").groupCode("GC000").groupType("G").koName("상위 부서").enName("상위 부서").jpName("상위 부서").cnName("상위 부서").fullPath("#00000").groupStatus(ONEGroup.Status.WORK.getTitle()).sortOrder("000000000").build();
        ONEGroup childGroup = ONEGroup.builder().parentUID(parentGroup.getUID()).groupCode("GC001").groupType("G").koName("하위 부서").enName("하위 부서").jpName("하위 부서").cnName("하위 부서").fullPath("#00001").groupStatus(ONEGroup.Status.WORK.getTitle()).sortOrder("000000000").build();

        entityManager.persist(parentGroup);
        entityManager.persist(childGroup);
    }
}