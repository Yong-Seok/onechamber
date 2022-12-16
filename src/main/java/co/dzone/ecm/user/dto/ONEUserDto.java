package co.dzone.ecm.user.dto;

import co.dzone.ecm.group.entity.ONEGroup;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ONEUserDto {

    private String uid;
    private String groupUID;
    private String account;
    private String koName;
    private String userCode;
    private String userStatus;
    private boolean flagPrimary;
    private ONEGroup group;

    @Builder
    public ONEUserDto(String uid, String groupUID, String account, String koName, String userCode, String userStatus, boolean flagPrimary, ONEGroup group) {
        this.uid = uid;
        this.groupUID = groupUID;
        this.account = account;
        this.koName = koName;
        this.userCode = userCode;
        this.userStatus = userStatus;
        this.flagPrimary = flagPrimary;
        this.group = group;
    }
}
