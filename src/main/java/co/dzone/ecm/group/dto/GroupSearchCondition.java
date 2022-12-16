package co.dzone.ecm.group.dto;

import co.dzone.ecm.base.dto.SearchCondition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GroupSearchCondition extends SearchCondition {
    private String parentUID;
    private String koName;
    private String groupStatus;
}
