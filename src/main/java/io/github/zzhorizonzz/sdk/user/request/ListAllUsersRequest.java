package io.github.zzhorizonzz.sdk.user.request;

import io.github.zzhorizonzz.client.users.UsersRequestBuilder;
import io.github.zzhorizonzz.client.users.count.CountRequestBuilder;
import lombok.Data;

import java.util.Arrays;

@Data
public class ListAllUsersRequest {
    private int limit;
    private int offset;
    private String[] emailAddress;
    private String[] externalId;
    private String[] organizationId;
    private String[] phoneNumber;
    private String[] web3Wallet;
    private String[] username;
    private String[] userId;
    private String query;
    private long lastActiveAtSince;
    private String orderBy;

    public UsersRequestBuilder.GetQueryParameters mapToGetQueryParameters(UsersRequestBuilder.GetQueryParameters getQueryParameters) {
        if(getQueryParameters == null) {
            return null;
        }

        getQueryParameters.limit = limit;
        getQueryParameters.offset = offset;
        getQueryParameters.lastActiveAtSince = Math.toIntExact(lastActiveAtSince);

        if (emailAddress != null) {
            getQueryParameters.emailAddress = Arrays.copyOf(emailAddress, emailAddress.length);
        }

        if (externalId != null) {
            getQueryParameters.externalId = Arrays.copyOf(externalId, externalId.length);
        }

        getQueryParameters.orderBy = orderBy;
        if (organizationId != null) {
            getQueryParameters.organizationId = Arrays.copyOf(organizationId, organizationId.length);
        }

        if (phoneNumber != null) {
            getQueryParameters.phoneNumber = Arrays.copyOf(phoneNumber, phoneNumber.length);
        }

        getQueryParameters.query = query;
        if (userId != null) {
            getQueryParameters.userId = Arrays.copyOf(userId, userId.length);
        }

        if (username != null) {
            getQueryParameters.username = Arrays.copyOf(username, username.length);
        }

        if (web3Wallet != null) {
            getQueryParameters.web3Wallet = Arrays.copyOf(web3Wallet, web3Wallet.length);
        }

        return getQueryParameters;
    }

    public CountRequestBuilder.GetQueryParameters mapToCountQueryParameters(CountRequestBuilder.GetQueryParameters getQueryParameters) {
        if(getQueryParameters == null) {
            return null;
        }

        if (emailAddress != null) {
            getQueryParameters.emailAddress = Arrays.copyOf(emailAddress, emailAddress.length);
        }

        if (externalId != null) {
            getQueryParameters.externalId = Arrays.copyOf(externalId, externalId.length);
        }

        if (phoneNumber != null) {
            getQueryParameters.phoneNumber = Arrays.copyOf(phoneNumber, phoneNumber.length);
        }

        getQueryParameters.query = query;

        if (userId != null) {
            getQueryParameters.userId = Arrays.copyOf(userId, userId.length);
        }

        if (username != null) {
            getQueryParameters.username = Arrays.copyOf(username, username.length);
        }

        if (web3Wallet != null) {
            getQueryParameters.web3Wallet = Arrays.copyOf(web3Wallet, web3Wallet.length);
        }

        return getQueryParameters;
    }
}
