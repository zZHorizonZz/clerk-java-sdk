package io.github.zzhorizonzz.sdk.user.mapping;

import io.github.zzhorizonzz.sdk.common.mapping.BaseMapper;
import io.github.zzhorizonzz.sdk.common.mapping.NumberMapper;
import io.github.zzhorizonzz.sdk.user.request.ListAllUsersRequest;
import io.github.zzhorizonzz.client.users.UsersRequestBuilder;
import io.github.zzhorizonzz.client.users.count.CountRequestBuilder;
import org.mapstruct.Mapper;

import java.util.Arrays;

@Mapper(config = BaseMapper .class, uses = {NumberMapper.class})
public interface ListAllUsersRequestMapper {


    default UsersRequestBuilder.GetQueryParameters mapToGetQueryParameters(ListAllUsersRequest params, UsersRequestBuilder.GetQueryParameters getQueryParameters) {
        if (params == null) {
            return null;
        }

        getQueryParameters.limit = (double) params.getLimit();
        getQueryParameters.offset = (double) params.getOffset();
        getQueryParameters.lastActiveAtSince = Math.toIntExact(params.getLastActiveAtSince());

        String[] emailAddress = params.getEmailAddress();
        if (emailAddress != null) {
            getQueryParameters.emailAddress = Arrays.copyOf(emailAddress, emailAddress.length);
        }
        String[] externalId = params.getExternalId();
        if (externalId != null) {
            getQueryParameters.externalId = Arrays.copyOf(externalId, externalId.length);
        }
        getQueryParameters.orderBy = params.getOrderBy();
        String[] organizationId = params.getOrganizationId();
        if (organizationId != null) {
            getQueryParameters.organizationId = Arrays.copyOf(organizationId, organizationId.length);
        }
        String[] phoneNumber = params.getPhoneNumber();
        if (phoneNumber != null) {
            getQueryParameters.phoneNumber = Arrays.copyOf(phoneNumber, phoneNumber.length);
        }
        getQueryParameters.query = params.getQuery();
        String[] userId = params.getUserId();
        if (userId != null) {
            getQueryParameters.userId = Arrays.copyOf(userId, userId.length);
        }
        String[] username = params.getUsername();
        if (username != null) {
            getQueryParameters.username = Arrays.copyOf(username, username.length);
        }
        String[] web3Wallet = params.getWeb3Wallet();
        if (web3Wallet != null) {
            getQueryParameters.web3Wallet = Arrays.copyOf(web3Wallet, web3Wallet.length);
        }

        return getQueryParameters;
    }

    default CountRequestBuilder.GetQueryParameters mapToCountQueryParameters(ListAllUsersRequest params, CountRequestBuilder.GetQueryParameters getQueryParameters) {
        if (params == null) {
            return null;
        }

        String[] emailAddress = params.getEmailAddress();
        if (emailAddress != null) {
            getQueryParameters.emailAddress = Arrays.copyOf(emailAddress, emailAddress.length);
        }
        String[] externalId = params.getExternalId();
        if (externalId != null) {
            getQueryParameters.externalId = Arrays.copyOf(externalId, externalId.length);
        }
        String[] phoneNumber = params.getPhoneNumber();
        if (phoneNumber != null) {
            getQueryParameters.phoneNumber = Arrays.copyOf(phoneNumber, phoneNumber.length);
        }
        getQueryParameters.query = params.getQuery();
        String[] userId = params.getUserId();
        if (userId != null) {
            getQueryParameters.userId = Arrays.copyOf(userId, userId.length);
        }
        String[] username = params.getUsername();
        if (username != null) {
            getQueryParameters.username = Arrays.copyOf(username, username.length);
        }
        String[] web3Wallet = params.getWeb3Wallet();
        if (web3Wallet != null) {
            getQueryParameters.web3Wallet = Arrays.copyOf(web3Wallet, web3Wallet.length);
        }

        return getQueryParameters;
    }
}
