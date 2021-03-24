package org.maikuro.lang;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MaikuroFileType  extends LanguageFileType {
    public static final MaikuroFileType INSTANCE = new MaikuroFileType();

    private MaikuroFileType() {
        super(MaikuroLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Maikuro";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Maikuro source file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "mi";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return MaikuroIcons.FILE;
    }
}
