package org.maikuro.lang;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor;
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory;
import org.antlr.intellij.adaptor.lexer.RuleIElementType;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.antlr.intellij.adaptor.parser.ANTLRParserAdaptor;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.jetbrains.annotations.NotNull;
import org.antlr.jetbrains.maikuro.parser.MaikuroLexer;
import org.antlr.jetbrains.maikuro.parser.MaikuroParser;
import org.maikuro.lang.psi.MaikuroPSIFileRoot;

import java.util.List;

public class MaikuroParserDefinition implements ParserDefinition {
    public static final IFileElementType FILE =
            new IFileElementType(MaikuroLanguage.INSTANCE);

    public static TokenIElementType ID;

    static {
        PSIElementTypeFactory.defineLanguageIElementTypes(MaikuroLanguage.INSTANCE,
                MaikuroParser.tokenNames,
                MaikuroParser.ruleNames);
        List<TokenIElementType> tokenIElementTypes =
                PSIElementTypeFactory.getTokenIElementTypes(MaikuroLanguage.INSTANCE);
        ID = tokenIElementTypes.get(MaikuroParser.IDENTIFIER);
    }

    public static final TokenSet COMMENTS =
            PSIElementTypeFactory.createTokenSet(
                    MaikuroLanguage.INSTANCE,
                    MaikuroLexer.COMMENT,
                    MaikuroLexer.LINE_COMMENT);

    public static final TokenSet WHITESPACE =
            PSIElementTypeFactory.createTokenSet(
                    MaikuroLanguage.INSTANCE,
                    MaikuroLexer.WS);

    public static final TokenSet STRING =
            PSIElementTypeFactory.createTokenSet(
                    MaikuroLanguage.INSTANCE,
                    MaikuroLexer.STRING_LITERAL);

    @Override
    public @NotNull Lexer createLexer(Project project) {
         MaikuroLexer lexer = new MaikuroLexer(null);

        return new ANTLRLexerAdaptor(MaikuroLanguage.INSTANCE, lexer);
    }

    @Override
    public @NotNull PsiParser createParser(Project project) {
        final MaikuroParser parser = new MaikuroParser(null);

        return new ANTLRParserAdaptor(MaikuroLanguage.INSTANCE, parser) {
            @Override
            protected ParseTree parse(Parser parser, IElementType root) {
                if (root instanceof IFileElementType) {
                    return ((MaikuroParser) parser).sourceFile();
                }

                return null;
            }
        };
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @Override
    public @NotNull TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @Override
    public @NotNull TokenSet getStringLiteralElements() {
        return STRING;
    }

    @Override
    public @NotNull PsiElement createElement(ASTNode node) {
        IElementType elType = node.getElementType();
        if ( elType instanceof TokenIElementType) {
            return new ANTLRPsiNode(node);
        }
        if ( !(elType instanceof RuleIElementType) ) {
            return new ANTLRPsiNode(node);
        }

        return new ANTLRPsiNode(node);
    }

    @Override
    public PsiFile createFile(FileViewProvider viewProvider) {
        return new MaikuroPSIFileRoot(viewProvider);
    }
}
