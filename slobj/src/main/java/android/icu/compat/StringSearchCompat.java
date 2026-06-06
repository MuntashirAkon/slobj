package android.icu.compat;

import android.os.Build;

import com.ibm.icu.text.StringSearch;

import java.text.CharacterIterator;
import java.util.Objects;

public class StringSearchCompat {
    public static final int DONE;

    static {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            DONE = android.icu.text.StringSearch.DONE;
        } else {
            DONE = StringSearch.DONE;
        }
    }

    private final StringSearch jStringSearch;
    private final android.icu.text.StringSearch icuStringSearch;

    public StringSearchCompat(String pattern, CharacterIterator target, RuleBasedCollatorCompat collator) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            jStringSearch = null;
            icuStringSearch = new android.icu.text.StringSearch(pattern, target, collator.icuCollator);
        } else {
            jStringSearch = new StringSearch(pattern, target, collator.jCollator);
            icuStringSearch = null;
        }
    }

    public int first() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Objects.requireNonNull(icuStringSearch).first();
        } else {
            return Objects.requireNonNull(jStringSearch).first();
        }
    }
}
