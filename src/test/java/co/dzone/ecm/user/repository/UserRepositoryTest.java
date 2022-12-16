package co.dzone.ecm.user.repository;

import co.dzone.ecm.group.entity.ONEGroup;
import co.dzone.ecm.user.dto.ONEUserDto;
import co.dzone.ecm.user.dto.UserSearchCondition;
import co.dzone.ecm.user.entity.ONEUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Slf4j
class UserRepositoryTest {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    @Test
    public void setTest() throws Exception {
        ONEGroup group = ONEGroup.builder().parentUID("parentUID").groupCode("groupCode").groupType("groupType").koName("koName").enName("enName").jpName("jpName").cnName("cnName").fullPath("fullPath").groupStatus(ONEGroup.Status.WORK.getTitle()).sortOrder("1").build();
        entityManager.persist(group);

        ONEUser user = ONEUser.builder().groupUID(group.getUID()).account("yongs21").password("password").koName("용석").enName("용석").jpName("용석").cnName("용석").email("yongseok993@gmail.com").userCode("userCode").userStatus(ONEUser.Status.WORK.getTitle()).flagPrimary(true).build();
        entityManager.persist(user);
    }

    @Test
    public void searchByUserDto() throws Exception {
        UserSearchCondition condition = new UserSearchCondition();
        condition.setKoName("용석");

        List<ONEUserDto> oneUserDtos = userRepository.searchByUserDto(condition);
        assertThat(oneUserDtos).size().isEqualTo(1);
        assertThat(oneUserDtos).extracting("koName").containsExactly("용석");
    }
}