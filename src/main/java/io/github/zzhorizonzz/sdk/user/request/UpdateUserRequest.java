package io.github.zzhorizonzz.sdk.user.request;

import io.github.zzhorizonzz.client.users.item.WithUserPatchRequestBody;
import io.github.zzhorizonzz.sdk.common.MetadataUtil;
import lombok.Data;

import java.util.Arrays;
import java.util.Map;

@Data
public class UpdateUserRequest {
    private String firstName;
    private String lastName;
    private String primaryEmailAddressID;
    private String primaryPhoneNumberID;
    private String primaryWeb3WalletID;
    private String username;
    private String profileImageID;
    private String profileImage;
    private String password;
    private boolean skipPasswordChecks;
    private boolean signOutOfOtherSessions;
    private String externalID;
    private Map<String, Object> publicMetadata;
    private Map<String, Object> privateMetadata;
    private Map<String, Object> unsafeMetadata;
    private String totpSecret;
    private String[] backupCodes;
    private String createdAt;

    public WithUserPatchRequestBody toUsersPostRequestBody() {
        WithUserPatchRequestBody withUserPatchRequestBody = new WithUserPatchRequestBody();

        withUserPatchRequestBody.setUnsafeMetadata(MetadataUtil.toUsersPatchUnsafeMetadata(unsafeMetadata));
        withUserPatchRequestBody.setPublicMetadata(MetadataUtil.toUsersPatchPublicMetadata(publicMetadata));
        withUserPatchRequestBody.setPrivateMetadata(MetadataUtil.toUsersPatchPrivateMetadata(privateMetadata));
        withUserPatchRequestBody.setBackupCodes(Arrays.stream(backupCodes).toList());
        withUserPatchRequestBody.setCreatedAt(createdAt);
        withUserPatchRequestBody.setFirstName(firstName);
        withUserPatchRequestBody.setLastName(lastName);
        withUserPatchRequestBody.setPassword(password);
        withUserPatchRequestBody.setSignOutOfOtherSessions(signOutOfOtherSessions);
        withUserPatchRequestBody.setSkipPasswordChecks(skipPasswordChecks);
        withUserPatchRequestBody.setTotpSecret(totpSecret);
        withUserPatchRequestBody.setUsername(username);

        return withUserPatchRequestBody;
    }
}
