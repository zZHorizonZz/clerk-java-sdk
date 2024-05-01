package io.github.zzhorizonzz.sdk.user.request;

import io.github.zzhorizonzz.client.users.item.metadata.MetadataPatchRequestBody;
import io.github.zzhorizonzz.sdk.common.MetadataUtil;
import lombok.Data;

import java.util.Map;

@Data
public class UpdateUserMetadataRequest {
    private Map<String, Object> publicMetadata;
    private Map<String, Object> privateMetadata;
    private Map<String, Object> unsafeMetadata;

    public MetadataPatchRequestBody toUsersPostRequestBody() {
        MetadataPatchRequestBody metadataPatchRequestBody = new MetadataPatchRequestBody();

        metadataPatchRequestBody.setUnsafeMetadata(MetadataUtil.toMetadataPatchUnsafeMetadata(unsafeMetadata));
        metadataPatchRequestBody.setPublicMetadata(MetadataUtil.toMetadataPatchPublicMetadata(publicMetadata));
        metadataPatchRequestBody.setPrivateMetadata(MetadataUtil.toMetadataPatchPrivateMetadata(privateMetadata));

        return metadataPatchRequestBody;
    }
}
