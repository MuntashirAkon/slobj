package android.icu.compat;

import android.os.Build;

import java.text.CollationKey;
import java.util.Objects;

public class CollationKeyCompat implements Comparable<CollationKeyCompat> {
    private final CollationKey jCollationKey;
    private final android.icu.text.CollationKey icuCollationKey;

    public CollationKeyCompat(CollationKey jCollationKey) {
        this.jCollationKey = jCollationKey;
        this.icuCollationKey = null;
    }

    public CollationKeyCompat(android.icu.text.CollationKey icuCollationKey) {
        this.jCollationKey = null;
        this.icuCollationKey = icuCollationKey;
    }

    public byte[] toByteArray() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Objects.requireNonNull(icuCollationKey).toByteArray();
        }
        return Objects.requireNonNull(jCollationKey).toByteArray();
    }

    @Override
    public boolean equals(Object o) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (o instanceof android.icu.text.CollationKey) {
                return Objects.equals(icuCollationKey, o);
            }
            if (o instanceof CollationKeyCompat) {
                return Objects.equals(icuCollationKey, ((CollationKeyCompat) o).icuCollationKey);
            }
        } else {
            if (o instanceof CollationKey) {
                return Objects.requireNonNull(jCollationKey).equals(o);
            }
            if (o instanceof CollationKeyCompat) {
                return Objects.equals(jCollationKey, ((CollationKeyCompat) o).jCollationKey);
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Objects.requireNonNull(icuCollationKey).hashCode();
        }
        return Objects.requireNonNull(jCollationKey).hashCode();
    }

    @Override
    public int compareTo(CollationKeyCompat o) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Objects.requireNonNull(icuCollationKey).compareTo(o.icuCollationKey);
        }
        return Objects.requireNonNull(jCollationKey).compareTo(o.jCollationKey);
    }
}
