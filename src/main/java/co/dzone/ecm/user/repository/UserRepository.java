package co.dzone.ecm.user.repository;

import co.dzone.ecm.base.repository.BaseRepository;
import co.dzone.ecm.group.dto.GroupSearchCondition;
import co.dzone.ecm.group.entity.QONEGroup;
import co.dzone.ecm.user.dto.ONEUserDto;
import co.dzone.ecm.user.dto.UserSearchCondition;
import co.dzone.ecm.user.entity.ONEUser;
import co.dzone.ecm.user.entity.QONEUser;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final BaseRepository<ONEUser> baseRepository;
    private final JPAQueryFactory queryFactory;
    private final QONEUser user = QONEUser.oNEUser;

    public ONEUser save(ONEUser group) {
        return baseRepository.save(group);
    }

    public ONEUser findById(String UID) {
        return queryFactory
                .selectFrom(user)
                .where(user.UID.eq(UID))
                .fetchOne();
    }

    public Boolean exist(String UID) {
        return queryFactory
                .selectOne()
                .from(user)
                .where(user.UID.eq(UID))
                .fetchFirst() != null;
    }

    public List<ONEUser> findAll() {
        return queryFactory
                .selectFrom(user)
                .fetch();
    }

    public List<ONEUser> findByName(String name) {
        return queryFactory
                .selectFrom(user)
                .where(user.koName.eq(name))
                .fetch();
    }

    public List<ONEUserDto> findByDto() {
        return queryFactory
                .select(
                        Projections.constructor(
                                ONEUserDto.class, user.UID, user.groupUID, user.account, user.koName, user.userCode, user.userStatus, user.flagPrimary,
                                ExpressionUtils.as(QONEGroup.oNEGroup, "group")
                    )
                )
                .from(user)
                .fetch();
    }

    public List<ONEUserDto> searchByUserDto(UserSearchCondition condition) {
        return queryFactory
                .select(
                        Projections.constructor(
                                ONEUserDto.class, user.UID, user.groupUID, user.account, user.koName, user.userCode, user.userStatus, user.flagPrimary,
                                ExpressionUtils.as(QONEGroup.oNEGroup, "group")
                        )
                )
                .from(user)
                .leftJoin(QONEGroup.oNEGroup).on(user.groupUID.eq(QONEGroup.oNEGroup.UID))
                .where(
                        groupUIDEq(condition.getGroupUID()),
                        accountEq(condition.getAccount()),
                        koNameEq(condition.getKoName())
                )
                .fetch();
    }

    private BooleanExpression groupUIDEq(String groupUID) {
        return StringUtils.hasText(groupUID) ? user.groupUID.eq(groupUID) : null;
    }

    private BooleanExpression accountEq(String account) {
        return StringUtils.hasText(account) ? user.account.eq(account) : null;
    }

    private BooleanExpression koNameEq(String koName) {
        return StringUtils.hasText(koName) ? user.koName.eq(koName) : null;
    }
}
