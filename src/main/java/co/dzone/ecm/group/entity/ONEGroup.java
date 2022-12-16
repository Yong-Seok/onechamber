package co.dzone.ecm.group.entity;

import co.dzone.ecm.base.entity.ONEBaseEntity;
import co.dzone.ecm.common.enummapper.EnumMapperType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ONEGroup")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ONEGroup extends ONEBaseEntity {

    private String parentUID;
    private String groupCode;
    private String groupType;
    private String koName;
    private String enName;
    private String jpName;
    private String cnName;
    private String fullPath;
    private String groupStatus;
    private String sortOrder;

    @Builder
    public ONEGroup(String parentUID, String groupCode, String groupType, String koName, String enName, String jpName, String cnName, String fullPath, String groupStatus, String sortOrder) {
        this.parentUID = parentUID;
        this.groupCode = groupCode;
        this.groupType = groupType;
        this.koName = koName;
        this.enName = enName;
        this.jpName = jpName;
        this.cnName = cnName;
        this.fullPath = fullPath;
        this.groupStatus = groupStatus;
        this.sortOrder = sortOrder;
    }

    public void changeName(String koName) {
        this.koName = koName;
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


