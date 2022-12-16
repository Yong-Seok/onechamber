package co.dzone.ecm.user.entity;

import co.dzone.ecm.group.entity.ONEGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Transactional
class ONEUserTest {
    @PersistenceContext
    EntityManager entityManager;

    @BeforeEach
    @Test
    public void initData() throws Exception {
        ONEGroup group = ONEGroup.builder().parentUID("parentUID").groupCode("groupCode").groupType("groupType").koName("koName").enName("enName").jpName("jpName").cnName("cnName").fullPath("fullPath").groupStatus(ONEGroup.Status.WORK.getTitle()).sortOrder("1").build();
        entityManager.persist(group);

        ONEUser user = ONEUser.builder().groupUID(group.getUID()).account("yongs21").password("password").koName("용석").enName("용석").jpName("용석").cnName("용석").email("yongseok993@gmail.com").userCode("userCode").userStatus(ONEUser.Status.WORK.getTitle()).flagPrimary(true).build();
        entityManager.persist(user);

    }
}