package co.dzone.ecm.user.entity;

import co.dzone.ecm.base.entity.ONEBaseEntity;
import co.dzone.ecm.common.enummapper.EnumMapperType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ONEUser")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ONEUser extends ONEBaseEntity {

    private String groupUID;
    private String account;
    private String password;
    private String koName;
    private String enName;
    private String jpName;
    private String cnName;
    private String email;
    private String userCode;
    private String userStatus;
    private boolean flagPrimary;

    @Builder
    public ONEUser(String groupUID, String account, String password, String koName, String enName, String jpName, String cnName, String email, String userCode, String userStatus, boolean flagPrimary) {
        this.groupUID = groupUID;
        this.account = account;
        this.password = password;
        this.koName = koName;
        this.enName = enName;
        this.jpName = jpName;
        this.cnName = cnName;
        this.email = email;
        this.userCode = userCode;
        this.userStatus = userStatus;
        this.flagPrimary = flagPrimary;
    }

    @RequiredArgsConstructor
    public enum Status implements EnumMapperType {
        WORK("W"),
        RETIRE("R"),
        DELETE("D");

        @Getter
        private final String title;

        @Override
        public String getCode() {
            return name();
        }
    }
}
