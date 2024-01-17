package io.github.zzhorizonzz.sdk.common.mapping;

import io.github.zzhorizonzz.users.UsersPostRequestBodyUnsafeMetadata;
import io.github.zzhorizonzz.users.item.WithUserPatchRequestBodyUnsafeMetadata;
import io.github.zzhorizonzz.users.item.metadata.MetadataPatchRequestBodyUnsafeMetadata;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper
public interface UnsafeMetadataMapper {
    default UsersPostRequestBodyUnsafeMetadata toUsersPostRequest(Map<String, Object> source) {
        UsersPostRequestBodyUnsafeMetadata target = new UsersPostRequestBodyUnsafeMetadata();
        target.setAdditionalData(source);
        return target;
    }

    default WithUserPatchRequestBodyUnsafeMetadata toUsersPatchRequest(Map<String, Object> source) {
        WithUserPatchRequestBodyUnsafeMetadata target = new WithUserPatchRequestBodyUnsafeMetadata();
        target.setAdditionalData(source);
        return target;
    }

    default MetadataPatchRequestBodyUnsafeMetadata toMetadataPatchRequest(Map<String, Object> source) {
        MetadataPatchRequestBodyUnsafeMetadata target = new MetadataPatchRequestBodyUnsafeMetadata();
        target.setAdditionalData(source);
        return target;
    }
}