package org.maikuro.lang;

import com.intellij.lang.Language;

public class MaikuroLanguage extends Language {
    public static final MaikuroLanguage INSTANCE = new MaikuroLanguage();

    private MaikuroLanguage() {
        super("Maikuro");
    }
}
