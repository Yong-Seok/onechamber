package co.dzone.ecm.group.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ONEGroupDto {
    private String uid;
    private String parentUID;
    private String koName;
    private String groupStatus;

    @Builder
    public ONEGroupDto(String uid, String parentUID, String koName, String groupStatus) {
        this.uid = uid;
        this.parentUID = parentUID;
        this.koName = koName;
        this.groupStatus = groupStatus;
    }
}
