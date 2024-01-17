package io.github.zzhorizonzz.sdk.user.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListMembershipsRequest {
    private int limit;
    private int offset;
    private String userID;
}
