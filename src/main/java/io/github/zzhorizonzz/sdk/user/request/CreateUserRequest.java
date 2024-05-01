package io.github.zzhorizonzz.sdk.user.request;

import io.github.zzhorizonzz.client.users.UsersPostRequestBody;
import io.github.zzhorizonzz.client.users.UsersPostRequestBodyPasswordHasher;
import io.github.zzhorizonzz.sdk.common.MetadataUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class CreateUserRequest {
    private List<String> emailAddress;
    private List<String> phoneNumber;
    private List<String> web3Wallet;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String externalId;
    private Map<String, Object> unsafeMetadata;
    private Map<String, Object> publicMetadata;
    private Map<String, Object> privateMetadata;
    private String passwordDigest;
    private String passwordHasher;
    private boolean skipPasswordRequirement;
    private boolean skipPasswordChecks;
    private String totpSecret;
    private List<String> backupCodes;
    private String createdAt;

    public UsersPostRequestBody toUsersPostRequestBody() {
        UsersPostRequestBody usersPostRequestBody = new UsersPostRequestBody();

        usersPostRequestBody.setUnsafeMetadata(MetadataUtil.toUsersPostUnsafeMetadata(unsafeMetadata));
        usersPostRequestBody.setPublicMetadata(MetadataUtil.toUsersPostPublicMetadata(publicMetadata));
        usersPostRequestBody.setPrivateMetadata(MetadataUtil.toUsersPostPrivateMetadata(privateMetadata));
        if (backupCodes != null) {
            usersPostRequestBody.setBackupCodes(new ArrayList<>(backupCodes));
        }

        usersPostRequestBody.setCreatedAt(createdAt);
        if (emailAddress != null) {
            usersPostRequestBody.setEmailAddress(new ArrayList<>(emailAddress));
        }

        usersPostRequestBody.setExternalId(externalId);
        usersPostRequestBody.setFirstName(firstName);
        usersPostRequestBody.setLastName(lastName);
        usersPostRequestBody.setPassword(password);
        usersPostRequestBody.setPasswordDigest(passwordDigest);
        if (phoneNumber != null) {
            usersPostRequestBody.setPhoneNumber(new ArrayList<>(phoneNumber));
        }

        usersPostRequestBody.setSkipPasswordChecks(skipPasswordChecks);
        usersPostRequestBody.setSkipPasswordRequirement(skipPasswordRequirement);
        usersPostRequestBody.setTotpSecret(totpSecret);
        usersPostRequestBody.setUsername(username);
        if (web3Wallet != null) {
            usersPostRequestBody.setWeb3Wallet(new ArrayList<>(web3Wallet));
        }

        usersPostRequestBody.setPasswordHasher(passwordHasher != null ? UsersPostRequestBodyPasswordHasher.forValue(passwordHasher) : null);
        return usersPostRequestBody;
    }
}
