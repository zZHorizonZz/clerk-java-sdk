package io.github.zzhorizonzz.sdk.jwk;

import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.JsonWebKeySet;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class JWKCache {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private JsonWebKeySet jwks;
    private Instant expiresAt;

    public boolean isInvalid() {
        lock.readLock().lock();
        try {
            return jwks == null || jwks.getJsonWebKeys().isEmpty() || Instant.now().isAfter(expiresAt);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void set(JsonWebKeySet jwks) {
        lock.writeLock().lock();
        try {
            this.jwks = jwks;
            this.expiresAt = Instant.now().plusSeconds(3600); // Expires after 1 hour
        } finally {
            lock.writeLock().unlock();
        }
    }

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
