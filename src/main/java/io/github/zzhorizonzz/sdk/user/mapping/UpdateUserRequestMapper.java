package io.github.zzhorizonzz.sdk.user.mapping;

import io.github.zzhorizonzz.sdk.common.mapping.PrivateMetadataMapper;
import io.github.zzhorizonzz.sdk.common.mapping.PublicMetadataMapper;
import io.github.zzhorizonzz.sdk.common.mapping.UnsafeMetadataMapper;
import io.github.zzhorizonzz.sdk.user.request.UpdateUserRequest;
import io.github.zzhorizonzz.users.item.WithUserPatchRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = {UnsafeMetadataMapper.class, PublicMetadataMapper.class, PrivateMetadataMapper.class})
public interface UpdateUserRequestMapper {

    @Mappings({
            @Mapping(target = "unsafeMetadata", source = "unsafeMetadata"),
            @Mapping(target = "publicMetadata", source = "publicMetadata"),
            @Mapping(target = "privateMetadata", source = "privateMetadata"),
    })
    WithUserPatchRequestBody toUsersPostRequestBody(UpdateUserRequest updateUserRequest);
}
