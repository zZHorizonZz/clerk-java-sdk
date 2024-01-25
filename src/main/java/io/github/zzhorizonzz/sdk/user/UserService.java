package io.github.zzhorizonzz.sdk.user;

import io.github.zzhorizonzz.client.models.DeletedObject;
import io.github.zzhorizonzz.client.models.OrganizationMemberships;
import io.github.zzhorizonzz.client.models.TotalCount;
import io.github.zzhorizonzz.client.models.User;
import io.github.zzhorizonzz.client.users.item.mfa.MfaDeleteResponse;
import io.github.zzhorizonzz.client.users.item.oauth_access_tokens.item.WithProvider;
import io.github.zzhorizonzz.sdk.BaseService;
import io.github.zzhorizonzz.sdk.ClerkClient;
import io.github.zzhorizonzz.sdk.user.mapping.*;
import io.github.zzhorizonzz.sdk.user.request.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.function.Consumer;

public class UserService extends BaseService {
    private final UserRequestMapper userRequestMapper = Mappers.getMapper(UserRequestMapper.class);
    private final ListAllUsersRequestMapper listAllUsersRequestMapper = Mappers.getMapper(ListAllUsersRequestMapper.class);
    private final UpdateUserRequestMapper updateUserRequestMapper = Mappers.getMapper(UpdateUserRequestMapper.class);
    private final UpdateUserMetadataRequestMapper updateUserMetadataRequestMapper = Mappers.getMapper(UpdateUserMetadataRequestMapper.class);
    private final ListMembershipsRequestMapper listMembershipsRequestMapper = Mappers.getMapper(ListMembershipsRequestMapper.class);

    public UserService(ClerkClient clerkClient) {
        super(clerkClient);
    }


    /**
     * @see io.github.zzhorizonzz.client.users.UsersRequestBuilder#post(io.github.zzhorizonzz.client.users.UsersPostRequestBody)
     */
    public User createUser(CreateUserRequest params) {
        return getHttpClient().users().post(userRequestMapper.toUsersPostRequestBody(params));
    }

    /**
     * @see io.github.zzhorizonzz.client.users.UsersRequestBuilder#get()
     */
    public List<User> listAll(ListAllUsersRequest params) {
        return getHttpClient().users().get((p) -> p.queryParameters = listAllUsersRequestMapper.mapToGetQueryParameters(params, p.queryParameters));
    }

    /**
     * @see io.github.zzhorizonzz.client.users.count.CountRequestBuilder#get()
     */
    public TotalCount count(ListAllUsersRequest params) {
        return getHttpClient().users().count().get((p) -> p.queryParameters = listAllUsersRequestMapper.mapToCountQueryParameters(params, p.queryParameters));
    }

    /**
     * @see io.github.zzhorizonzz.client.users.item.WithUserItemRequestBuilder#get()
     */
    public User read(String userId) {
        return getHttpClient().users().byUser_id(userId).get();
    }

    /**
     * @see io.github.zzhorizonzz.client.users.item.WithUserItemRequestBuilder#delete()
     */
    public DeletedObject delete(String userId) {
        return getHttpClient().users().byUser_id(userId).delete();
    }

    /**
     * @see io.github.zzhorizonzz.client.users.item.WithUserItemRequestBuilder#patch(io.github.zzhorizonzz.client.users.item.WithUserPatchRequestBody)
     */
    public User update(String userId, UpdateUserRequest updateRequest) {
        return getHttpClient().users().byUser_id(userId).patch(updateUserRequestMapper.toUsersPostRequestBody(updateRequest));
    }

    /**
     * @see io.github.zzhorizonzz.client.users.item.metadata.MetadataRequestBuilder#patch(io.github.zzhorizonzz.client.users.item.metadata.MetadataPatchRequestBody)
     */
    public User updateMetadata(String userId, UpdateUserMetadataRequest updateMetadataRequest) {
        return getHttpClient().users().byUser_id(userId).metadata().patch(updateUserMetadataRequestMapper.toUsersPostRequestBody(updateMetadataRequest));
    }

    /**
     * @see io.github.zzhorizonzz.client.users.item.oauth_access_tokens.item.WithProviderItemRequestBuilder#get()
     */
    public List<WithProvider> listOAuthAccessTokens(String userID, String provider) {
        return getHttpClient().users().byUser_id(userID).oauthAccessTokens().byProvider(provider).get();
    }

    /**
     * @see io.github.zzhorizonzz.client.users.item.WithUserItemRequestBuilder#delete()
     */
    public MfaDeleteResponse disableMFA(String userId) {
        return getHttpClient().users().byUser_id(userId).mfa().delete();
    }

    /**
     * @see io.github.zzhorizonzz.client.users.item.ban.BanRequestBuilder#post()
     */
    public User ban(String userId) {
        return getHttpClient().users().byUser_id(userId).ban().post();
    }

    /**
     * @see io.github.zzhorizonzz.client.users.item.unban.UnbanRequestBuilder#post()
     */
    public User unban(String userId) {
        return getHttpClient().users().byUser_id(userId).unban().post();
    }

    /**
     * @see io.github.zzhorizonzz.client.users.item.lock.LockRequestBuilder#post()
     */
    public User lock(String userId) {
        return getHttpClient().users().byUser_id(userId).lock().post();
    }

    /**
     * @see io.github.zzhorizonzz.client.users.item.unlock.UnlockRequestBuilder#post()
     */
    public User unlock(String userId) {
        return getHttpClient().users().byUser_id(userId).unlock().post();
    }

    /**
     * @see io.github.zzhorizonzz.client.users.item.organization_memberships.OrganizationMembershipsRequestBuilder#get(Consumer)
     */
    public OrganizationMemberships listMemberships(ListMembershipsRequest params) {
        return getHttpClient().users().byUser_id(params.getUserID()).organizationMemberships().get((p) -> p.queryParameters = listMembershipsRequestMapper.mapToGetQueryParameters(params, p.queryParameters));
    }
}
