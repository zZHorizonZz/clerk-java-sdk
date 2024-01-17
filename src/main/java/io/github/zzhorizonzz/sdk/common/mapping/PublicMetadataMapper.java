package io.github.zzhorizonzz.sdk.common.mapping;

import io.github.zzhorizonzz.users.UsersPostRequestBodyPublicMetadata;
import io.github.zzhorizonzz.users.item.WithUserPatchRequestBodyPublicMetadata;
import io.github.zzhorizonzz.users.item.metadata.MetadataPatchRequestBodyPublicMetadata;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper
public interface PublicMetadataMapper {
    default UsersPostRequestBodyPublicMetadata toUsersPostRequest(Map<String, Object> source) {
        UsersPostRequestBodyPublicMetadata target = new UsersPostRequestBodyPublicMetadata();
        target.setAdditionalData(source);
        return target;
    }

    default WithUserPatchRequestBodyPublicMetadata toUsersPatchRequest(Map<String, Object> source) {
        WithUserPatchRequestBodyPublicMetadata target = new WithUserPatchRequestBodyPublicMetadata();
        target.setAdditionalData(source);
        return target;
    }

    default MetadataPatchRequestBodyPublicMetadata toMetadataPatchRequest(Map<String, Object> source) {
        MetadataPatchRequestBodyPublicMetadata target = new MetadataPatchRequestBodyPublicMetadata();
        target.setAdditionalData(source);
        return target;
    }
}
