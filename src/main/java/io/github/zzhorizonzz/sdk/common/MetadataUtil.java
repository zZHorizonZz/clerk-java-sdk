package io.github.zzhorizonzz.sdk.common;

import io.github.zzhorizonzz.client.users.UsersPostRequestBodyPrivateMetadata;
import io.github.zzhorizonzz.client.users.UsersPostRequestBodyPublicMetadata;
import io.github.zzhorizonzz.client.users.UsersPostRequestBodyUnsafeMetadata;
import io.github.zzhorizonzz.client.users.item.WithUserPatchRequestBodyPrivateMetadata;
import io.github.zzhorizonzz.client.users.item.WithUserPatchRequestBodyPublicMetadata;
import io.github.zzhorizonzz.client.users.item.WithUserPatchRequestBodyUnsafeMetadata;
import io.github.zzhorizonzz.client.users.item.metadata.MetadataPatchRequestBodyPrivateMetadata;
import io.github.zzhorizonzz.client.users.item.metadata.MetadataPatchRequestBodyPublicMetadata;
import io.github.zzhorizonzz.client.users.item.metadata.MetadataPatchRequestBodyUnsafeMetadata;

import java.util.Map;

public class MetadataUtil {

    public static UsersPostRequestBodyPrivateMetadata toUsersPostPrivateMetadata(Map<String, Object> source) {
        UsersPostRequestBodyPrivateMetadata target = new UsersPostRequestBodyPrivateMetadata();
        target.setAdditionalData(source);
        return target;
    }

    public static WithUserPatchRequestBodyPrivateMetadata toUsersPatchPrivateMetadata(Map<String, Object> source) {
        WithUserPatchRequestBodyPrivateMetadata target = new WithUserPatchRequestBodyPrivateMetadata();
        target.setAdditionalData(source);
        return target;
    }

    public static MetadataPatchRequestBodyPrivateMetadata toMetadataPatchPrivateMetadata(Map<String, Object> source) {
        MetadataPatchRequestBodyPrivateMetadata target = new MetadataPatchRequestBodyPrivateMetadata();
        target.setAdditionalData(source);
        return target;
    }

    public static UsersPostRequestBodyPublicMetadata toUsersPostPublicMetadata(Map<String, Object> source) {
        UsersPostRequestBodyPublicMetadata target = new UsersPostRequestBodyPublicMetadata();
        target.setAdditionalData(source);
        return target;
    }

    public static WithUserPatchRequestBodyPublicMetadata toUsersPatchPublicMetadata(Map<String, Object> source) {
        WithUserPatchRequestBodyPublicMetadata target = new WithUserPatchRequestBodyPublicMetadata();
        target.setAdditionalData(source);
        return target;
    }

    public static MetadataPatchRequestBodyPublicMetadata toMetadataPatchPublicMetadata(Map<String, Object> source) {
        MetadataPatchRequestBodyPublicMetadata target = new MetadataPatchRequestBodyPublicMetadata();
        target.setAdditionalData(source);
        return target;
    }

    public static UsersPostRequestBodyUnsafeMetadata toUsersPostUnsafeMetadata(Map<String, Object> source) {
        UsersPostRequestBodyUnsafeMetadata target = new UsersPostRequestBodyUnsafeMetadata();
        target.setAdditionalData(source);
        return target;
    }

    public static WithUserPatchRequestBodyUnsafeMetadata toUsersPatchUnsafeMetadata(Map<String, Object> source) {
        WithUserPatchRequestBodyUnsafeMetadata target = new WithUserPatchRequestBodyUnsafeMetadata();
        target.setAdditionalData(source);
        return target;
    }

    public static MetadataPatchRequestBodyUnsafeMetadata toMetadataPatchUnsafeMetadata(Map<String, Object> source) {
        MetadataPatchRequestBodyUnsafeMetadata target = new MetadataPatchRequestBodyUnsafeMetadata();
        target.setAdditionalData(source);
        return target;
    }
}
