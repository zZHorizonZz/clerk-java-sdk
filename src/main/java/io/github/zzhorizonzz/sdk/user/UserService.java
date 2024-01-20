package io.github.zzhorizonzz.sdk.user;

import io.github.zzhorizonzz.client.models.DeletedObject;
import io.github.zzhorizonzz.client.models.OrganizationMemberships;
import io.github.zzhorizonzz.client.models.TotalCount;
import io.github.zzhorizonzz.client.models.User;
import io.github.zzhorizonzz.sdk.BaseService;
import io.github.zzhorizonzz.sdk.ClerkClient;
import io.github.zzhorizonzz.sdk.user.mapping.*;
import io.github.zzhorizonzz.sdk.user.request.*;
import io.github.zzhorizonzz.client.users.item.mfa.MfaDeleteResponse;
import io.github.zzhorizonzz.client.users.item.oauth_access_tokens.item.WithProvider;
import org.mapstruct.factory.Mappers;

import java.util.List;

public class UserService extends BaseService {
    private final UserRequestMapper userRequestMapper = Mappers.getMapper(UserRequestMapper.class);
    private final ListAllUsersRequestMapper listAllUsersRequestMapper = Mappers.getMapper(ListAllUsersRequestMapper.class);
    private final UpdateUserRequestMapper updateUserRequestMapper = Mappers.getMapper(UpdateUserRequestMapper.class);
    private final UpdateUserMetadataRequestMapper updateUserMetadataRequestMapper = Mappers.getMapper(UpdateUserMetadataRequestMapper.class);
    private final ListMembershipsRequestMapper listMembershipsRequestMapper = Mappers.getMapper(ListMembershipsRequestMapper.class);

    public UserService(ClerkClient clerkClient) {
        super(clerkClient);
    }

    public User createUser(CreateUserRequest params) {
        return getHttpClient().users().post(userRequestMapper.toUsersPostRequestBody(params));
    }

    public List<User> listAll(ListAllUsersRequest params) {
        return getHttpClient().users().get((p) -> p.queryParameters = listAllUsersRequestMapper.mapToGetQueryParameters(params, p.queryParameters));
    }

    public TotalCount count(ListAllUsersRequest params) {
        return getHttpClient().users().count().get((p) -> p.queryParameters = listAllUsersRequestMapper.mapToCountQueryParameters(params, p.queryParameters));
    }

    public User read(String userId) {
        return getHttpClient().users().byUser_id(userId).get();
    }

    public DeletedObject delete(String userId) {
        return getHttpClient().users().byUser_id(userId).delete();
    }

    public User update(String userId, UpdateUserRequest updateRequest) {
        return getHttpClient().users().byUser_id(userId).patch(updateUserRequestMapper.toUsersPostRequestBody(updateRequest));
    }

    public User updateMetadata(String userId, UpdateUserMetadataRequest updateMetadataRequest) {
        return getHttpClient().users().byUser_id(userId).metadata().patch(updateUserMetadataRequestMapper.toUsersPostRequestBody(updateMetadataRequest));
    }

    public List<WithProvider> listOAuthAccessTokens(String userID, String provider) {
        return getHttpClient().users().byUser_id(userID).oauthAccessTokens().byProvider(provider).get();
    }

    public MfaDeleteResponse disableMFA(String userId) {
        return getHttpClient().users().byUser_id(userId).mfa().delete();
    }

    public User ban(String userId) {
        return getHttpClient().users().byUser_id(userId).ban().post();
    }

    public User unban(String userId) {
        return getHttpClient().users().byUser_id(userId).unban().post();
    }

    public User lock(String userId) {
        return getHttpClient().users().byUser_id(userId).lock().post();
    }

    public User unlock(String userId) {
        return getHttpClient().users().byUser_id(userId).unlock().post();
    }

    public OrganizationMemberships listMemberships(ListMembershipsRequest params) {
        return getHttpClient().users().byUser_id(params.getUserID()).organizationMemberships().get((p) -> p.queryParameters = listMembershipsRequestMapper.mapToGetQueryParameters(params, p.queryParameters));
    }
}
