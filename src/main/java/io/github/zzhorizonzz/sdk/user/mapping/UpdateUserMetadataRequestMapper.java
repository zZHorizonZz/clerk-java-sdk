package io.github.zzhorizonzz.sdk.user.mapping;

import io.github.zzhorizonzz.sdk.common.mapping.BaseMapper;
import io.github.zzhorizonzz.sdk.common.mapping.PrivateMetadataMapper;
import io.github.zzhorizonzz.sdk.common.mapping.PublicMetadataMapper;
import io.github.zzhorizonzz.sdk.common.mapping.UnsafeMetadataMapper;
import io.github.zzhorizonzz.sdk.user.request.UpdateUserMetadataRequest;
import io.github.zzhorizonzz.client.users.item.metadata.MetadataPatchRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(config = BaseMapper.class, uses = {UnsafeMetadataMapper.class, PublicMetadataMapper.class, PrivateMetadataMapper.class})
public interface UpdateUserMetadataRequestMapper {

    @Mappings({
            @Mapping(target = "unsafeMetadata", source = "unsafeMetadata"),
            @Mapping(target = "publicMetadata", source = "publicMetadata"),
            @Mapping(target = "privateMetadata", source = "privateMetadata"),
    })
    MetadataPatchRequestBody toUsersPostRequestBody(UpdateUserMetadataRequest updateUserMetadataRequest);
}
