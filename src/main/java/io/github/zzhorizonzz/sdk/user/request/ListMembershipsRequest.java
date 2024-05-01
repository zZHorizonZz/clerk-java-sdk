package io.github.zzhorizonzz.sdk.user.request;

import io.github.zzhorizonzz.client.users.item.organization_memberships.OrganizationMembershipsRequestBuilder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListMembershipsRequest {
    private int limit;
    private int offset;
    private String userID;

    public OrganizationMembershipsRequestBuilder.GetQueryParameters mapToGetQueryParameters(OrganizationMembershipsRequestBuilder.GetQueryParameters getQueryParameters) {
        if (getQueryParameters == null) {
            return null;
        }

        getQueryParameters.limit = limit;
        getQueryParameters.offset = offset;

        return getQueryParameters;
    }
}
