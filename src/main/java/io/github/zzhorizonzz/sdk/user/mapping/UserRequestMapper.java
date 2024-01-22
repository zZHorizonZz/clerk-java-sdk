package io.github.zzhorizonzz.sdk.user.mapping;

import io.github.zzhorizonzz.client.users.UsersPostRequestBody;
import io.github.zzhorizonzz.sdk.common.mapping.BaseMapper;
import io.github.zzhorizonzz.sdk.common.mapping.PrivateMetadataMapper;
import io.github.zzhorizonzz.sdk.common.mapping.PublicMetadataMapper;
import io.github.zzhorizonzz.sdk.common.mapping.UnsafeMetadataMapper;
import io.github.zzhorizonzz.sdk.user.request.CreateUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(config = BaseMapper.class, uses = {UnsafeMetadataMapper.class, PublicMetadataMapper.class, PrivateMetadataMapper.class})
public interface UserRequestMapper {

    @Mappings({
            @Mapping(target = "unsafeMetadata", source = "unsafeMetadata"),
            @Mapping(target = "publicMetadata", source = "publicMetadata"),
            @Mapping(target = "privateMetadata", source = "privateMetadata"),
            @Mapping(target = "passwordHasher", expression = "java(createUserRequest.getPasswordHasher() != null ? io.github.zzhorizonzz.client.users.UsersPostRequestBodyPasswordHasher.forValue(createUserRequest.getPasswordHasher()) : null)"),
    })
    UsersPostRequestBody toUsersPostRequestBody(CreateUserRequest createUserRequest);
}
