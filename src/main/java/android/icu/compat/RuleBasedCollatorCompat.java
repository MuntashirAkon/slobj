package android.icu.compat;

import android.os.Build;

import java.util.Objects;

public class RuleBasedCollatorCompat {
    final java.text.RuleBasedCollator jCollator;
    final android.icu.text.RuleBasedCollator icuCollator;

    public RuleBasedCollatorCompat(java.text.RuleBasedCollator jCollator) {
        this.jCollator = jCollator;
        this.icuCollator = null;
    }

    public RuleBasedCollatorCompat(android.icu.text.RuleBasedCollator icuCollator) {
        this.jCollator = null;
        this.icuCollator = icuCollator;
    }

    public void setStrength(int newStrength) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Objects.requireNonNull(icuCollator).setStrength(newStrength);
        } else if (jCollator != null) {
            Objects.requireNonNull(jCollator).setStrength(newStrength);
        }
    }

    public void setAlternateHandlingShifted(boolean shifted) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Objects.requireNonNull(icuCollator).setAlternateHandlingShifted(shifted);
        } // else not supported
    }

    public CollationKeyCompat getCollationKey(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return new CollationKeyCompat(Objects.requireNonNull(icuCollator).getCollationKey(source));
        }
        return new CollationKeyCompat(Objects.requireNonNull(jCollator).getCollationKey(source));
    }
}
