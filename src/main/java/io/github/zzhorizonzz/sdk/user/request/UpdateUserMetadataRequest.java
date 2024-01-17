package io.github.zzhorizonzz.sdk.user.request;

import lombok.Data;

import java.util.Map;

@Data
public class UpdateUserMetadataRequest {
    private Map<String, Object> publicMetadata;
    private Map<String, Object> privateMetadata;
    private Map<String, Object> unsafeMetadata;
}
