package android.icu.compat;

import android.os.Build;

import java.util.Locale;

public class CollatorCompat {
    public static final int PRIMARY;
    public static final int SECONDARY;
    public static final int TERTIARY;
    public static final int QUATERNARY;
    public static final int IDENTICAL;

    static {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            PRIMARY = android.icu.text.Collator.PRIMARY;
            SECONDARY = android.icu.text.Collator.SECONDARY;
            TERTIARY = android.icu.text.Collator.TERTIARY;
            QUATERNARY = android.icu.text.Collator.QUATERNARY;
            IDENTICAL = android.icu.text.Collator.IDENTICAL;
        } else {
            PRIMARY = java.text.Collator.PRIMARY;
            SECONDARY = java.text.Collator.SECONDARY;
            TERTIARY = java.text.Collator.TERTIARY;
            QUATERNARY = java.text.Collator.TERTIARY; // Unfortunately, there's no quarternary in Java API
            IDENTICAL = java.text.Collator.IDENTICAL;
        }
    }

    public static RuleBasedCollatorCompat clone(Locale locale) throws CloneNotSupportedException {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return new RuleBasedCollatorCompat((android.icu.text.RuleBasedCollator) android.icu.text.Collator.getInstance(locale).clone());
        }
        return new RuleBasedCollatorCompat((java.text.RuleBasedCollator) java.text.Collator.getInstance(locale).clone());
    }
}
