package co.dzone.ecm.group.repository;

import co.dzone.ecm.base.repository.BaseRepository;
import co.dzone.ecm.group.dto.GroupSearchCondition;
import co.dzone.ecm.group.dto.ONEGroupDto;
import co.dzone.ecm.group.entity.ONEGroup;
import co.dzone.ecm.group.entity.QONEGroup;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GroupRepository {
    private final JPAQueryFactory queryFactory;
    private final BaseRepository<ONEGroup> baseRepository;
    private static final QONEGroup group = QONEGroup.oNEGroup;

    public ONEGroup save(ONEGroup group) {
        return baseRepository.save(group);
    }

    public ONEGroup findById(String UID) {
        return queryFactory
                .selectFrom(group)
                .where(group.UID.eq(UID))
                .fetchOne();
    }

    public Boolean exist(String UID) {
        return queryFactory
                .selectOne()
                .from(group)
                .where(group.UID.eq(UID))
                .fetchFirst() != null;
    }

    public List<ONEGroup> findAll() {
        return queryFactory
                .selectFrom(group)
                .fetch();
    }

    public List<ONEGroup> findByName(String name) {
        return queryFactory
                .selectFrom(group)
                .where(group.koName.eq(name))
                .fetch();
    }

    public List<ONEGroupDto> findByDto() {
        return queryFactory
                .select(Projections.constructor(ONEGroupDto.class, group.UID, group.parentUID, group.koName, group.groupStatus))
                .from(group)
                .fetch();
    }

    public List<ONEGroupDto> searchByGroupDto(GroupSearchCondition condition) {
        return queryFactory
                .select(Projections.constructor(ONEGroupDto.class, group.UID, group.parentUID, group.koName, group.groupStatus))
                .from(group)
                .where(
                        parentUIDEq(condition.getParentUID()),
                        koNameEq(condition.getKoName()),
                        groupStatusEq(condition.getGroupStatus())
                )
                .fetch();
    }

    private BooleanExpression groupStatusEq(String groupStatus) {
        return StringUtils.hasText(groupStatus) ? group.groupStatus.eq(groupStatus) : null;
    }

    private BooleanExpression parentUIDEq(String parentUID) {
        return StringUtils.hasText(parentUID) ? group.parentUID.eq(parentUID) : null;
    }

    private BooleanExpression koNameEq(String koName) {
        return StringUtils.hasText(koName) ? group.koName.eq(koName) : null;
    }
}