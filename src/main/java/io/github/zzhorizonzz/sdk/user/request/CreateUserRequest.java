package io.github.zzhorizonzz.sdk.user.request;

import lombok.Data;

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
}
