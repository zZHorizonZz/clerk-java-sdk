package io.github.zzhorizonzz.sdk.user.request;

import lombok.Data;

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
}
