package io.github.zzhorizonzz.sdk.user.request;

import lombok.Data;

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
}
