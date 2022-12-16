package co.dzone.ecm.common.enummapper;

import lombok.Getter;

@Getter
public class EnumMapperValue {
    private final String code;
    private final String title;

    public EnumMapperValue(EnumMapperType enumMapperType) {
        this.code = enumMapperType.getCode();
        this.title = enumMapperType.getTitle();
    }
}
