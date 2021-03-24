package org.maikuro.lang.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.util.IconLoader;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import org.antlr.intellij.adaptor.psi.ScopeNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.maikuro.lang.MaikuroFileType;
import org.maikuro.lang.MaikuroLanguage;

import javax.swing.*;

public class MaikuroPSIFileRoot extends PsiFileBase implements ScopeNode {
    public MaikuroPSIFileRoot(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, MaikuroLanguage.INSTANCE);
    }

    @Override
    public @NotNull FileType getFileType() {
        return MaikuroFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Maikuro Language file";
    }

    @Override
    public Icon getIcon(int flags) {
        return IconLoader.getIcon("/icons/maikuro-icon.svg");
    }

    @Override
    public ScopeNode getContext() {
        return null;
    }

    @Nullable
    @Override
    public PsiElement resolve(PsiNamedElement element) {
        return null;
    }
}
