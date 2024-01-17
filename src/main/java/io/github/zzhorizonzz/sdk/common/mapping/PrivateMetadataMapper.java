package io.github.zzhorizonzz.sdk.common.mapping;

import io.github.zzhorizonzz.users.UsersPostRequestBodyPrivateMetadata;
import io.github.zzhorizonzz.users.item.WithUserPatchRequestBodyPrivateMetadata;
import io.github.zzhorizonzz.users.item.metadata.MetadataPatchRequestBodyPrivateMetadata;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper
public interface PrivateMetadataMapper {
    default UsersPostRequestBodyPrivateMetadata toUsersPostRequest(Map<String, Object> source) {
        UsersPostRequestBodyPrivateMetadata target = new UsersPostRequestBodyPrivateMetadata();
        target.setAdditionalData(source);
        return target;
    }

    default WithUserPatchRequestBodyPrivateMetadata toUsersPatchRequest(Map<String, Object> source) {
        WithUserPatchRequestBodyPrivateMetadata target = new WithUserPatchRequestBodyPrivateMetadata();
        target.setAdditionalData(source);
        return target;
    }

    default MetadataPatchRequestBodyPrivateMetadata toMetadataPatchRequest(Map<String, Object> source) {
        MetadataPatchRequestBodyPrivateMetadata target = new MetadataPatchRequestBodyPrivateMetadata();
        target.setAdditionalData(source);
        return target;
    }
}