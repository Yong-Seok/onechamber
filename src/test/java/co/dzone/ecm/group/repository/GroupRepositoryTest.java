package co.dzone.ecm.group.repository;

import co.dzone.ecm.group.dto.GroupSearchCondition;
import co.dzone.ecm.group.dto.ONEGroupDto;
import co.dzone.ecm.group.entity.ONEGroup;
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
class GroupRepositoryTest {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    GroupRepository groupRepository;

    @BeforeEach
    public void initData() throws Exception {
        ONEGroup parentGroup = ONEGroup.builder().parentUID("ROOT").groupCode("GC000").groupType("G").koName("상위 부서").enName("상위 부서").jpName("상위 부서").cnName("상위 부서").fullPath("#00000").groupStatus(ONEGroup.Status.WORK.getTitle()).sortOrder("000000000").build();
        groupRepository.save(parentGroup);

        ONEGroup childGroup = ONEGroup.builder().parentUID(parentGroup.getUID()).groupCode("GC001").groupType("G").koName("하위 부서").enName("하위 부서").jpName("하위 부서").cnName("하위 부서").fullPath("#00001").groupStatus(ONEGroup.Status.WORK.getTitle()).sortOrder("000000000").build();
        groupRepository.save(childGroup);
    }

    @Test
    public void findAllAndFindByIdAndExistTest() throws Exception {
        // findAll
        List<ONEGroup> groups = groupRepository.findAll();
        assertThat(groups).extracting("koName").containsExactly("상위 부서", "하위 부서");

        // findById
        for (ONEGroup group : groups) {
            ONEGroup byId = groupRepository.findById(group.getUID());
            assertThat(group.getUID()).isEqualTo(byId.getUID());
            System.out.println("byId = " + byId);
            Boolean exist = groupRepository.exist(byId.getUID());
            assertThat(exist).isTrue();
            System.out.println("exist = " + exist);
        }
    }

    @Test
    public void findByName() throws Exception {
        List<ONEGroup> groups = groupRepository.findByName("상위 부서");
        assertThat(groups.size()).isEqualTo(1);
    }

    @Test
    public void changeName() throws Exception {
        List<ONEGroup> groups = groupRepository.findAll();
        for (ONEGroup group : groups) {
            group.changeName(group.getKoName() + "_update");
        }

        Thread.sleep(2000);
        groupRepository.save(groups.get(0));
        groupRepository.save(groups.get(1));


        List<ONEGroup> groups2 = groupRepository.findAll();
        assertThat(groups2).extracting("koName").containsExactly("상위 부서_update", "하위 부서_update");
    }

    @Test
    public void convertDtoTest() throws Exception {
        List<ONEGroupDto> result = groupRepository.findByDto();

        for (ONEGroupDto oneGroupDto : result) {
            System.out.println("oneGroupDto = " + oneGroupDto);
        }
    }

    @Test
    public void searchByGroupDto() throws Exception {
        GroupSearchCondition condition = new GroupSearchCondition();
        condition.setParentUID("ROOT");
        condition.setGroupStatus("W");
        condition.setKoName("상위 부서");

        List<ONEGroupDto> oneGroupDtos = groupRepository.searchByGroupDto(condition);
        assertThat(oneGroupDtos).hasSize(1);
        assertThat(oneGroupDtos.get(0).getKoName()).isEqualTo("상위 부서");
    }
}