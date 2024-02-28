package io.github.zzhorizonzz.sdk.user.mapping;

import io.github.zzhorizonzz.sdk.common.mapping.BaseMapper;
import io.github.zzhorizonzz.sdk.common.mapping.NumberMapper;
import io.github.zzhorizonzz.sdk.user.request.ListMembershipsRequest;
import io.github.zzhorizonzz.client.users.item.organization_memberships.OrganizationMembershipsRequestBuilder;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapper.class, uses = {NumberMapper.class})
public interface ListMembershipsRequestMapper {

    default OrganizationMembershipsRequestBuilder.GetQueryParameters mapToGetQueryParameters(ListMembershipsRequest params, OrganizationMembershipsRequestBuilder.GetQueryParameters getQueryParameters) {
        getQueryParameters.limit = params.getLimit();
        getQueryParameters.offset = params.getOffset();
        return getQueryParameters;
    }
}
