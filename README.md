# Clerk - Java - SDK (Unofficial)

This SDK is an unofficial Java implementation for interacting with Clerk services. It is important to note that this SDK
is not official, and is neither supported nor affiliated with Clerk.com in any way.

## Installation

You can easily install the Clerk Java SDK in your project using Maven. Add the following dependency to your `pom.xml`
file:

```xml

<dependency>
    <groupId>io.github.zzhorizonzz</groupId>
    <artifactId>clerk-java-sdk</artifactId>
    <version>VERSION</version>
</dependency>

```

## Features

The Clerk Java SDK provides a range of functionalities
making it easier to integrate Clerk services into your Java applications.
These functionalities include:

- User authentication and management
- JWT validation and session handling
- ~~TODO: Organization and roles management~~
- ~~TODO: Customizable user sessions and claims~~

## Usage

### Prerequisites

For ease of using this library with frameworks like Quarkus, the Clerk Java SDK doesn't include any HTTP client
implementation. Instead, you need to provide your own RequestAdapter.

#### Using Quarkus

When using quarkus you can use this quarkus extension:

```xml

<dependency>
    <TODO></TODO>
</dependency>

```

#### Using Basic Java

If you are using basic java, you need to add one of those three dependencies:

OKHTTP:

```xml

<dependency>
    <groupId>com.microsoft.kiota</groupId>
    <artifactId>microsoft-kiota-http-okHttp</artifactId>
    <version>VERSION</version>
</dependency>

```

If you want to use Vert.x:

```xml

<dependency>
    <groupId>io.kiota</groupId>
    <artifactId>kiota-http-vertx</artifactId>
    <version>VERSION</version>
</dependency>

```

For basic java:

```xml

<dependency>
    <groupId>io.kiota</groupId>
    <artifactId>kiota-http-jdk</artifactId>
    <version>VERSION</version>
</dependency>

```

### Setting Up

For simplicity we will use the OkHttp implementation in this example. You can use any other implementation you want. If
you want to know more you can check
the [Kiota documentation](https://learn.microsoft.com/en-us/openapi/kiota/quickstarts/java).

```java
final ApiKeyAuthenticationProvider authProvider = new ApiKeyAuthenticationProvider("token", "authorization", ApiKeyLocation.HEADER, "https://api.clerk.com/v1");
final OkHttpRequestAdapter adapter = new OkHttpRequestAdapter(authProvider);
final ClerkClient client = new ClerkClient(adapter);
```

### Creating a New User

You can create a new user using the `UserService`:

```java
UserService userService = client.getUserService();
CreateUserRequest request = new CreateUserRequest();
// Set required request parameters
User newUser = userService.createUser(request);
```

### Verifying a JWT Token

Verify the JWT tokens using `JWTService`:

```java
JWTService jwtService = client.getJWTService();
SessionClaims claims = jwtService.verifyToken("YOUR_JWT_TOKEN", new VerifyTokenOptions());
```

## Documentation

For more detailed documentation, including all available methods and their usage, please refer
to [Documentation](LINK_TO_DOCUMENTATION).

## Contribution

Contributions to the Clerk Java SDK are welcome! Please ensure you follow
the [contribution guidelines](LINK_TO_CONTRIBUTION_GUIDELINES) when submitting pull requests.

## License

This SDK is released under the [Apache License, Version 2.0](LICENSE).

## Disclaimer

This is an unofficial SDK and is not endorsed or supported by Clerk.com. Use it at your own risk.

## Contact

For any questions or suggestions regarding the SDK, feel free to open an issue in
the [GitHub repository](https://github.com/zZHorizonZz/clerk-java-sdk).
