package io.github.zzhorizonzz.sdk.jwk;

import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.JsonWebKeySet;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Caches JSON Web Keys (JWKs) and manages their lifecycle.
 * It provides thread-safe operations to set and retrieve keys, and to check the cache validity.
 * <p>
 * Source: <a href="https://github.com/clerk/clerk-sdk-go/blob/main/clerk/jwks_cache.go">JWK Cache</a>
 */
public class JWKCache {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private JsonWebKeySet jwks;
    private Instant expiresAt;

    /**
     * Checks if the cached JWKs are invalid (i.e., null, empty, or expired).
     *
     * @return true if the cache is invalid, false otherwise.
     */
    public boolean isInvalid() {
        lock.readLock().lock();
        try {
            return jwks == null || jwks.getJsonWebKeys().isEmpty() || Instant.now().isAfter(expiresAt);
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Sets the JWKs in the cache with a default expiration time of 1 hour.
     *
     * @param jwks The JsonWebKeySet to cache.
     */
    public void set(JsonWebKeySet jwks) {
        lock.writeLock().lock();
        try {
            this.jwks = jwks;
            this.expiresAt = Instant.now().plusSeconds(3600); // Expires after 1 hour
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Retrieves a JWK from the cache by its Key ID (kid).
     *
     * @param kid The Key ID of the JWK to retrieve.
     * @return The JsonWebKey associated with the given Key ID.
     * @throws Exception If no JWK is found for the provided Key ID.
     */
    public JsonWebKey get(String kid) throws Exception {
        lock.readLock().lock();
        try {
            List<JsonWebKey> keys = jwks.getJsonWebKeys();
            for (JsonWebKey key : keys) {
                if (key.getKeyId().equals(kid)) {
                    return key;
                }
            }
        } finally {
            lock.readLock().unlock();
        }

        throw new Exception("No JWK key found for kid " + kid);
    }
}
