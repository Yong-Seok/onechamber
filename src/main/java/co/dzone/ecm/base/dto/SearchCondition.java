package co.dzone.ecm.base.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchCondition {
    private String UID;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
}