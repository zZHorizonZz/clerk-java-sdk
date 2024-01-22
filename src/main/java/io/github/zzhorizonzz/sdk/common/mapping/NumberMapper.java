package io.github.zzhorizonzz.sdk.common.mapping;

import org.mapstruct.Mapper;

@Mapper(config = BaseMapper.class)
public interface NumberMapper {

    default Integer mapDoubleToInt(Double value) {
        return value != null ? value.intValue() : null;
    }

    default Integer mapLongToInteger(Long value) {
        return value != null ? value.intValue() : null;
    }
}
