package org.maikuro.lang;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor;
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.jetbrains.annotations.NotNull;
import org.antlr.jetbrains.maikuro.parser.MaikuroLexer;
import org.antlr.jetbrains.maikuro.parser.MaikuroParser;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class MaikuroSyntaxHighlighter extends SyntaxHighlighterBase {
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];
    public static final TextAttributesKey ID =
            createTextAttributesKey("SAMPLE_ID", DefaultLanguageHighlighterColors.IDENTIFIER);
    public static final TextAttributesKey KEYWORD =
            createTextAttributesKey("SAMPLE_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey STRING =
            createTextAttributesKey("SAMPLE_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey LINE_COMMENT =
            createTextAttributesKey("SAMPLE_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BLOCK_COMMENT =
            createTextAttributesKey("SAMPLE_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);

    static {
        PSIElementTypeFactory.defineLanguageIElementTypes(MaikuroLanguage.INSTANCE,
                MaikuroParser.tokenNames,
                MaikuroParser.ruleNames);
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        MaikuroLexer lexer = new MaikuroLexer(null);
        return new ANTLRLexerAdaptor(MaikuroLanguage.INSTANCE, lexer);
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if ( !(tokenType instanceof TokenIElementType) ) return EMPTY_KEYS;
        TokenIElementType myType = (TokenIElementType)tokenType;
        int ttype = myType.getANTLRTokenType();
        TextAttributesKey attrKey;
        switch ( ttype ) {
            case MaikuroLexer.IDENTIFIER:
            case MaikuroLexer.TYPE_IDENTIFIER:
                attrKey = ID;
                break;

            case MaikuroLexer.PACKAGE:
            case MaikuroLexer.IMPORT:
            case MaikuroLexer.AS:
            case MaikuroLexer.CLASS:
            case MaikuroLexer.INTERFACE:
            case MaikuroLexer.EXTENDS:
            case MaikuroLexer.IMPLEMENTS:
            case MaikuroLexer.FUN:
            case MaikuroLexer.VAR:
            case MaikuroLexer.CONST:
            case MaikuroLexer.IF:
            case MaikuroLexer.UNLESS:
            case MaikuroLexer.ELSE:
            case MaikuroLexer.ELSEIF:
            case MaikuroLexer.ENUM:
            case MaikuroLexer.NEW:
            case MaikuroLexer.FOR:
            case MaikuroLexer.FOREACH:
            case MaikuroLexer.LOOP:
            case MaikuroLexer.UNTIL:
            case MaikuroLexer.WHILE:
            case MaikuroLexer.SWITCH:
            case MaikuroLexer.MATCH:
            case MaikuroLexer.CASE:
            case MaikuroLexer.DEFAULT:
            case MaikuroLexer.DO:
            case MaikuroLexer.PUBLIC:
            case MaikuroLexer.PROTECTED:
            case MaikuroLexer.PRIVATE:
            case MaikuroLexer.ABSTRACT:
            case MaikuroLexer.STATIC:
            case MaikuroLexer.FINAL:
            case MaikuroLexer.ALIAS:
            case MaikuroLexer.THIS:
            case MaikuroLexer.SUPER:
            case MaikuroLexer.RETURN:
            case MaikuroLexer.THROW:
            case MaikuroLexer.BREAK:
            case MaikuroLexer.CONTINUE:
            case MaikuroLexer.TRY:
            case MaikuroLexer.CATCH:
            case MaikuroLexer.FINALLY:
            case MaikuroLexer.NUMBER_LITERAL:
            case MaikuroLexer.BOOL_LITERAL :
                attrKey = KEYWORD;
                break;
            case MaikuroLexer.STRING_LITERAL :
                attrKey = STRING;
                break;
            case MaikuroLexer.COMMENT :
                attrKey = LINE_COMMENT;
                break;
            case MaikuroLexer.LINE_COMMENT :
                attrKey = BLOCK_COMMENT;
                break;
            default :
                return EMPTY_KEYS;
        }
        return new TextAttributesKey[] {attrKey};
    }
}
