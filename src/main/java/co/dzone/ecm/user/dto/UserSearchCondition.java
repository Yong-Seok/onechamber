package co.dzone.ecm.user.dto;

import co.dzone.ecm.base.dto.SearchCondition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSearchCondition extends SearchCondition {
    private String groupUID;
    private String account;
    private String koName;
}
