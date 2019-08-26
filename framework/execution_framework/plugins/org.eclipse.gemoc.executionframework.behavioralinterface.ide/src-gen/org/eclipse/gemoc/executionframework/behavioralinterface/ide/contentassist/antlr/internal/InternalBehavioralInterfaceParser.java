package org.eclipse.gemoc.executionframework.behavioralinterface.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import org.eclipse.gemoc.executionframework.behavioralinterface.services.BehavioralInterfaceGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
@SuppressWarnings("all")
public class InternalBehavioralInterfaceParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'both'", "'accepted'", "'exposed'", "'BehavioralInterface'", "'event'", "':'", "'parameters'", "'='", "'['", "']'", "','", "'=>'", "'('", "')'", "'<'", "'>'", "'.'", "'?'", "'extends'", "'&'", "'super'", "'*'", "'import'", "';'", "'static'", "'extension'"
    };
    public static final int RULE_STRING=5;
    public static final int RULE_SL_COMMENT=7;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__33=33;
    public static final int T__12=12;
    public static final int T__34=34;
    public static final int T__13=13;
    public static final int T__35=35;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__10=10;
    public static final int T__32=32;
    public static final int RULE_ID=4;
    public static final int RULE_WS=8;
    public static final int RULE_ANY_OTHER=9;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=6;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalBehavioralInterfaceParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalBehavioralInterfaceParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalBehavioralInterfaceParser.tokenNames; }
    public String getGrammarFileName() { return "InternalBehavioralInterface.g"; }


    	private BehavioralInterfaceGrammarAccess grammarAccess;

    	public void setGrammarAccess(BehavioralInterfaceGrammarAccess grammarAccess) {
    		this.grammarAccess = grammarAccess;
    	}

    	@Override
    	protected Grammar getGrammar() {
    		return grammarAccess.getGrammar();
    	}

    	@Override
    	protected String getValueForTokenName(String tokenName) {
    		return tokenName;
    	}



    // $ANTLR start "entryRuleBehavioralInterface"
    // InternalBehavioralInterface.g:54:1: entryRuleBehavioralInterface : ruleBehavioralInterface EOF ;
    public final void entryRuleBehavioralInterface() throws RecognitionException {
        try {
            // InternalBehavioralInterface.g:55:1: ( ruleBehavioralInterface EOF )
            // InternalBehavioralInterface.g:56:1: ruleBehavioralInterface EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBehavioralInterfaceRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleBehavioralInterface();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getBehavioralInterfaceRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleBehavioralInterface"


    // $ANTLR start "ruleBehavioralInterface"
    // InternalBehavioralInterface.g:63:1: ruleBehavioralInterface : ( ( rule__BehavioralInterface__Group__0 ) ) ;
    public final void ruleBehavioralInterface() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:67:2: ( ( ( rule__BehavioralInterface__Group__0 ) ) )
            // InternalBehavioralInterface.g:68:2: ( ( rule__BehavioralInterface__Group__0 ) )
            {
            // InternalBehavioralInterface.g:68:2: ( ( rule__BehavioralInterface__Group__0 ) )
            // InternalBehavioralInterface.g:69:3: ( rule__BehavioralInterface__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBehavioralInterfaceAccess().getGroup()); 
            }
            // InternalBehavioralInterface.g:70:3: ( rule__BehavioralInterface__Group__0 )
            // InternalBehavioralInterface.g:70:4: rule__BehavioralInterface__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__BehavioralInterface__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getBehavioralInterfaceAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleBehavioralInterface"


    // $ANTLR start "entryRuleEvent"
    // InternalBehavioralInterface.g:79:1: entryRuleEvent : ruleEvent EOF ;
    public final void entryRuleEvent() throws RecognitionException {
        try {
            // InternalBehavioralInterface.g:80:1: ( ruleEvent EOF )
            // InternalBehavioralInterface.g:81:1: ruleEvent EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleEvent();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleEvent"


    // $ANTLR start "ruleEvent"
    // InternalBehavioralInterface.g:88:1: ruleEvent : ( ( rule__Event__Group__0 ) ) ;
    public final void ruleEvent() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:92:2: ( ( ( rule__Event__Group__0 ) ) )
            // InternalBehavioralInterface.g:93:2: ( ( rule__Event__Group__0 ) )
            {
            // InternalBehavioralInterface.g:93:2: ( ( rule__Event__Group__0 ) )
            // InternalBehavioralInterface.g:94:3: ( rule__Event__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventAccess().getGroup()); 
            }
            // InternalBehavioralInterface.g:95:3: ( rule__Event__Group__0 )
            // InternalBehavioralInterface.g:95:4: rule__Event__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Event__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleEvent"


    // $ANTLR start "entryRuleEventParameter"
    // InternalBehavioralInterface.g:104:1: entryRuleEventParameter : ruleEventParameter EOF ;
    public final void entryRuleEventParameter() throws RecognitionException {
        try {
            // InternalBehavioralInterface.g:105:1: ( ruleEventParameter EOF )
            // InternalBehavioralInterface.g:106:1: ruleEventParameter EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventParameterRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleEventParameter();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventParameterRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleEventParameter"


    // $ANTLR start "ruleEventParameter"
    // InternalBehavioralInterface.g:113:1: ruleEventParameter : ( ( rule__EventParameter__Group__0 ) ) ;
    public final void ruleEventParameter() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:117:2: ( ( ( rule__EventParameter__Group__0 ) ) )
            // InternalBehavioralInterface.g:118:2: ( ( rule__EventParameter__Group__0 ) )
            {
            // InternalBehavioralInterface.g:118:2: ( ( rule__EventParameter__Group__0 ) )
            // InternalBehavioralInterface.g:119:3: ( rule__EventParameter__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventParameterAccess().getGroup()); 
            }
            // InternalBehavioralInterface.g:120:3: ( rule__EventParameter__Group__0 )
            // InternalBehavioralInterface.g:120:4: rule__EventParameter__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__EventParameter__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventParameterAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleEventParameter"


    // $ANTLR start "entryRuleJvmTypeReference"
    // InternalBehavioralInterface.g:129:1: entryRuleJvmTypeReference : ruleJvmTypeReference EOF ;
    public final void entryRuleJvmTypeReference() throws RecognitionException {
        try {
            // InternalBehavioralInterface.g:130:1: ( ruleJvmTypeReference EOF )
            // InternalBehavioralInterface.g:131:1: ruleJvmTypeReference EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmTypeReferenceRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleJvmTypeReference();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmTypeReferenceRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleJvmTypeReference"


    // $ANTLR start "ruleJvmTypeReference"
    // InternalBehavioralInterface.g:138:1: ruleJvmTypeReference : ( ( rule__JvmTypeReference__Alternatives ) ) ;
    public final void ruleJvmTypeReference() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:142:2: ( ( ( rule__JvmTypeReference__Alternatives ) ) )
            // InternalBehavioralInterface.g:143:2: ( ( rule__JvmTypeReference__Alternatives ) )
            {
            // InternalBehavioralInterface.g:143:2: ( ( rule__JvmTypeReference__Alternatives ) )
            // InternalBehavioralInterface.g:144:3: ( rule__JvmTypeReference__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmTypeReferenceAccess().getAlternatives()); 
            }
            // InternalBehavioralInterface.g:145:3: ( rule__JvmTypeReference__Alternatives )
            // InternalBehavioralInterface.g:145:4: rule__JvmTypeReference__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__JvmTypeReference__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmTypeReferenceAccess().getAlternatives()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleJvmTypeReference"


    // $ANTLR start "entryRuleArrayBrackets"
    // InternalBehavioralInterface.g:154:1: entryRuleArrayBrackets : ruleArrayBrackets EOF ;
    public final void entryRuleArrayBrackets() throws RecognitionException {
        try {
            // InternalBehavioralInterface.g:155:1: ( ruleArrayBrackets EOF )
            // InternalBehavioralInterface.g:156:1: ruleArrayBrackets EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getArrayBracketsRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleArrayBrackets();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getArrayBracketsRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleArrayBrackets"


    // $ANTLR start "ruleArrayBrackets"
    // InternalBehavioralInterface.g:163:1: ruleArrayBrackets : ( ( rule__ArrayBrackets__Group__0 ) ) ;
    public final void ruleArrayBrackets() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:167:2: ( ( ( rule__ArrayBrackets__Group__0 ) ) )
            // InternalBehavioralInterface.g:168:2: ( ( rule__ArrayBrackets__Group__0 ) )
            {
            // InternalBehavioralInterface.g:168:2: ( ( rule__ArrayBrackets__Group__0 ) )
            // InternalBehavioralInterface.g:169:3: ( rule__ArrayBrackets__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getArrayBracketsAccess().getGroup()); 
            }
            // InternalBehavioralInterface.g:170:3: ( rule__ArrayBrackets__Group__0 )
            // InternalBehavioralInterface.g:170:4: rule__ArrayBrackets__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ArrayBrackets__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getArrayBracketsAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleArrayBrackets"


    // $ANTLR start "entryRuleXFunctionTypeRef"
    // InternalBehavioralInterface.g:179:1: entryRuleXFunctionTypeRef : ruleXFunctionTypeRef EOF ;
    public final void entryRuleXFunctionTypeRef() throws RecognitionException {
        try {
            // InternalBehavioralInterface.g:180:1: ( ruleXFunctionTypeRef EOF )
            // InternalBehavioralInterface.g:181:1: ruleXFunctionTypeRef EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXFunctionTypeRefRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleXFunctionTypeRef();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getXFunctionTypeRefRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleXFunctionTypeRef"


    // $ANTLR start "ruleXFunctionTypeRef"
    // InternalBehavioralInterface.g:188:1: ruleXFunctionTypeRef : ( ( rule__XFunctionTypeRef__Group__0 ) ) ;
    public final void ruleXFunctionTypeRef() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:192:2: ( ( ( rule__XFunctionTypeRef__Group__0 ) ) )
            // InternalBehavioralInterface.g:193:2: ( ( rule__XFunctionTypeRef__Group__0 ) )
            {
            // InternalBehavioralInterface.g:193:2: ( ( rule__XFunctionTypeRef__Group__0 ) )
            // InternalBehavioralInterface.g:194:3: ( rule__XFunctionTypeRef__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXFunctionTypeRefAccess().getGroup()); 
            }
            // InternalBehavioralInterface.g:195:3: ( rule__XFunctionTypeRef__Group__0 )
            // InternalBehavioralInterface.g:195:4: rule__XFunctionTypeRef__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__XFunctionTypeRef__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getXFunctionTypeRefAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleXFunctionTypeRef"


    // $ANTLR start "entryRuleJvmParameterizedTypeReference"
    // InternalBehavioralInterface.g:204:1: entryRuleJvmParameterizedTypeReference : ruleJvmParameterizedTypeReference EOF ;
    public final void entryRuleJvmParameterizedTypeReference() throws RecognitionException {
        try {
            // InternalBehavioralInterface.g:205:1: ( ruleJvmParameterizedTypeReference EOF )
            // InternalBehavioralInterface.g:206:1: ruleJvmParameterizedTypeReference EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleJvmParameterizedTypeReference();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleJvmParameterizedTypeReference"


    // $ANTLR start "ruleJvmParameterizedTypeReference"
    // InternalBehavioralInterface.g:213:1: ruleJvmParameterizedTypeReference : ( ( rule__JvmParameterizedTypeReference__Group__0 ) ) ;
    public final void ruleJvmParameterizedTypeReference() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:217:2: ( ( ( rule__JvmParameterizedTypeReference__Group__0 ) ) )
            // InternalBehavioralInterface.g:218:2: ( ( rule__JvmParameterizedTypeReference__Group__0 ) )
            {
            // InternalBehavioralInterface.g:218:2: ( ( rule__JvmParameterizedTypeReference__Group__0 ) )
            // InternalBehavioralInterface.g:219:3: ( rule__JvmParameterizedTypeReference__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getGroup()); 
            }
            // InternalBehavioralInterface.g:220:3: ( rule__JvmParameterizedTypeReference__Group__0 )
            // InternalBehavioralInterface.g:220:4: rule__JvmParameterizedTypeReference__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleJvmParameterizedTypeReference"


    // $ANTLR start "entryRuleJvmArgumentTypeReference"
    // InternalBehavioralInterface.g:229:1: entryRuleJvmArgumentTypeReference : ruleJvmArgumentTypeReference EOF ;
    public final void entryRuleJvmArgumentTypeReference() throws RecognitionException {
        try {
            // InternalBehavioralInterface.g:230:1: ( ruleJvmArgumentTypeReference EOF )
            // InternalBehavioralInterface.g:231:1: ruleJvmArgumentTypeReference EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmArgumentTypeReferenceRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleJvmArgumentTypeReference();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmArgumentTypeReferenceRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleJvmArgumentTypeReference"


    // $ANTLR start "ruleJvmArgumentTypeReference"
    // InternalBehavioralInterface.g:238:1: ruleJvmArgumentTypeReference : ( ( rule__JvmArgumentTypeReference__Alternatives ) ) ;
    public final void ruleJvmArgumentTypeReference() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:242:2: ( ( ( rule__JvmArgumentTypeReference__Alternatives ) ) )
            // InternalBehavioralInterface.g:243:2: ( ( rule__JvmArgumentTypeReference__Alternatives ) )
            {
            // InternalBehavioralInterface.g:243:2: ( ( rule__JvmArgumentTypeReference__Alternatives ) )
            // InternalBehavioralInterface.g:244:3: ( rule__JvmArgumentTypeReference__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmArgumentTypeReferenceAccess().getAlternatives()); 
            }
            // InternalBehavioralInterface.g:245:3: ( rule__JvmArgumentTypeReference__Alternatives )
            // InternalBehavioralInterface.g:245:4: rule__JvmArgumentTypeReference__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__JvmArgumentTypeReference__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmArgumentTypeReferenceAccess().getAlternatives()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleJvmArgumentTypeReference"


    // $ANTLR start "entryRuleJvmWildcardTypeReference"
    // InternalBehavioralInterface.g:254:1: entryRuleJvmWildcardTypeReference : ruleJvmWildcardTypeReference EOF ;
    public final void entryRuleJvmWildcardTypeReference() throws RecognitionException {
        try {
            // InternalBehavioralInterface.g:255:1: ( ruleJvmWildcardTypeReference EOF )
            // InternalBehavioralInterface.g:256:1: ruleJvmWildcardTypeReference EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmWildcardTypeReferenceRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleJvmWildcardTypeReference();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmWildcardTypeReferenceRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleJvmWildcardTypeReference"


    // $ANTLR start "ruleJvmWildcardTypeReference"
    // InternalBehavioralInterface.g:263:1: ruleJvmWildcardTypeReference : ( ( rule__JvmWildcardTypeReference__Group__0 ) ) ;
    public final void ruleJvmWildcardTypeReference() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:267:2: ( ( ( rule__JvmWildcardTypeReference__Group__0 ) ) )
            // InternalBehavioralInterface.g:268:2: ( ( rule__JvmWildcardTypeReference__Group__0 ) )
            {
            // InternalBehavioralInterface.g:268:2: ( ( rule__JvmWildcardTypeReference__Group__0 ) )
            // InternalBehavioralInterface.g:269:3: ( rule__JvmWildcardTypeReference__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmWildcardTypeReferenceAccess().getGroup()); 
            }
            // InternalBehavioralInterface.g:270:3: ( rule__JvmWildcardTypeReference__Group__0 )
            // InternalBehavioralInterface.g:270:4: rule__JvmWildcardTypeReference__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__JvmWildcardTypeReference__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmWildcardTypeReferenceAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleJvmWildcardTypeReference"


    // $ANTLR start "entryRuleJvmUpperBound"
    // InternalBehavioralInterface.g:279:1: entryRuleJvmUpperBound : ruleJvmUpperBound EOF ;
    public final void entryRuleJvmUpperBound() throws RecognitionException {
        try {
            // InternalBehavioralInterface.g:280:1: ( ruleJvmUpperBound EOF )
            // InternalBehavioralInterface.g:281:1: ruleJvmUpperBound EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmUpperBoundRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleJvmUpperBound();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmUpperBoundRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleJvmUpperBound"


    // $ANTLR start "ruleJvmUpperBound"
    // InternalBehavioralInterface.g:288:1: ruleJvmUpperBound : ( ( rule__JvmUpperBound__Group__0 ) ) ;
    public final void ruleJvmUpperBound() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:292:2: ( ( ( rule__JvmUpperBound__Group__0 ) ) )
            // InternalBehavioralInterface.g:293:2: ( ( rule__JvmUpperBound__Group__0 ) )
            {
            // InternalBehavioralInterface.g:293:2: ( ( rule__JvmUpperBound__Group__0 ) )
            // InternalBehavioralInterface.g:294:3: ( rule__JvmUpperBound__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmUpperBoundAccess().getGroup()); 
            }
            // InternalBehavioralInterface.g:295:3: ( rule__JvmUpperBound__Group__0 )
            // InternalBehavioralInterface.g:295:4: rule__JvmUpperBound__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__JvmUpperBound__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmUpperBoundAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleJvmUpperBound"


    // $ANTLR start "entryRuleJvmUpperBoundAnded"
    // InternalBehavioralInterface.g:304:1: entryRuleJvmUpperBoundAnded : ruleJvmUpperBoundAnded EOF ;
    public final void entryRuleJvmUpperBoundAnded() throws RecognitionException {
        try {
            // InternalBehavioralInterface.g:305:1: ( ruleJvmUpperBoundAnded EOF )
            // InternalBehavioralInterface.g:306:1: ruleJvmUpperBoundAnded EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmUpperBoundAndedRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleJvmUpperBoundAnded();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmUpperBoundAndedRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleJvmUpperBoundAnded"


    // $ANTLR start "ruleJvmUpperBoundAnded"
    // InternalBehavioralInterface.g:313:1: ruleJvmUpperBoundAnded : ( ( rule__JvmUpperBoundAnded__Group__0 ) ) ;
    public final void ruleJvmUpperBoundAnded() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:317:2: ( ( ( rule__JvmUpperBoundAnded__Group__0 ) ) )
            // InternalBehavioralInterface.g:318:2: ( ( rule__JvmUpperBoundAnded__Group__0 ) )
            {
            // InternalBehavioralInterface.g:318:2: ( ( rule__JvmUpperBoundAnded__Group__0 ) )
            // InternalBehavioralInterface.g:319:3: ( rule__JvmUpperBoundAnded__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmUpperBoundAndedAccess().getGroup()); 
            }
            // InternalBehavioralInterface.g:320:3: ( rule__JvmUpperBoundAnded__Group__0 )
            // InternalBehavioralInterface.g:320:4: rule__JvmUpperBoundAnded__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__JvmUpperBoundAnded__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmUpperBoundAndedAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleJvmUpperBoundAnded"


    // $ANTLR start "entryRuleJvmLowerBound"
    // InternalBehavioralInterface.g:329:1: entryRuleJvmLowerBound : ruleJvmLowerBound EOF ;
    public final void entryRuleJvmLowerBound() throws RecognitionException {
        try {
            // InternalBehavioralInterface.g:330:1: ( ruleJvmLowerBound EOF )
            // InternalBehavioralInterface.g:331:1: ruleJvmLowerBound EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmLowerBoundRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleJvmLowerBound();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmLowerBoundRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleJvmLowerBound"


    // $ANTLR start "ruleJvmLowerBound"
    // InternalBehavioralInterface.g:338:1: ruleJvmLowerBound : ( ( rule__JvmLowerBound__Group__0 ) ) ;
    public final void ruleJvmLowerBound() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:342:2: ( ( ( rule__JvmLowerBound__Group__0 ) ) )
            // InternalBehavioralInterface.g:343:2: ( ( rule__JvmLowerBound__Group__0 ) )
            {
            // InternalBehavioralInterface.g:343:2: ( ( rule__JvmLowerBound__Group__0 ) )
            // InternalBehavioralInterface.g:344:3: ( rule__JvmLowerBound__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmLowerBoundAccess().getGroup()); 
            }
            // InternalBehavioralInterface.g:345:3: ( rule__JvmLowerBound__Group__0 )
            // InternalBehavioralInterface.g:345:4: rule__JvmLowerBound__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__JvmLowerBound__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmLowerBoundAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleJvmLowerBound"


    // $ANTLR start "entryRuleJvmLowerBoundAnded"
    // InternalBehavioralInterface.g:354:1: entryRuleJvmLowerBoundAnded : ruleJvmLowerBoundAnded EOF ;
    public final void entryRuleJvmLowerBoundAnded() throws RecognitionException {
        try {
            // InternalBehavioralInterface.g:355:1: ( ruleJvmLowerBoundAnded EOF )
            // InternalBehavioralInterface.g:356:1: ruleJvmLowerBoundAnded EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmLowerBoundAndedRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleJvmLowerBoundAnded();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmLowerBoundAndedRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleJvmLowerBoundAnded"


    // $ANTLR start "ruleJvmLowerBoundAnded"
    // InternalBehavioralInterface.g:363:1: ruleJvmLowerBoundAnded : ( ( rule__JvmLowerBoundAnded__Group__0 ) ) ;
    public final void ruleJvmLowerBoundAnded() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:367:2: ( ( ( rule__JvmLowerBoundAnded__Group__0 ) ) )
            // InternalBehavioralInterface.g:368:2: ( ( rule__JvmLowerBoundAnded__Group__0 ) )
            {
            // InternalBehavioralInterface.g:368:2: ( ( rule__JvmLowerBoundAnded__Group__0 ) )
            // InternalBehavioralInterface.g:369:3: ( rule__JvmLowerBoundAnded__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmLowerBoundAndedAccess().getGroup()); 
            }
            // InternalBehavioralInterface.g:370:3: ( rule__JvmLowerBoundAnded__Group__0 )
            // InternalBehavioralInterface.g:370:4: rule__JvmLowerBoundAnded__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__JvmLowerBoundAnded__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmLowerBoundAndedAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleJvmLowerBoundAnded"


    // $ANTLR start "entryRuleQualifiedName"
    // InternalBehavioralInterface.g:379:1: entryRuleQualifiedName : ruleQualifiedName EOF ;
    public final void entryRuleQualifiedName() throws RecognitionException {
        try {
            // InternalBehavioralInterface.g:380:1: ( ruleQualifiedName EOF )
            // InternalBehavioralInterface.g:381:1: ruleQualifiedName EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getQualifiedNameRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getQualifiedNameRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleQualifiedName"


    // $ANTLR start "ruleQualifiedName"
    // InternalBehavioralInterface.g:388:1: ruleQualifiedName : ( ( rule__QualifiedName__Group__0 ) ) ;
    public final void ruleQualifiedName() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:392:2: ( ( ( rule__QualifiedName__Group__0 ) ) )
            // InternalBehavioralInterface.g:393:2: ( ( rule__QualifiedName__Group__0 ) )
            {
            // InternalBehavioralInterface.g:393:2: ( ( rule__QualifiedName__Group__0 ) )
            // InternalBehavioralInterface.g:394:3: ( rule__QualifiedName__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getQualifiedNameAccess().getGroup()); 
            }
            // InternalBehavioralInterface.g:395:3: ( rule__QualifiedName__Group__0 )
            // InternalBehavioralInterface.g:395:4: rule__QualifiedName__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__QualifiedName__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getQualifiedNameAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleQualifiedName"


    // $ANTLR start "entryRuleQualifiedNameWithWildcard"
    // InternalBehavioralInterface.g:404:1: entryRuleQualifiedNameWithWildcard : ruleQualifiedNameWithWildcard EOF ;
    public final void entryRuleQualifiedNameWithWildcard() throws RecognitionException {
        try {
            // InternalBehavioralInterface.g:405:1: ( ruleQualifiedNameWithWildcard EOF )
            // InternalBehavioralInterface.g:406:1: ruleQualifiedNameWithWildcard EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getQualifiedNameWithWildcardRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleQualifiedNameWithWildcard();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getQualifiedNameWithWildcardRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleQualifiedNameWithWildcard"


    // $ANTLR start "ruleQualifiedNameWithWildcard"
    // InternalBehavioralInterface.g:413:1: ruleQualifiedNameWithWildcard : ( ( rule__QualifiedNameWithWildcard__Group__0 ) ) ;
    public final void ruleQualifiedNameWithWildcard() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:417:2: ( ( ( rule__QualifiedNameWithWildcard__Group__0 ) ) )
            // InternalBehavioralInterface.g:418:2: ( ( rule__QualifiedNameWithWildcard__Group__0 ) )
            {
            // InternalBehavioralInterface.g:418:2: ( ( rule__QualifiedNameWithWildcard__Group__0 ) )
            // InternalBehavioralInterface.g:419:3: ( rule__QualifiedNameWithWildcard__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getQualifiedNameWithWildcardAccess().getGroup()); 
            }
            // InternalBehavioralInterface.g:420:3: ( rule__QualifiedNameWithWildcard__Group__0 )
            // InternalBehavioralInterface.g:420:4: rule__QualifiedNameWithWildcard__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__QualifiedNameWithWildcard__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getQualifiedNameWithWildcardAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleQualifiedNameWithWildcard"


    // $ANTLR start "entryRuleValidID"
    // InternalBehavioralInterface.g:429:1: entryRuleValidID : ruleValidID EOF ;
    public final void entryRuleValidID() throws RecognitionException {
        try {
            // InternalBehavioralInterface.g:430:1: ( ruleValidID EOF )
            // InternalBehavioralInterface.g:431:1: ruleValidID EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getValidIDRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleValidID();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getValidIDRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleValidID"


    // $ANTLR start "ruleValidID"
    // InternalBehavioralInterface.g:438:1: ruleValidID : ( RULE_ID ) ;
    public final void ruleValidID() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:442:2: ( ( RULE_ID ) )
            // InternalBehavioralInterface.g:443:2: ( RULE_ID )
            {
            // InternalBehavioralInterface.g:443:2: ( RULE_ID )
            // InternalBehavioralInterface.g:444:3: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getValidIDAccess().getIDTerminalRuleCall()); 
            }
            match(input,RULE_ID,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getValidIDAccess().getIDTerminalRuleCall()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleValidID"


    // $ANTLR start "entryRuleXImportDeclaration"
    // InternalBehavioralInterface.g:454:1: entryRuleXImportDeclaration : ruleXImportDeclaration EOF ;
    public final void entryRuleXImportDeclaration() throws RecognitionException {
        try {
            // InternalBehavioralInterface.g:455:1: ( ruleXImportDeclaration EOF )
            // InternalBehavioralInterface.g:456:1: ruleXImportDeclaration EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXImportDeclarationRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleXImportDeclaration();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getXImportDeclarationRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleXImportDeclaration"


    // $ANTLR start "ruleXImportDeclaration"
    // InternalBehavioralInterface.g:463:1: ruleXImportDeclaration : ( ( rule__XImportDeclaration__Group__0 ) ) ;
    public final void ruleXImportDeclaration() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:467:2: ( ( ( rule__XImportDeclaration__Group__0 ) ) )
            // InternalBehavioralInterface.g:468:2: ( ( rule__XImportDeclaration__Group__0 ) )
            {
            // InternalBehavioralInterface.g:468:2: ( ( rule__XImportDeclaration__Group__0 ) )
            // InternalBehavioralInterface.g:469:3: ( rule__XImportDeclaration__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXImportDeclarationAccess().getGroup()); 
            }
            // InternalBehavioralInterface.g:470:3: ( rule__XImportDeclaration__Group__0 )
            // InternalBehavioralInterface.g:470:4: rule__XImportDeclaration__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__XImportDeclaration__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getXImportDeclarationAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleXImportDeclaration"


    // $ANTLR start "entryRuleQualifiedNameInStaticImport"
    // InternalBehavioralInterface.g:479:1: entryRuleQualifiedNameInStaticImport : ruleQualifiedNameInStaticImport EOF ;
    public final void entryRuleQualifiedNameInStaticImport() throws RecognitionException {
        try {
            // InternalBehavioralInterface.g:480:1: ( ruleQualifiedNameInStaticImport EOF )
            // InternalBehavioralInterface.g:481:1: ruleQualifiedNameInStaticImport EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getQualifiedNameInStaticImportRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleQualifiedNameInStaticImport();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getQualifiedNameInStaticImportRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleQualifiedNameInStaticImport"


    // $ANTLR start "ruleQualifiedNameInStaticImport"
    // InternalBehavioralInterface.g:488:1: ruleQualifiedNameInStaticImport : ( ( ( rule__QualifiedNameInStaticImport__Group__0 ) ) ( ( rule__QualifiedNameInStaticImport__Group__0 )* ) ) ;
    public final void ruleQualifiedNameInStaticImport() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:492:2: ( ( ( ( rule__QualifiedNameInStaticImport__Group__0 ) ) ( ( rule__QualifiedNameInStaticImport__Group__0 )* ) ) )
            // InternalBehavioralInterface.g:493:2: ( ( ( rule__QualifiedNameInStaticImport__Group__0 ) ) ( ( rule__QualifiedNameInStaticImport__Group__0 )* ) )
            {
            // InternalBehavioralInterface.g:493:2: ( ( ( rule__QualifiedNameInStaticImport__Group__0 ) ) ( ( rule__QualifiedNameInStaticImport__Group__0 )* ) )
            // InternalBehavioralInterface.g:494:3: ( ( rule__QualifiedNameInStaticImport__Group__0 ) ) ( ( rule__QualifiedNameInStaticImport__Group__0 )* )
            {
            // InternalBehavioralInterface.g:494:3: ( ( rule__QualifiedNameInStaticImport__Group__0 ) )
            // InternalBehavioralInterface.g:495:4: ( rule__QualifiedNameInStaticImport__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getQualifiedNameInStaticImportAccess().getGroup()); 
            }
            // InternalBehavioralInterface.g:496:4: ( rule__QualifiedNameInStaticImport__Group__0 )
            // InternalBehavioralInterface.g:496:5: rule__QualifiedNameInStaticImport__Group__0
            {
            pushFollow(FOLLOW_3);
            rule__QualifiedNameInStaticImport__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getQualifiedNameInStaticImportAccess().getGroup()); 
            }

            }

            // InternalBehavioralInterface.g:499:3: ( ( rule__QualifiedNameInStaticImport__Group__0 )* )
            // InternalBehavioralInterface.g:500:4: ( rule__QualifiedNameInStaticImport__Group__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getQualifiedNameInStaticImportAccess().getGroup()); 
            }
            // InternalBehavioralInterface.g:501:4: ( rule__QualifiedNameInStaticImport__Group__0 )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==RULE_ID) ) {
                    int LA1_2 = input.LA(2);

                    if ( (LA1_2==26) ) {
                        alt1=1;
                    }


                }


                switch (alt1) {
            	case 1 :
            	    // InternalBehavioralInterface.g:501:5: rule__QualifiedNameInStaticImport__Group__0
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__QualifiedNameInStaticImport__Group__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getQualifiedNameInStaticImportAccess().getGroup()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleQualifiedNameInStaticImport"


    // $ANTLR start "ruleEventType"
    // InternalBehavioralInterface.g:511:1: ruleEventType : ( ( rule__EventType__Alternatives ) ) ;
    public final void ruleEventType() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:515:1: ( ( ( rule__EventType__Alternatives ) ) )
            // InternalBehavioralInterface.g:516:2: ( ( rule__EventType__Alternatives ) )
            {
            // InternalBehavioralInterface.g:516:2: ( ( rule__EventType__Alternatives ) )
            // InternalBehavioralInterface.g:517:3: ( rule__EventType__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventTypeAccess().getAlternatives()); 
            }
            // InternalBehavioralInterface.g:518:3: ( rule__EventType__Alternatives )
            // InternalBehavioralInterface.g:518:4: rule__EventType__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__EventType__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventTypeAccess().getAlternatives()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleEventType"


    // $ANTLR start "rule__JvmTypeReference__Alternatives"
    // InternalBehavioralInterface.g:526:1: rule__JvmTypeReference__Alternatives : ( ( ( rule__JvmTypeReference__Group_0__0 ) ) | ( ruleXFunctionTypeRef ) );
    public final void rule__JvmTypeReference__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:530:1: ( ( ( rule__JvmTypeReference__Group_0__0 ) ) | ( ruleXFunctionTypeRef ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==RULE_ID) ) {
                alt2=1;
            }
            else if ( ((LA2_0>=21 && LA2_0<=22)) ) {
                alt2=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // InternalBehavioralInterface.g:531:2: ( ( rule__JvmTypeReference__Group_0__0 ) )
                    {
                    // InternalBehavioralInterface.g:531:2: ( ( rule__JvmTypeReference__Group_0__0 ) )
                    // InternalBehavioralInterface.g:532:3: ( rule__JvmTypeReference__Group_0__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getJvmTypeReferenceAccess().getGroup_0()); 
                    }
                    // InternalBehavioralInterface.g:533:3: ( rule__JvmTypeReference__Group_0__0 )
                    // InternalBehavioralInterface.g:533:4: rule__JvmTypeReference__Group_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__JvmTypeReference__Group_0__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getJvmTypeReferenceAccess().getGroup_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalBehavioralInterface.g:537:2: ( ruleXFunctionTypeRef )
                    {
                    // InternalBehavioralInterface.g:537:2: ( ruleXFunctionTypeRef )
                    // InternalBehavioralInterface.g:538:3: ruleXFunctionTypeRef
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getJvmTypeReferenceAccess().getXFunctionTypeRefParserRuleCall_1()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleXFunctionTypeRef();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getJvmTypeReferenceAccess().getXFunctionTypeRefParserRuleCall_1()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmTypeReference__Alternatives"


    // $ANTLR start "rule__JvmArgumentTypeReference__Alternatives"
    // InternalBehavioralInterface.g:547:1: rule__JvmArgumentTypeReference__Alternatives : ( ( ruleJvmTypeReference ) | ( ruleJvmWildcardTypeReference ) );
    public final void rule__JvmArgumentTypeReference__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:551:1: ( ( ruleJvmTypeReference ) | ( ruleJvmWildcardTypeReference ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==RULE_ID||(LA3_0>=21 && LA3_0<=22)) ) {
                alt3=1;
            }
            else if ( (LA3_0==27) ) {
                alt3=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // InternalBehavioralInterface.g:552:2: ( ruleJvmTypeReference )
                    {
                    // InternalBehavioralInterface.g:552:2: ( ruleJvmTypeReference )
                    // InternalBehavioralInterface.g:553:3: ruleJvmTypeReference
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getJvmArgumentTypeReferenceAccess().getJvmTypeReferenceParserRuleCall_0()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleJvmTypeReference();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getJvmArgumentTypeReferenceAccess().getJvmTypeReferenceParserRuleCall_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalBehavioralInterface.g:558:2: ( ruleJvmWildcardTypeReference )
                    {
                    // InternalBehavioralInterface.g:558:2: ( ruleJvmWildcardTypeReference )
                    // InternalBehavioralInterface.g:559:3: ruleJvmWildcardTypeReference
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getJvmArgumentTypeReferenceAccess().getJvmWildcardTypeReferenceParserRuleCall_1()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleJvmWildcardTypeReference();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getJvmArgumentTypeReferenceAccess().getJvmWildcardTypeReferenceParserRuleCall_1()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmArgumentTypeReference__Alternatives"


    // $ANTLR start "rule__JvmWildcardTypeReference__Alternatives_2"
    // InternalBehavioralInterface.g:568:1: rule__JvmWildcardTypeReference__Alternatives_2 : ( ( ( rule__JvmWildcardTypeReference__Group_2_0__0 ) ) | ( ( rule__JvmWildcardTypeReference__Group_2_1__0 ) ) );
    public final void rule__JvmWildcardTypeReference__Alternatives_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:572:1: ( ( ( rule__JvmWildcardTypeReference__Group_2_0__0 ) ) | ( ( rule__JvmWildcardTypeReference__Group_2_1__0 ) ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==28) ) {
                alt4=1;
            }
            else if ( (LA4_0==30) ) {
                alt4=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalBehavioralInterface.g:573:2: ( ( rule__JvmWildcardTypeReference__Group_2_0__0 ) )
                    {
                    // InternalBehavioralInterface.g:573:2: ( ( rule__JvmWildcardTypeReference__Group_2_0__0 ) )
                    // InternalBehavioralInterface.g:574:3: ( rule__JvmWildcardTypeReference__Group_2_0__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getJvmWildcardTypeReferenceAccess().getGroup_2_0()); 
                    }
                    // InternalBehavioralInterface.g:575:3: ( rule__JvmWildcardTypeReference__Group_2_0__0 )
                    // InternalBehavioralInterface.g:575:4: rule__JvmWildcardTypeReference__Group_2_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__JvmWildcardTypeReference__Group_2_0__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getJvmWildcardTypeReferenceAccess().getGroup_2_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalBehavioralInterface.g:579:2: ( ( rule__JvmWildcardTypeReference__Group_2_1__0 ) )
                    {
                    // InternalBehavioralInterface.g:579:2: ( ( rule__JvmWildcardTypeReference__Group_2_1__0 ) )
                    // InternalBehavioralInterface.g:580:3: ( rule__JvmWildcardTypeReference__Group_2_1__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getJvmWildcardTypeReferenceAccess().getGroup_2_1()); 
                    }
                    // InternalBehavioralInterface.g:581:3: ( rule__JvmWildcardTypeReference__Group_2_1__0 )
                    // InternalBehavioralInterface.g:581:4: rule__JvmWildcardTypeReference__Group_2_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__JvmWildcardTypeReference__Group_2_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getJvmWildcardTypeReferenceAccess().getGroup_2_1()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmWildcardTypeReference__Alternatives_2"


    // $ANTLR start "rule__XImportDeclaration__Alternatives_1"
    // InternalBehavioralInterface.g:589:1: rule__XImportDeclaration__Alternatives_1 : ( ( ( rule__XImportDeclaration__Group_1_0__0 ) ) | ( ( rule__XImportDeclaration__ImportedTypeAssignment_1_1 ) ) | ( ( rule__XImportDeclaration__ImportedNamespaceAssignment_1_2 ) ) );
    public final void rule__XImportDeclaration__Alternatives_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:593:1: ( ( ( rule__XImportDeclaration__Group_1_0__0 ) ) | ( ( rule__XImportDeclaration__ImportedTypeAssignment_1_1 ) ) | ( ( rule__XImportDeclaration__ImportedNamespaceAssignment_1_2 ) ) )
            int alt5=3;
            alt5 = dfa5.predict(input);
            switch (alt5) {
                case 1 :
                    // InternalBehavioralInterface.g:594:2: ( ( rule__XImportDeclaration__Group_1_0__0 ) )
                    {
                    // InternalBehavioralInterface.g:594:2: ( ( rule__XImportDeclaration__Group_1_0__0 ) )
                    // InternalBehavioralInterface.g:595:3: ( rule__XImportDeclaration__Group_1_0__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getXImportDeclarationAccess().getGroup_1_0()); 
                    }
                    // InternalBehavioralInterface.g:596:3: ( rule__XImportDeclaration__Group_1_0__0 )
                    // InternalBehavioralInterface.g:596:4: rule__XImportDeclaration__Group_1_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__XImportDeclaration__Group_1_0__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getXImportDeclarationAccess().getGroup_1_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalBehavioralInterface.g:600:2: ( ( rule__XImportDeclaration__ImportedTypeAssignment_1_1 ) )
                    {
                    // InternalBehavioralInterface.g:600:2: ( ( rule__XImportDeclaration__ImportedTypeAssignment_1_1 ) )
                    // InternalBehavioralInterface.g:601:3: ( rule__XImportDeclaration__ImportedTypeAssignment_1_1 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getXImportDeclarationAccess().getImportedTypeAssignment_1_1()); 
                    }
                    // InternalBehavioralInterface.g:602:3: ( rule__XImportDeclaration__ImportedTypeAssignment_1_1 )
                    // InternalBehavioralInterface.g:602:4: rule__XImportDeclaration__ImportedTypeAssignment_1_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__XImportDeclaration__ImportedTypeAssignment_1_1();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getXImportDeclarationAccess().getImportedTypeAssignment_1_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalBehavioralInterface.g:606:2: ( ( rule__XImportDeclaration__ImportedNamespaceAssignment_1_2 ) )
                    {
                    // InternalBehavioralInterface.g:606:2: ( ( rule__XImportDeclaration__ImportedNamespaceAssignment_1_2 ) )
                    // InternalBehavioralInterface.g:607:3: ( rule__XImportDeclaration__ImportedNamespaceAssignment_1_2 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getXImportDeclarationAccess().getImportedNamespaceAssignment_1_2()); 
                    }
                    // InternalBehavioralInterface.g:608:3: ( rule__XImportDeclaration__ImportedNamespaceAssignment_1_2 )
                    // InternalBehavioralInterface.g:608:4: rule__XImportDeclaration__ImportedNamespaceAssignment_1_2
                    {
                    pushFollow(FOLLOW_2);
                    rule__XImportDeclaration__ImportedNamespaceAssignment_1_2();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getXImportDeclarationAccess().getImportedNamespaceAssignment_1_2()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XImportDeclaration__Alternatives_1"


    // $ANTLR start "rule__XImportDeclaration__Alternatives_1_0_3"
    // InternalBehavioralInterface.g:616:1: rule__XImportDeclaration__Alternatives_1_0_3 : ( ( ( rule__XImportDeclaration__WildcardAssignment_1_0_3_0 ) ) | ( ( rule__XImportDeclaration__MemberNameAssignment_1_0_3_1 ) ) );
    public final void rule__XImportDeclaration__Alternatives_1_0_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:620:1: ( ( ( rule__XImportDeclaration__WildcardAssignment_1_0_3_0 ) ) | ( ( rule__XImportDeclaration__MemberNameAssignment_1_0_3_1 ) ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==31) ) {
                alt6=1;
            }
            else if ( (LA6_0==RULE_ID) ) {
                alt6=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // InternalBehavioralInterface.g:621:2: ( ( rule__XImportDeclaration__WildcardAssignment_1_0_3_0 ) )
                    {
                    // InternalBehavioralInterface.g:621:2: ( ( rule__XImportDeclaration__WildcardAssignment_1_0_3_0 ) )
                    // InternalBehavioralInterface.g:622:3: ( rule__XImportDeclaration__WildcardAssignment_1_0_3_0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getXImportDeclarationAccess().getWildcardAssignment_1_0_3_0()); 
                    }
                    // InternalBehavioralInterface.g:623:3: ( rule__XImportDeclaration__WildcardAssignment_1_0_3_0 )
                    // InternalBehavioralInterface.g:623:4: rule__XImportDeclaration__WildcardAssignment_1_0_3_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__XImportDeclaration__WildcardAssignment_1_0_3_0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getXImportDeclarationAccess().getWildcardAssignment_1_0_3_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalBehavioralInterface.g:627:2: ( ( rule__XImportDeclaration__MemberNameAssignment_1_0_3_1 ) )
                    {
                    // InternalBehavioralInterface.g:627:2: ( ( rule__XImportDeclaration__MemberNameAssignment_1_0_3_1 ) )
                    // InternalBehavioralInterface.g:628:3: ( rule__XImportDeclaration__MemberNameAssignment_1_0_3_1 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getXImportDeclarationAccess().getMemberNameAssignment_1_0_3_1()); 
                    }
                    // InternalBehavioralInterface.g:629:3: ( rule__XImportDeclaration__MemberNameAssignment_1_0_3_1 )
                    // InternalBehavioralInterface.g:629:4: rule__XImportDeclaration__MemberNameAssignment_1_0_3_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__XImportDeclaration__MemberNameAssignment_1_0_3_1();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getXImportDeclarationAccess().getMemberNameAssignment_1_0_3_1()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XImportDeclaration__Alternatives_1_0_3"


    // $ANTLR start "rule__EventType__Alternatives"
    // InternalBehavioralInterface.g:637:1: rule__EventType__Alternatives : ( ( ( 'both' ) ) | ( ( 'accepted' ) ) | ( ( 'exposed' ) ) );
    public final void rule__EventType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:641:1: ( ( ( 'both' ) ) | ( ( 'accepted' ) ) | ( ( 'exposed' ) ) )
            int alt7=3;
            switch ( input.LA(1) ) {
            case 10:
                {
                alt7=1;
                }
                break;
            case 11:
                {
                alt7=2;
                }
                break;
            case 12:
                {
                alt7=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // InternalBehavioralInterface.g:642:2: ( ( 'both' ) )
                    {
                    // InternalBehavioralInterface.g:642:2: ( ( 'both' ) )
                    // InternalBehavioralInterface.g:643:3: ( 'both' )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getEventTypeAccess().getBOTHEnumLiteralDeclaration_0()); 
                    }
                    // InternalBehavioralInterface.g:644:3: ( 'both' )
                    // InternalBehavioralInterface.g:644:4: 'both'
                    {
                    match(input,10,FOLLOW_2); if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getEventTypeAccess().getBOTHEnumLiteralDeclaration_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalBehavioralInterface.g:648:2: ( ( 'accepted' ) )
                    {
                    // InternalBehavioralInterface.g:648:2: ( ( 'accepted' ) )
                    // InternalBehavioralInterface.g:649:3: ( 'accepted' )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getEventTypeAccess().getACCEPTEDEnumLiteralDeclaration_1()); 
                    }
                    // InternalBehavioralInterface.g:650:3: ( 'accepted' )
                    // InternalBehavioralInterface.g:650:4: 'accepted'
                    {
                    match(input,11,FOLLOW_2); if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getEventTypeAccess().getACCEPTEDEnumLiteralDeclaration_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalBehavioralInterface.g:654:2: ( ( 'exposed' ) )
                    {
                    // InternalBehavioralInterface.g:654:2: ( ( 'exposed' ) )
                    // InternalBehavioralInterface.g:655:3: ( 'exposed' )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getEventTypeAccess().getEXPOSEDEnumLiteralDeclaration_2()); 
                    }
                    // InternalBehavioralInterface.g:656:3: ( 'exposed' )
                    // InternalBehavioralInterface.g:656:4: 'exposed'
                    {
                    match(input,12,FOLLOW_2); if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getEventTypeAccess().getEXPOSEDEnumLiteralDeclaration_2()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EventType__Alternatives"


    // $ANTLR start "rule__BehavioralInterface__Group__0"
    // InternalBehavioralInterface.g:664:1: rule__BehavioralInterface__Group__0 : rule__BehavioralInterface__Group__0__Impl rule__BehavioralInterface__Group__1 ;
    public final void rule__BehavioralInterface__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:668:1: ( rule__BehavioralInterface__Group__0__Impl rule__BehavioralInterface__Group__1 )
            // InternalBehavioralInterface.g:669:2: rule__BehavioralInterface__Group__0__Impl rule__BehavioralInterface__Group__1
            {
            pushFollow(FOLLOW_4);
            rule__BehavioralInterface__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__BehavioralInterface__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BehavioralInterface__Group__0"


    // $ANTLR start "rule__BehavioralInterface__Group__0__Impl"
    // InternalBehavioralInterface.g:676:1: rule__BehavioralInterface__Group__0__Impl : ( 'BehavioralInterface' ) ;
    public final void rule__BehavioralInterface__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:680:1: ( ( 'BehavioralInterface' ) )
            // InternalBehavioralInterface.g:681:1: ( 'BehavioralInterface' )
            {
            // InternalBehavioralInterface.g:681:1: ( 'BehavioralInterface' )
            // InternalBehavioralInterface.g:682:2: 'BehavioralInterface'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBehavioralInterfaceAccess().getBehavioralInterfaceKeyword_0()); 
            }
            match(input,13,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getBehavioralInterfaceAccess().getBehavioralInterfaceKeyword_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BehavioralInterface__Group__0__Impl"


    // $ANTLR start "rule__BehavioralInterface__Group__1"
    // InternalBehavioralInterface.g:691:1: rule__BehavioralInterface__Group__1 : rule__BehavioralInterface__Group__1__Impl rule__BehavioralInterface__Group__2 ;
    public final void rule__BehavioralInterface__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:695:1: ( rule__BehavioralInterface__Group__1__Impl rule__BehavioralInterface__Group__2 )
            // InternalBehavioralInterface.g:696:2: rule__BehavioralInterface__Group__1__Impl rule__BehavioralInterface__Group__2
            {
            pushFollow(FOLLOW_5);
            rule__BehavioralInterface__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__BehavioralInterface__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BehavioralInterface__Group__1"


    // $ANTLR start "rule__BehavioralInterface__Group__1__Impl"
    // InternalBehavioralInterface.g:703:1: rule__BehavioralInterface__Group__1__Impl : ( ( rule__BehavioralInterface__NameAssignment_1 ) ) ;
    public final void rule__BehavioralInterface__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:707:1: ( ( ( rule__BehavioralInterface__NameAssignment_1 ) ) )
            // InternalBehavioralInterface.g:708:1: ( ( rule__BehavioralInterface__NameAssignment_1 ) )
            {
            // InternalBehavioralInterface.g:708:1: ( ( rule__BehavioralInterface__NameAssignment_1 ) )
            // InternalBehavioralInterface.g:709:2: ( rule__BehavioralInterface__NameAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBehavioralInterfaceAccess().getNameAssignment_1()); 
            }
            // InternalBehavioralInterface.g:710:2: ( rule__BehavioralInterface__NameAssignment_1 )
            // InternalBehavioralInterface.g:710:3: rule__BehavioralInterface__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__BehavioralInterface__NameAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getBehavioralInterfaceAccess().getNameAssignment_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BehavioralInterface__Group__1__Impl"


    // $ANTLR start "rule__BehavioralInterface__Group__2"
    // InternalBehavioralInterface.g:718:1: rule__BehavioralInterface__Group__2 : rule__BehavioralInterface__Group__2__Impl ;
    public final void rule__BehavioralInterface__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:722:1: ( rule__BehavioralInterface__Group__2__Impl )
            // InternalBehavioralInterface.g:723:2: rule__BehavioralInterface__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__BehavioralInterface__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BehavioralInterface__Group__2"


    // $ANTLR start "rule__BehavioralInterface__Group__2__Impl"
    // InternalBehavioralInterface.g:729:1: rule__BehavioralInterface__Group__2__Impl : ( ( rule__BehavioralInterface__EventsAssignment_2 )* ) ;
    public final void rule__BehavioralInterface__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:733:1: ( ( ( rule__BehavioralInterface__EventsAssignment_2 )* ) )
            // InternalBehavioralInterface.g:734:1: ( ( rule__BehavioralInterface__EventsAssignment_2 )* )
            {
            // InternalBehavioralInterface.g:734:1: ( ( rule__BehavioralInterface__EventsAssignment_2 )* )
            // InternalBehavioralInterface.g:735:2: ( rule__BehavioralInterface__EventsAssignment_2 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBehavioralInterfaceAccess().getEventsAssignment_2()); 
            }
            // InternalBehavioralInterface.g:736:2: ( rule__BehavioralInterface__EventsAssignment_2 )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>=10 && LA8_0<=12)||LA8_0==14) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalBehavioralInterface.g:736:3: rule__BehavioralInterface__EventsAssignment_2
            	    {
            	    pushFollow(FOLLOW_6);
            	    rule__BehavioralInterface__EventsAssignment_2();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getBehavioralInterfaceAccess().getEventsAssignment_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BehavioralInterface__Group__2__Impl"


    // $ANTLR start "rule__Event__Group__0"
    // InternalBehavioralInterface.g:745:1: rule__Event__Group__0 : rule__Event__Group__0__Impl rule__Event__Group__1 ;
    public final void rule__Event__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:749:1: ( rule__Event__Group__0__Impl rule__Event__Group__1 )
            // InternalBehavioralInterface.g:750:2: rule__Event__Group__0__Impl rule__Event__Group__1
            {
            pushFollow(FOLLOW_5);
            rule__Event__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Event__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__Group__0"


    // $ANTLR start "rule__Event__Group__0__Impl"
    // InternalBehavioralInterface.g:757:1: rule__Event__Group__0__Impl : ( ( rule__Event__TypeAssignment_0 )? ) ;
    public final void rule__Event__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:761:1: ( ( ( rule__Event__TypeAssignment_0 )? ) )
            // InternalBehavioralInterface.g:762:1: ( ( rule__Event__TypeAssignment_0 )? )
            {
            // InternalBehavioralInterface.g:762:1: ( ( rule__Event__TypeAssignment_0 )? )
            // InternalBehavioralInterface.g:763:2: ( rule__Event__TypeAssignment_0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventAccess().getTypeAssignment_0()); 
            }
            // InternalBehavioralInterface.g:764:2: ( rule__Event__TypeAssignment_0 )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( ((LA9_0>=10 && LA9_0<=12)) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalBehavioralInterface.g:764:3: rule__Event__TypeAssignment_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Event__TypeAssignment_0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventAccess().getTypeAssignment_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__Group__0__Impl"


    // $ANTLR start "rule__Event__Group__1"
    // InternalBehavioralInterface.g:772:1: rule__Event__Group__1 : rule__Event__Group__1__Impl rule__Event__Group__2 ;
    public final void rule__Event__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:776:1: ( rule__Event__Group__1__Impl rule__Event__Group__2 )
            // InternalBehavioralInterface.g:777:2: rule__Event__Group__1__Impl rule__Event__Group__2
            {
            pushFollow(FOLLOW_4);
            rule__Event__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Event__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__Group__1"


    // $ANTLR start "rule__Event__Group__1__Impl"
    // InternalBehavioralInterface.g:784:1: rule__Event__Group__1__Impl : ( 'event' ) ;
    public final void rule__Event__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:788:1: ( ( 'event' ) )
            // InternalBehavioralInterface.g:789:1: ( 'event' )
            {
            // InternalBehavioralInterface.g:789:1: ( 'event' )
            // InternalBehavioralInterface.g:790:2: 'event'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventAccess().getEventKeyword_1()); 
            }
            match(input,14,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventAccess().getEventKeyword_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__Group__1__Impl"


    // $ANTLR start "rule__Event__Group__2"
    // InternalBehavioralInterface.g:799:1: rule__Event__Group__2 : rule__Event__Group__2__Impl rule__Event__Group__3 ;
    public final void rule__Event__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:803:1: ( rule__Event__Group__2__Impl rule__Event__Group__3 )
            // InternalBehavioralInterface.g:804:2: rule__Event__Group__2__Impl rule__Event__Group__3
            {
            pushFollow(FOLLOW_7);
            rule__Event__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Event__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__Group__2"


    // $ANTLR start "rule__Event__Group__2__Impl"
    // InternalBehavioralInterface.g:811:1: rule__Event__Group__2__Impl : ( ( rule__Event__NameAssignment_2 ) ) ;
    public final void rule__Event__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:815:1: ( ( ( rule__Event__NameAssignment_2 ) ) )
            // InternalBehavioralInterface.g:816:1: ( ( rule__Event__NameAssignment_2 ) )
            {
            // InternalBehavioralInterface.g:816:1: ( ( rule__Event__NameAssignment_2 ) )
            // InternalBehavioralInterface.g:817:2: ( rule__Event__NameAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventAccess().getNameAssignment_2()); 
            }
            // InternalBehavioralInterface.g:818:2: ( rule__Event__NameAssignment_2 )
            // InternalBehavioralInterface.g:818:3: rule__Event__NameAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Event__NameAssignment_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventAccess().getNameAssignment_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__Group__2__Impl"


    // $ANTLR start "rule__Event__Group__3"
    // InternalBehavioralInterface.g:826:1: rule__Event__Group__3 : rule__Event__Group__3__Impl rule__Event__Group__4 ;
    public final void rule__Event__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:830:1: ( rule__Event__Group__3__Impl rule__Event__Group__4 )
            // InternalBehavioralInterface.g:831:2: rule__Event__Group__3__Impl rule__Event__Group__4
            {
            pushFollow(FOLLOW_8);
            rule__Event__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Event__Group__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__Group__3"


    // $ANTLR start "rule__Event__Group__3__Impl"
    // InternalBehavioralInterface.g:838:1: rule__Event__Group__3__Impl : ( ':' ) ;
    public final void rule__Event__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:842:1: ( ( ':' ) )
            // InternalBehavioralInterface.g:843:1: ( ':' )
            {
            // InternalBehavioralInterface.g:843:1: ( ':' )
            // InternalBehavioralInterface.g:844:2: ':'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventAccess().getColonKeyword_3()); 
            }
            match(input,15,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventAccess().getColonKeyword_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__Group__3__Impl"


    // $ANTLR start "rule__Event__Group__4"
    // InternalBehavioralInterface.g:853:1: rule__Event__Group__4 : rule__Event__Group__4__Impl ;
    public final void rule__Event__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:857:1: ( rule__Event__Group__4__Impl )
            // InternalBehavioralInterface.g:858:2: rule__Event__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Event__Group__4__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__Group__4"


    // $ANTLR start "rule__Event__Group__4__Impl"
    // InternalBehavioralInterface.g:864:1: rule__Event__Group__4__Impl : ( ( rule__Event__Group_4__0 )? ) ;
    public final void rule__Event__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:868:1: ( ( ( rule__Event__Group_4__0 )? ) )
            // InternalBehavioralInterface.g:869:1: ( ( rule__Event__Group_4__0 )? )
            {
            // InternalBehavioralInterface.g:869:1: ( ( rule__Event__Group_4__0 )? )
            // InternalBehavioralInterface.g:870:2: ( rule__Event__Group_4__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventAccess().getGroup_4()); 
            }
            // InternalBehavioralInterface.g:871:2: ( rule__Event__Group_4__0 )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==16) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalBehavioralInterface.g:871:3: rule__Event__Group_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Event__Group_4__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventAccess().getGroup_4()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__Group__4__Impl"


    // $ANTLR start "rule__Event__Group_4__0"
    // InternalBehavioralInterface.g:880:1: rule__Event__Group_4__0 : rule__Event__Group_4__0__Impl rule__Event__Group_4__1 ;
    public final void rule__Event__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:884:1: ( rule__Event__Group_4__0__Impl rule__Event__Group_4__1 )
            // InternalBehavioralInterface.g:885:2: rule__Event__Group_4__0__Impl rule__Event__Group_4__1
            {
            pushFollow(FOLLOW_9);
            rule__Event__Group_4__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Event__Group_4__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__Group_4__0"


    // $ANTLR start "rule__Event__Group_4__0__Impl"
    // InternalBehavioralInterface.g:892:1: rule__Event__Group_4__0__Impl : ( 'parameters' ) ;
    public final void rule__Event__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:896:1: ( ( 'parameters' ) )
            // InternalBehavioralInterface.g:897:1: ( 'parameters' )
            {
            // InternalBehavioralInterface.g:897:1: ( 'parameters' )
            // InternalBehavioralInterface.g:898:2: 'parameters'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventAccess().getParametersKeyword_4_0()); 
            }
            match(input,16,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventAccess().getParametersKeyword_4_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__Group_4__0__Impl"


    // $ANTLR start "rule__Event__Group_4__1"
    // InternalBehavioralInterface.g:907:1: rule__Event__Group_4__1 : rule__Event__Group_4__1__Impl rule__Event__Group_4__2 ;
    public final void rule__Event__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:911:1: ( rule__Event__Group_4__1__Impl rule__Event__Group_4__2 )
            // InternalBehavioralInterface.g:912:2: rule__Event__Group_4__1__Impl rule__Event__Group_4__2
            {
            pushFollow(FOLLOW_10);
            rule__Event__Group_4__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Event__Group_4__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__Group_4__1"


    // $ANTLR start "rule__Event__Group_4__1__Impl"
    // InternalBehavioralInterface.g:919:1: rule__Event__Group_4__1__Impl : ( '=' ) ;
    public final void rule__Event__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:923:1: ( ( '=' ) )
            // InternalBehavioralInterface.g:924:1: ( '=' )
            {
            // InternalBehavioralInterface.g:924:1: ( '=' )
            // InternalBehavioralInterface.g:925:2: '='
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventAccess().getEqualsSignKeyword_4_1()); 
            }
            match(input,17,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventAccess().getEqualsSignKeyword_4_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__Group_4__1__Impl"


    // $ANTLR start "rule__Event__Group_4__2"
    // InternalBehavioralInterface.g:934:1: rule__Event__Group_4__2 : rule__Event__Group_4__2__Impl rule__Event__Group_4__3 ;
    public final void rule__Event__Group_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:938:1: ( rule__Event__Group_4__2__Impl rule__Event__Group_4__3 )
            // InternalBehavioralInterface.g:939:2: rule__Event__Group_4__2__Impl rule__Event__Group_4__3
            {
            pushFollow(FOLLOW_4);
            rule__Event__Group_4__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Event__Group_4__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__Group_4__2"


    // $ANTLR start "rule__Event__Group_4__2__Impl"
    // InternalBehavioralInterface.g:946:1: rule__Event__Group_4__2__Impl : ( '[' ) ;
    public final void rule__Event__Group_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:950:1: ( ( '[' ) )
            // InternalBehavioralInterface.g:951:1: ( '[' )
            {
            // InternalBehavioralInterface.g:951:1: ( '[' )
            // InternalBehavioralInterface.g:952:2: '['
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventAccess().getLeftSquareBracketKeyword_4_2()); 
            }
            match(input,18,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventAccess().getLeftSquareBracketKeyword_4_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__Group_4__2__Impl"


    // $ANTLR start "rule__Event__Group_4__3"
    // InternalBehavioralInterface.g:961:1: rule__Event__Group_4__3 : rule__Event__Group_4__3__Impl rule__Event__Group_4__4 ;
    public final void rule__Event__Group_4__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:965:1: ( rule__Event__Group_4__3__Impl rule__Event__Group_4__4 )
            // InternalBehavioralInterface.g:966:2: rule__Event__Group_4__3__Impl rule__Event__Group_4__4
            {
            pushFollow(FOLLOW_11);
            rule__Event__Group_4__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Event__Group_4__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__Group_4__3"


    // $ANTLR start "rule__Event__Group_4__3__Impl"
    // InternalBehavioralInterface.g:973:1: rule__Event__Group_4__3__Impl : ( ( rule__Event__ParamsAssignment_4_3 ) ) ;
    public final void rule__Event__Group_4__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:977:1: ( ( ( rule__Event__ParamsAssignment_4_3 ) ) )
            // InternalBehavioralInterface.g:978:1: ( ( rule__Event__ParamsAssignment_4_3 ) )
            {
            // InternalBehavioralInterface.g:978:1: ( ( rule__Event__ParamsAssignment_4_3 ) )
            // InternalBehavioralInterface.g:979:2: ( rule__Event__ParamsAssignment_4_3 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventAccess().getParamsAssignment_4_3()); 
            }
            // InternalBehavioralInterface.g:980:2: ( rule__Event__ParamsAssignment_4_3 )
            // InternalBehavioralInterface.g:980:3: rule__Event__ParamsAssignment_4_3
            {
            pushFollow(FOLLOW_2);
            rule__Event__ParamsAssignment_4_3();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventAccess().getParamsAssignment_4_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__Group_4__3__Impl"


    // $ANTLR start "rule__Event__Group_4__4"
    // InternalBehavioralInterface.g:988:1: rule__Event__Group_4__4 : rule__Event__Group_4__4__Impl rule__Event__Group_4__5 ;
    public final void rule__Event__Group_4__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:992:1: ( rule__Event__Group_4__4__Impl rule__Event__Group_4__5 )
            // InternalBehavioralInterface.g:993:2: rule__Event__Group_4__4__Impl rule__Event__Group_4__5
            {
            pushFollow(FOLLOW_11);
            rule__Event__Group_4__4__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Event__Group_4__5();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__Group_4__4"


    // $ANTLR start "rule__Event__Group_4__4__Impl"
    // InternalBehavioralInterface.g:1000:1: rule__Event__Group_4__4__Impl : ( ( rule__Event__Group_4_4__0 )* ) ;
    public final void rule__Event__Group_4__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1004:1: ( ( ( rule__Event__Group_4_4__0 )* ) )
            // InternalBehavioralInterface.g:1005:1: ( ( rule__Event__Group_4_4__0 )* )
            {
            // InternalBehavioralInterface.g:1005:1: ( ( rule__Event__Group_4_4__0 )* )
            // InternalBehavioralInterface.g:1006:2: ( rule__Event__Group_4_4__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventAccess().getGroup_4_4()); 
            }
            // InternalBehavioralInterface.g:1007:2: ( rule__Event__Group_4_4__0 )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==20) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalBehavioralInterface.g:1007:3: rule__Event__Group_4_4__0
            	    {
            	    pushFollow(FOLLOW_12);
            	    rule__Event__Group_4_4__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventAccess().getGroup_4_4()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__Group_4__4__Impl"


    // $ANTLR start "rule__Event__Group_4__5"
    // InternalBehavioralInterface.g:1015:1: rule__Event__Group_4__5 : rule__Event__Group_4__5__Impl ;
    public final void rule__Event__Group_4__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1019:1: ( rule__Event__Group_4__5__Impl )
            // InternalBehavioralInterface.g:1020:2: rule__Event__Group_4__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Event__Group_4__5__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__Group_4__5"


    // $ANTLR start "rule__Event__Group_4__5__Impl"
    // InternalBehavioralInterface.g:1026:1: rule__Event__Group_4__5__Impl : ( ']' ) ;
    public final void rule__Event__Group_4__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1030:1: ( ( ']' ) )
            // InternalBehavioralInterface.g:1031:1: ( ']' )
            {
            // InternalBehavioralInterface.g:1031:1: ( ']' )
            // InternalBehavioralInterface.g:1032:2: ']'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventAccess().getRightSquareBracketKeyword_4_5()); 
            }
            match(input,19,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventAccess().getRightSquareBracketKeyword_4_5()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__Group_4__5__Impl"


    // $ANTLR start "rule__Event__Group_4_4__0"
    // InternalBehavioralInterface.g:1042:1: rule__Event__Group_4_4__0 : rule__Event__Group_4_4__0__Impl rule__Event__Group_4_4__1 ;
    public final void rule__Event__Group_4_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1046:1: ( rule__Event__Group_4_4__0__Impl rule__Event__Group_4_4__1 )
            // InternalBehavioralInterface.g:1047:2: rule__Event__Group_4_4__0__Impl rule__Event__Group_4_4__1
            {
            pushFollow(FOLLOW_4);
            rule__Event__Group_4_4__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Event__Group_4_4__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__Group_4_4__0"


    // $ANTLR start "rule__Event__Group_4_4__0__Impl"
    // InternalBehavioralInterface.g:1054:1: rule__Event__Group_4_4__0__Impl : ( ',' ) ;
    public final void rule__Event__Group_4_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1058:1: ( ( ',' ) )
            // InternalBehavioralInterface.g:1059:1: ( ',' )
            {
            // InternalBehavioralInterface.g:1059:1: ( ',' )
            // InternalBehavioralInterface.g:1060:2: ','
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventAccess().getCommaKeyword_4_4_0()); 
            }
            match(input,20,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventAccess().getCommaKeyword_4_4_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__Group_4_4__0__Impl"


    // $ANTLR start "rule__Event__Group_4_4__1"
    // InternalBehavioralInterface.g:1069:1: rule__Event__Group_4_4__1 : rule__Event__Group_4_4__1__Impl ;
    public final void rule__Event__Group_4_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1073:1: ( rule__Event__Group_4_4__1__Impl )
            // InternalBehavioralInterface.g:1074:2: rule__Event__Group_4_4__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Event__Group_4_4__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__Group_4_4__1"


    // $ANTLR start "rule__Event__Group_4_4__1__Impl"
    // InternalBehavioralInterface.g:1080:1: rule__Event__Group_4_4__1__Impl : ( ( rule__Event__ParamsAssignment_4_4_1 ) ) ;
    public final void rule__Event__Group_4_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1084:1: ( ( ( rule__Event__ParamsAssignment_4_4_1 ) ) )
            // InternalBehavioralInterface.g:1085:1: ( ( rule__Event__ParamsAssignment_4_4_1 ) )
            {
            // InternalBehavioralInterface.g:1085:1: ( ( rule__Event__ParamsAssignment_4_4_1 ) )
            // InternalBehavioralInterface.g:1086:2: ( rule__Event__ParamsAssignment_4_4_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventAccess().getParamsAssignment_4_4_1()); 
            }
            // InternalBehavioralInterface.g:1087:2: ( rule__Event__ParamsAssignment_4_4_1 )
            // InternalBehavioralInterface.g:1087:3: rule__Event__ParamsAssignment_4_4_1
            {
            pushFollow(FOLLOW_2);
            rule__Event__ParamsAssignment_4_4_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventAccess().getParamsAssignment_4_4_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__Group_4_4__1__Impl"


    // $ANTLR start "rule__EventParameter__Group__0"
    // InternalBehavioralInterface.g:1096:1: rule__EventParameter__Group__0 : rule__EventParameter__Group__0__Impl rule__EventParameter__Group__1 ;
    public final void rule__EventParameter__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1100:1: ( rule__EventParameter__Group__0__Impl rule__EventParameter__Group__1 )
            // InternalBehavioralInterface.g:1101:2: rule__EventParameter__Group__0__Impl rule__EventParameter__Group__1
            {
            pushFollow(FOLLOW_7);
            rule__EventParameter__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__EventParameter__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EventParameter__Group__0"


    // $ANTLR start "rule__EventParameter__Group__0__Impl"
    // InternalBehavioralInterface.g:1108:1: rule__EventParameter__Group__0__Impl : ( ( rule__EventParameter__NameAssignment_0 ) ) ;
    public final void rule__EventParameter__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1112:1: ( ( ( rule__EventParameter__NameAssignment_0 ) ) )
            // InternalBehavioralInterface.g:1113:1: ( ( rule__EventParameter__NameAssignment_0 ) )
            {
            // InternalBehavioralInterface.g:1113:1: ( ( rule__EventParameter__NameAssignment_0 ) )
            // InternalBehavioralInterface.g:1114:2: ( rule__EventParameter__NameAssignment_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventParameterAccess().getNameAssignment_0()); 
            }
            // InternalBehavioralInterface.g:1115:2: ( rule__EventParameter__NameAssignment_0 )
            // InternalBehavioralInterface.g:1115:3: rule__EventParameter__NameAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__EventParameter__NameAssignment_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventParameterAccess().getNameAssignment_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EventParameter__Group__0__Impl"


    // $ANTLR start "rule__EventParameter__Group__1"
    // InternalBehavioralInterface.g:1123:1: rule__EventParameter__Group__1 : rule__EventParameter__Group__1__Impl rule__EventParameter__Group__2 ;
    public final void rule__EventParameter__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1127:1: ( rule__EventParameter__Group__1__Impl rule__EventParameter__Group__2 )
            // InternalBehavioralInterface.g:1128:2: rule__EventParameter__Group__1__Impl rule__EventParameter__Group__2
            {
            pushFollow(FOLLOW_4);
            rule__EventParameter__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__EventParameter__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EventParameter__Group__1"


    // $ANTLR start "rule__EventParameter__Group__1__Impl"
    // InternalBehavioralInterface.g:1135:1: rule__EventParameter__Group__1__Impl : ( ':' ) ;
    public final void rule__EventParameter__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1139:1: ( ( ':' ) )
            // InternalBehavioralInterface.g:1140:1: ( ':' )
            {
            // InternalBehavioralInterface.g:1140:1: ( ':' )
            // InternalBehavioralInterface.g:1141:2: ':'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventParameterAccess().getColonKeyword_1()); 
            }
            match(input,15,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventParameterAccess().getColonKeyword_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EventParameter__Group__1__Impl"


    // $ANTLR start "rule__EventParameter__Group__2"
    // InternalBehavioralInterface.g:1150:1: rule__EventParameter__Group__2 : rule__EventParameter__Group__2__Impl rule__EventParameter__Group__3 ;
    public final void rule__EventParameter__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1154:1: ( rule__EventParameter__Group__2__Impl rule__EventParameter__Group__3 )
            // InternalBehavioralInterface.g:1155:2: rule__EventParameter__Group__2__Impl rule__EventParameter__Group__3
            {
            pushFollow(FOLLOW_13);
            rule__EventParameter__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__EventParameter__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EventParameter__Group__2"


    // $ANTLR start "rule__EventParameter__Group__2__Impl"
    // InternalBehavioralInterface.g:1162:1: rule__EventParameter__Group__2__Impl : ( ( rule__EventParameter__TypeAssignment_2 ) ) ;
    public final void rule__EventParameter__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1166:1: ( ( ( rule__EventParameter__TypeAssignment_2 ) ) )
            // InternalBehavioralInterface.g:1167:1: ( ( rule__EventParameter__TypeAssignment_2 ) )
            {
            // InternalBehavioralInterface.g:1167:1: ( ( rule__EventParameter__TypeAssignment_2 ) )
            // InternalBehavioralInterface.g:1168:2: ( rule__EventParameter__TypeAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventParameterAccess().getTypeAssignment_2()); 
            }
            // InternalBehavioralInterface.g:1169:2: ( rule__EventParameter__TypeAssignment_2 )
            // InternalBehavioralInterface.g:1169:3: rule__EventParameter__TypeAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__EventParameter__TypeAssignment_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventParameterAccess().getTypeAssignment_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EventParameter__Group__2__Impl"


    // $ANTLR start "rule__EventParameter__Group__3"
    // InternalBehavioralInterface.g:1177:1: rule__EventParameter__Group__3 : rule__EventParameter__Group__3__Impl ;
    public final void rule__EventParameter__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1181:1: ( rule__EventParameter__Group__3__Impl )
            // InternalBehavioralInterface.g:1182:2: rule__EventParameter__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__EventParameter__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EventParameter__Group__3"


    // $ANTLR start "rule__EventParameter__Group__3__Impl"
    // InternalBehavioralInterface.g:1188:1: rule__EventParameter__Group__3__Impl : ( ( rule__EventParameter__ManyAssignment_3 )? ) ;
    public final void rule__EventParameter__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1192:1: ( ( ( rule__EventParameter__ManyAssignment_3 )? ) )
            // InternalBehavioralInterface.g:1193:1: ( ( rule__EventParameter__ManyAssignment_3 )? )
            {
            // InternalBehavioralInterface.g:1193:1: ( ( rule__EventParameter__ManyAssignment_3 )? )
            // InternalBehavioralInterface.g:1194:2: ( rule__EventParameter__ManyAssignment_3 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventParameterAccess().getManyAssignment_3()); 
            }
            // InternalBehavioralInterface.g:1195:2: ( rule__EventParameter__ManyAssignment_3 )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==31) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalBehavioralInterface.g:1195:3: rule__EventParameter__ManyAssignment_3
                    {
                    pushFollow(FOLLOW_2);
                    rule__EventParameter__ManyAssignment_3();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventParameterAccess().getManyAssignment_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EventParameter__Group__3__Impl"


    // $ANTLR start "rule__JvmTypeReference__Group_0__0"
    // InternalBehavioralInterface.g:1204:1: rule__JvmTypeReference__Group_0__0 : rule__JvmTypeReference__Group_0__0__Impl rule__JvmTypeReference__Group_0__1 ;
    public final void rule__JvmTypeReference__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1208:1: ( rule__JvmTypeReference__Group_0__0__Impl rule__JvmTypeReference__Group_0__1 )
            // InternalBehavioralInterface.g:1209:2: rule__JvmTypeReference__Group_0__0__Impl rule__JvmTypeReference__Group_0__1
            {
            pushFollow(FOLLOW_10);
            rule__JvmTypeReference__Group_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__JvmTypeReference__Group_0__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmTypeReference__Group_0__0"


    // $ANTLR start "rule__JvmTypeReference__Group_0__0__Impl"
    // InternalBehavioralInterface.g:1216:1: rule__JvmTypeReference__Group_0__0__Impl : ( ruleJvmParameterizedTypeReference ) ;
    public final void rule__JvmTypeReference__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1220:1: ( ( ruleJvmParameterizedTypeReference ) )
            // InternalBehavioralInterface.g:1221:1: ( ruleJvmParameterizedTypeReference )
            {
            // InternalBehavioralInterface.g:1221:1: ( ruleJvmParameterizedTypeReference )
            // InternalBehavioralInterface.g:1222:2: ruleJvmParameterizedTypeReference
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmTypeReferenceAccess().getJvmParameterizedTypeReferenceParserRuleCall_0_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleJvmParameterizedTypeReference();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmTypeReferenceAccess().getJvmParameterizedTypeReferenceParserRuleCall_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmTypeReference__Group_0__0__Impl"


    // $ANTLR start "rule__JvmTypeReference__Group_0__1"
    // InternalBehavioralInterface.g:1231:1: rule__JvmTypeReference__Group_0__1 : rule__JvmTypeReference__Group_0__1__Impl ;
    public final void rule__JvmTypeReference__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1235:1: ( rule__JvmTypeReference__Group_0__1__Impl )
            // InternalBehavioralInterface.g:1236:2: rule__JvmTypeReference__Group_0__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__JvmTypeReference__Group_0__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmTypeReference__Group_0__1"


    // $ANTLR start "rule__JvmTypeReference__Group_0__1__Impl"
    // InternalBehavioralInterface.g:1242:1: rule__JvmTypeReference__Group_0__1__Impl : ( ( rule__JvmTypeReference__Group_0_1__0 )* ) ;
    public final void rule__JvmTypeReference__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1246:1: ( ( ( rule__JvmTypeReference__Group_0_1__0 )* ) )
            // InternalBehavioralInterface.g:1247:1: ( ( rule__JvmTypeReference__Group_0_1__0 )* )
            {
            // InternalBehavioralInterface.g:1247:1: ( ( rule__JvmTypeReference__Group_0_1__0 )* )
            // InternalBehavioralInterface.g:1248:2: ( rule__JvmTypeReference__Group_0_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmTypeReferenceAccess().getGroup_0_1()); 
            }
            // InternalBehavioralInterface.g:1249:2: ( rule__JvmTypeReference__Group_0_1__0 )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==18) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalBehavioralInterface.g:1249:3: rule__JvmTypeReference__Group_0_1__0
            	    {
            	    pushFollow(FOLLOW_14);
            	    rule__JvmTypeReference__Group_0_1__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmTypeReferenceAccess().getGroup_0_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmTypeReference__Group_0__1__Impl"


    // $ANTLR start "rule__JvmTypeReference__Group_0_1__0"
    // InternalBehavioralInterface.g:1258:1: rule__JvmTypeReference__Group_0_1__0 : rule__JvmTypeReference__Group_0_1__0__Impl ;
    public final void rule__JvmTypeReference__Group_0_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1262:1: ( rule__JvmTypeReference__Group_0_1__0__Impl )
            // InternalBehavioralInterface.g:1263:2: rule__JvmTypeReference__Group_0_1__0__Impl
            {
            pushFollow(FOLLOW_2);
            rule__JvmTypeReference__Group_0_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmTypeReference__Group_0_1__0"


    // $ANTLR start "rule__JvmTypeReference__Group_0_1__0__Impl"
    // InternalBehavioralInterface.g:1269:1: rule__JvmTypeReference__Group_0_1__0__Impl : ( ( rule__JvmTypeReference__Group_0_1_0__0 ) ) ;
    public final void rule__JvmTypeReference__Group_0_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1273:1: ( ( ( rule__JvmTypeReference__Group_0_1_0__0 ) ) )
            // InternalBehavioralInterface.g:1274:1: ( ( rule__JvmTypeReference__Group_0_1_0__0 ) )
            {
            // InternalBehavioralInterface.g:1274:1: ( ( rule__JvmTypeReference__Group_0_1_0__0 ) )
            // InternalBehavioralInterface.g:1275:2: ( rule__JvmTypeReference__Group_0_1_0__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmTypeReferenceAccess().getGroup_0_1_0()); 
            }
            // InternalBehavioralInterface.g:1276:2: ( rule__JvmTypeReference__Group_0_1_0__0 )
            // InternalBehavioralInterface.g:1276:3: rule__JvmTypeReference__Group_0_1_0__0
            {
            pushFollow(FOLLOW_2);
            rule__JvmTypeReference__Group_0_1_0__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmTypeReferenceAccess().getGroup_0_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmTypeReference__Group_0_1__0__Impl"


    // $ANTLR start "rule__JvmTypeReference__Group_0_1_0__0"
    // InternalBehavioralInterface.g:1285:1: rule__JvmTypeReference__Group_0_1_0__0 : rule__JvmTypeReference__Group_0_1_0__0__Impl rule__JvmTypeReference__Group_0_1_0__1 ;
    public final void rule__JvmTypeReference__Group_0_1_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1289:1: ( rule__JvmTypeReference__Group_0_1_0__0__Impl rule__JvmTypeReference__Group_0_1_0__1 )
            // InternalBehavioralInterface.g:1290:2: rule__JvmTypeReference__Group_0_1_0__0__Impl rule__JvmTypeReference__Group_0_1_0__1
            {
            pushFollow(FOLLOW_10);
            rule__JvmTypeReference__Group_0_1_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__JvmTypeReference__Group_0_1_0__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmTypeReference__Group_0_1_0__0"


    // $ANTLR start "rule__JvmTypeReference__Group_0_1_0__0__Impl"
    // InternalBehavioralInterface.g:1297:1: rule__JvmTypeReference__Group_0_1_0__0__Impl : ( () ) ;
    public final void rule__JvmTypeReference__Group_0_1_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1301:1: ( ( () ) )
            // InternalBehavioralInterface.g:1302:1: ( () )
            {
            // InternalBehavioralInterface.g:1302:1: ( () )
            // InternalBehavioralInterface.g:1303:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmTypeReferenceAccess().getJvmGenericArrayTypeReferenceComponentTypeAction_0_1_0_0()); 
            }
            // InternalBehavioralInterface.g:1304:2: ()
            // InternalBehavioralInterface.g:1304:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmTypeReferenceAccess().getJvmGenericArrayTypeReferenceComponentTypeAction_0_1_0_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmTypeReference__Group_0_1_0__0__Impl"


    // $ANTLR start "rule__JvmTypeReference__Group_0_1_0__1"
    // InternalBehavioralInterface.g:1312:1: rule__JvmTypeReference__Group_0_1_0__1 : rule__JvmTypeReference__Group_0_1_0__1__Impl ;
    public final void rule__JvmTypeReference__Group_0_1_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1316:1: ( rule__JvmTypeReference__Group_0_1_0__1__Impl )
            // InternalBehavioralInterface.g:1317:2: rule__JvmTypeReference__Group_0_1_0__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__JvmTypeReference__Group_0_1_0__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmTypeReference__Group_0_1_0__1"


    // $ANTLR start "rule__JvmTypeReference__Group_0_1_0__1__Impl"
    // InternalBehavioralInterface.g:1323:1: rule__JvmTypeReference__Group_0_1_0__1__Impl : ( ruleArrayBrackets ) ;
    public final void rule__JvmTypeReference__Group_0_1_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1327:1: ( ( ruleArrayBrackets ) )
            // InternalBehavioralInterface.g:1328:1: ( ruleArrayBrackets )
            {
            // InternalBehavioralInterface.g:1328:1: ( ruleArrayBrackets )
            // InternalBehavioralInterface.g:1329:2: ruleArrayBrackets
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmTypeReferenceAccess().getArrayBracketsParserRuleCall_0_1_0_1()); 
            }
            pushFollow(FOLLOW_2);
            ruleArrayBrackets();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmTypeReferenceAccess().getArrayBracketsParserRuleCall_0_1_0_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmTypeReference__Group_0_1_0__1__Impl"


    // $ANTLR start "rule__ArrayBrackets__Group__0"
    // InternalBehavioralInterface.g:1339:1: rule__ArrayBrackets__Group__0 : rule__ArrayBrackets__Group__0__Impl rule__ArrayBrackets__Group__1 ;
    public final void rule__ArrayBrackets__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1343:1: ( rule__ArrayBrackets__Group__0__Impl rule__ArrayBrackets__Group__1 )
            // InternalBehavioralInterface.g:1344:2: rule__ArrayBrackets__Group__0__Impl rule__ArrayBrackets__Group__1
            {
            pushFollow(FOLLOW_15);
            rule__ArrayBrackets__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__ArrayBrackets__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ArrayBrackets__Group__0"


    // $ANTLR start "rule__ArrayBrackets__Group__0__Impl"
    // InternalBehavioralInterface.g:1351:1: rule__ArrayBrackets__Group__0__Impl : ( '[' ) ;
    public final void rule__ArrayBrackets__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1355:1: ( ( '[' ) )
            // InternalBehavioralInterface.g:1356:1: ( '[' )
            {
            // InternalBehavioralInterface.g:1356:1: ( '[' )
            // InternalBehavioralInterface.g:1357:2: '['
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getArrayBracketsAccess().getLeftSquareBracketKeyword_0()); 
            }
            match(input,18,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getArrayBracketsAccess().getLeftSquareBracketKeyword_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ArrayBrackets__Group__0__Impl"


    // $ANTLR start "rule__ArrayBrackets__Group__1"
    // InternalBehavioralInterface.g:1366:1: rule__ArrayBrackets__Group__1 : rule__ArrayBrackets__Group__1__Impl ;
    public final void rule__ArrayBrackets__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1370:1: ( rule__ArrayBrackets__Group__1__Impl )
            // InternalBehavioralInterface.g:1371:2: rule__ArrayBrackets__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ArrayBrackets__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ArrayBrackets__Group__1"


    // $ANTLR start "rule__ArrayBrackets__Group__1__Impl"
    // InternalBehavioralInterface.g:1377:1: rule__ArrayBrackets__Group__1__Impl : ( ']' ) ;
    public final void rule__ArrayBrackets__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1381:1: ( ( ']' ) )
            // InternalBehavioralInterface.g:1382:1: ( ']' )
            {
            // InternalBehavioralInterface.g:1382:1: ( ']' )
            // InternalBehavioralInterface.g:1383:2: ']'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getArrayBracketsAccess().getRightSquareBracketKeyword_1()); 
            }
            match(input,19,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getArrayBracketsAccess().getRightSquareBracketKeyword_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ArrayBrackets__Group__1__Impl"


    // $ANTLR start "rule__XFunctionTypeRef__Group__0"
    // InternalBehavioralInterface.g:1393:1: rule__XFunctionTypeRef__Group__0 : rule__XFunctionTypeRef__Group__0__Impl rule__XFunctionTypeRef__Group__1 ;
    public final void rule__XFunctionTypeRef__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1397:1: ( rule__XFunctionTypeRef__Group__0__Impl rule__XFunctionTypeRef__Group__1 )
            // InternalBehavioralInterface.g:1398:2: rule__XFunctionTypeRef__Group__0__Impl rule__XFunctionTypeRef__Group__1
            {
            pushFollow(FOLLOW_16);
            rule__XFunctionTypeRef__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__XFunctionTypeRef__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XFunctionTypeRef__Group__0"


    // $ANTLR start "rule__XFunctionTypeRef__Group__0__Impl"
    // InternalBehavioralInterface.g:1405:1: rule__XFunctionTypeRef__Group__0__Impl : ( ( rule__XFunctionTypeRef__Group_0__0 )? ) ;
    public final void rule__XFunctionTypeRef__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1409:1: ( ( ( rule__XFunctionTypeRef__Group_0__0 )? ) )
            // InternalBehavioralInterface.g:1410:1: ( ( rule__XFunctionTypeRef__Group_0__0 )? )
            {
            // InternalBehavioralInterface.g:1410:1: ( ( rule__XFunctionTypeRef__Group_0__0 )? )
            // InternalBehavioralInterface.g:1411:2: ( rule__XFunctionTypeRef__Group_0__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXFunctionTypeRefAccess().getGroup_0()); 
            }
            // InternalBehavioralInterface.g:1412:2: ( rule__XFunctionTypeRef__Group_0__0 )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==22) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalBehavioralInterface.g:1412:3: rule__XFunctionTypeRef__Group_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__XFunctionTypeRef__Group_0__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getXFunctionTypeRefAccess().getGroup_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XFunctionTypeRef__Group__0__Impl"


    // $ANTLR start "rule__XFunctionTypeRef__Group__1"
    // InternalBehavioralInterface.g:1420:1: rule__XFunctionTypeRef__Group__1 : rule__XFunctionTypeRef__Group__1__Impl rule__XFunctionTypeRef__Group__2 ;
    public final void rule__XFunctionTypeRef__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1424:1: ( rule__XFunctionTypeRef__Group__1__Impl rule__XFunctionTypeRef__Group__2 )
            // InternalBehavioralInterface.g:1425:2: rule__XFunctionTypeRef__Group__1__Impl rule__XFunctionTypeRef__Group__2
            {
            pushFollow(FOLLOW_17);
            rule__XFunctionTypeRef__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__XFunctionTypeRef__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XFunctionTypeRef__Group__1"


    // $ANTLR start "rule__XFunctionTypeRef__Group__1__Impl"
    // InternalBehavioralInterface.g:1432:1: rule__XFunctionTypeRef__Group__1__Impl : ( '=>' ) ;
    public final void rule__XFunctionTypeRef__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1436:1: ( ( '=>' ) )
            // InternalBehavioralInterface.g:1437:1: ( '=>' )
            {
            // InternalBehavioralInterface.g:1437:1: ( '=>' )
            // InternalBehavioralInterface.g:1438:2: '=>'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXFunctionTypeRefAccess().getEqualsSignGreaterThanSignKeyword_1()); 
            }
            match(input,21,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getXFunctionTypeRefAccess().getEqualsSignGreaterThanSignKeyword_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XFunctionTypeRef__Group__1__Impl"


    // $ANTLR start "rule__XFunctionTypeRef__Group__2"
    // InternalBehavioralInterface.g:1447:1: rule__XFunctionTypeRef__Group__2 : rule__XFunctionTypeRef__Group__2__Impl ;
    public final void rule__XFunctionTypeRef__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1451:1: ( rule__XFunctionTypeRef__Group__2__Impl )
            // InternalBehavioralInterface.g:1452:2: rule__XFunctionTypeRef__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__XFunctionTypeRef__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XFunctionTypeRef__Group__2"


    // $ANTLR start "rule__XFunctionTypeRef__Group__2__Impl"
    // InternalBehavioralInterface.g:1458:1: rule__XFunctionTypeRef__Group__2__Impl : ( ( rule__XFunctionTypeRef__ReturnTypeAssignment_2 ) ) ;
    public final void rule__XFunctionTypeRef__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1462:1: ( ( ( rule__XFunctionTypeRef__ReturnTypeAssignment_2 ) ) )
            // InternalBehavioralInterface.g:1463:1: ( ( rule__XFunctionTypeRef__ReturnTypeAssignment_2 ) )
            {
            // InternalBehavioralInterface.g:1463:1: ( ( rule__XFunctionTypeRef__ReturnTypeAssignment_2 ) )
            // InternalBehavioralInterface.g:1464:2: ( rule__XFunctionTypeRef__ReturnTypeAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXFunctionTypeRefAccess().getReturnTypeAssignment_2()); 
            }
            // InternalBehavioralInterface.g:1465:2: ( rule__XFunctionTypeRef__ReturnTypeAssignment_2 )
            // InternalBehavioralInterface.g:1465:3: rule__XFunctionTypeRef__ReturnTypeAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__XFunctionTypeRef__ReturnTypeAssignment_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getXFunctionTypeRefAccess().getReturnTypeAssignment_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XFunctionTypeRef__Group__2__Impl"


    // $ANTLR start "rule__XFunctionTypeRef__Group_0__0"
    // InternalBehavioralInterface.g:1474:1: rule__XFunctionTypeRef__Group_0__0 : rule__XFunctionTypeRef__Group_0__0__Impl rule__XFunctionTypeRef__Group_0__1 ;
    public final void rule__XFunctionTypeRef__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1478:1: ( rule__XFunctionTypeRef__Group_0__0__Impl rule__XFunctionTypeRef__Group_0__1 )
            // InternalBehavioralInterface.g:1479:2: rule__XFunctionTypeRef__Group_0__0__Impl rule__XFunctionTypeRef__Group_0__1
            {
            pushFollow(FOLLOW_18);
            rule__XFunctionTypeRef__Group_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__XFunctionTypeRef__Group_0__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XFunctionTypeRef__Group_0__0"


    // $ANTLR start "rule__XFunctionTypeRef__Group_0__0__Impl"
    // InternalBehavioralInterface.g:1486:1: rule__XFunctionTypeRef__Group_0__0__Impl : ( '(' ) ;
    public final void rule__XFunctionTypeRef__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1490:1: ( ( '(' ) )
            // InternalBehavioralInterface.g:1491:1: ( '(' )
            {
            // InternalBehavioralInterface.g:1491:1: ( '(' )
            // InternalBehavioralInterface.g:1492:2: '('
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXFunctionTypeRefAccess().getLeftParenthesisKeyword_0_0()); 
            }
            match(input,22,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getXFunctionTypeRefAccess().getLeftParenthesisKeyword_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XFunctionTypeRef__Group_0__0__Impl"


    // $ANTLR start "rule__XFunctionTypeRef__Group_0__1"
    // InternalBehavioralInterface.g:1501:1: rule__XFunctionTypeRef__Group_0__1 : rule__XFunctionTypeRef__Group_0__1__Impl rule__XFunctionTypeRef__Group_0__2 ;
    public final void rule__XFunctionTypeRef__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1505:1: ( rule__XFunctionTypeRef__Group_0__1__Impl rule__XFunctionTypeRef__Group_0__2 )
            // InternalBehavioralInterface.g:1506:2: rule__XFunctionTypeRef__Group_0__1__Impl rule__XFunctionTypeRef__Group_0__2
            {
            pushFollow(FOLLOW_18);
            rule__XFunctionTypeRef__Group_0__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__XFunctionTypeRef__Group_0__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XFunctionTypeRef__Group_0__1"


    // $ANTLR start "rule__XFunctionTypeRef__Group_0__1__Impl"
    // InternalBehavioralInterface.g:1513:1: rule__XFunctionTypeRef__Group_0__1__Impl : ( ( rule__XFunctionTypeRef__Group_0_1__0 )? ) ;
    public final void rule__XFunctionTypeRef__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1517:1: ( ( ( rule__XFunctionTypeRef__Group_0_1__0 )? ) )
            // InternalBehavioralInterface.g:1518:1: ( ( rule__XFunctionTypeRef__Group_0_1__0 )? )
            {
            // InternalBehavioralInterface.g:1518:1: ( ( rule__XFunctionTypeRef__Group_0_1__0 )? )
            // InternalBehavioralInterface.g:1519:2: ( rule__XFunctionTypeRef__Group_0_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXFunctionTypeRefAccess().getGroup_0_1()); 
            }
            // InternalBehavioralInterface.g:1520:2: ( rule__XFunctionTypeRef__Group_0_1__0 )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==RULE_ID||(LA15_0>=21 && LA15_0<=22)) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalBehavioralInterface.g:1520:3: rule__XFunctionTypeRef__Group_0_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__XFunctionTypeRef__Group_0_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getXFunctionTypeRefAccess().getGroup_0_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XFunctionTypeRef__Group_0__1__Impl"


    // $ANTLR start "rule__XFunctionTypeRef__Group_0__2"
    // InternalBehavioralInterface.g:1528:1: rule__XFunctionTypeRef__Group_0__2 : rule__XFunctionTypeRef__Group_0__2__Impl ;
    public final void rule__XFunctionTypeRef__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1532:1: ( rule__XFunctionTypeRef__Group_0__2__Impl )
            // InternalBehavioralInterface.g:1533:2: rule__XFunctionTypeRef__Group_0__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__XFunctionTypeRef__Group_0__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XFunctionTypeRef__Group_0__2"


    // $ANTLR start "rule__XFunctionTypeRef__Group_0__2__Impl"
    // InternalBehavioralInterface.g:1539:1: rule__XFunctionTypeRef__Group_0__2__Impl : ( ')' ) ;
    public final void rule__XFunctionTypeRef__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1543:1: ( ( ')' ) )
            // InternalBehavioralInterface.g:1544:1: ( ')' )
            {
            // InternalBehavioralInterface.g:1544:1: ( ')' )
            // InternalBehavioralInterface.g:1545:2: ')'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXFunctionTypeRefAccess().getRightParenthesisKeyword_0_2()); 
            }
            match(input,23,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getXFunctionTypeRefAccess().getRightParenthesisKeyword_0_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XFunctionTypeRef__Group_0__2__Impl"


    // $ANTLR start "rule__XFunctionTypeRef__Group_0_1__0"
    // InternalBehavioralInterface.g:1555:1: rule__XFunctionTypeRef__Group_0_1__0 : rule__XFunctionTypeRef__Group_0_1__0__Impl rule__XFunctionTypeRef__Group_0_1__1 ;
    public final void rule__XFunctionTypeRef__Group_0_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1559:1: ( rule__XFunctionTypeRef__Group_0_1__0__Impl rule__XFunctionTypeRef__Group_0_1__1 )
            // InternalBehavioralInterface.g:1560:2: rule__XFunctionTypeRef__Group_0_1__0__Impl rule__XFunctionTypeRef__Group_0_1__1
            {
            pushFollow(FOLLOW_19);
            rule__XFunctionTypeRef__Group_0_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__XFunctionTypeRef__Group_0_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XFunctionTypeRef__Group_0_1__0"


    // $ANTLR start "rule__XFunctionTypeRef__Group_0_1__0__Impl"
    // InternalBehavioralInterface.g:1567:1: rule__XFunctionTypeRef__Group_0_1__0__Impl : ( ( rule__XFunctionTypeRef__ParamTypesAssignment_0_1_0 ) ) ;
    public final void rule__XFunctionTypeRef__Group_0_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1571:1: ( ( ( rule__XFunctionTypeRef__ParamTypesAssignment_0_1_0 ) ) )
            // InternalBehavioralInterface.g:1572:1: ( ( rule__XFunctionTypeRef__ParamTypesAssignment_0_1_0 ) )
            {
            // InternalBehavioralInterface.g:1572:1: ( ( rule__XFunctionTypeRef__ParamTypesAssignment_0_1_0 ) )
            // InternalBehavioralInterface.g:1573:2: ( rule__XFunctionTypeRef__ParamTypesAssignment_0_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXFunctionTypeRefAccess().getParamTypesAssignment_0_1_0()); 
            }
            // InternalBehavioralInterface.g:1574:2: ( rule__XFunctionTypeRef__ParamTypesAssignment_0_1_0 )
            // InternalBehavioralInterface.g:1574:3: rule__XFunctionTypeRef__ParamTypesAssignment_0_1_0
            {
            pushFollow(FOLLOW_2);
            rule__XFunctionTypeRef__ParamTypesAssignment_0_1_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getXFunctionTypeRefAccess().getParamTypesAssignment_0_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XFunctionTypeRef__Group_0_1__0__Impl"


    // $ANTLR start "rule__XFunctionTypeRef__Group_0_1__1"
    // InternalBehavioralInterface.g:1582:1: rule__XFunctionTypeRef__Group_0_1__1 : rule__XFunctionTypeRef__Group_0_1__1__Impl ;
    public final void rule__XFunctionTypeRef__Group_0_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1586:1: ( rule__XFunctionTypeRef__Group_0_1__1__Impl )
            // InternalBehavioralInterface.g:1587:2: rule__XFunctionTypeRef__Group_0_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__XFunctionTypeRef__Group_0_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XFunctionTypeRef__Group_0_1__1"


    // $ANTLR start "rule__XFunctionTypeRef__Group_0_1__1__Impl"
    // InternalBehavioralInterface.g:1593:1: rule__XFunctionTypeRef__Group_0_1__1__Impl : ( ( rule__XFunctionTypeRef__Group_0_1_1__0 )* ) ;
    public final void rule__XFunctionTypeRef__Group_0_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1597:1: ( ( ( rule__XFunctionTypeRef__Group_0_1_1__0 )* ) )
            // InternalBehavioralInterface.g:1598:1: ( ( rule__XFunctionTypeRef__Group_0_1_1__0 )* )
            {
            // InternalBehavioralInterface.g:1598:1: ( ( rule__XFunctionTypeRef__Group_0_1_1__0 )* )
            // InternalBehavioralInterface.g:1599:2: ( rule__XFunctionTypeRef__Group_0_1_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXFunctionTypeRefAccess().getGroup_0_1_1()); 
            }
            // InternalBehavioralInterface.g:1600:2: ( rule__XFunctionTypeRef__Group_0_1_1__0 )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==20) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // InternalBehavioralInterface.g:1600:3: rule__XFunctionTypeRef__Group_0_1_1__0
            	    {
            	    pushFollow(FOLLOW_12);
            	    rule__XFunctionTypeRef__Group_0_1_1__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getXFunctionTypeRefAccess().getGroup_0_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XFunctionTypeRef__Group_0_1__1__Impl"


    // $ANTLR start "rule__XFunctionTypeRef__Group_0_1_1__0"
    // InternalBehavioralInterface.g:1609:1: rule__XFunctionTypeRef__Group_0_1_1__0 : rule__XFunctionTypeRef__Group_0_1_1__0__Impl rule__XFunctionTypeRef__Group_0_1_1__1 ;
    public final void rule__XFunctionTypeRef__Group_0_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1613:1: ( rule__XFunctionTypeRef__Group_0_1_1__0__Impl rule__XFunctionTypeRef__Group_0_1_1__1 )
            // InternalBehavioralInterface.g:1614:2: rule__XFunctionTypeRef__Group_0_1_1__0__Impl rule__XFunctionTypeRef__Group_0_1_1__1
            {
            pushFollow(FOLLOW_17);
            rule__XFunctionTypeRef__Group_0_1_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__XFunctionTypeRef__Group_0_1_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XFunctionTypeRef__Group_0_1_1__0"


    // $ANTLR start "rule__XFunctionTypeRef__Group_0_1_1__0__Impl"
    // InternalBehavioralInterface.g:1621:1: rule__XFunctionTypeRef__Group_0_1_1__0__Impl : ( ',' ) ;
    public final void rule__XFunctionTypeRef__Group_0_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1625:1: ( ( ',' ) )
            // InternalBehavioralInterface.g:1626:1: ( ',' )
            {
            // InternalBehavioralInterface.g:1626:1: ( ',' )
            // InternalBehavioralInterface.g:1627:2: ','
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXFunctionTypeRefAccess().getCommaKeyword_0_1_1_0()); 
            }
            match(input,20,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getXFunctionTypeRefAccess().getCommaKeyword_0_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XFunctionTypeRef__Group_0_1_1__0__Impl"


    // $ANTLR start "rule__XFunctionTypeRef__Group_0_1_1__1"
    // InternalBehavioralInterface.g:1636:1: rule__XFunctionTypeRef__Group_0_1_1__1 : rule__XFunctionTypeRef__Group_0_1_1__1__Impl ;
    public final void rule__XFunctionTypeRef__Group_0_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1640:1: ( rule__XFunctionTypeRef__Group_0_1_1__1__Impl )
            // InternalBehavioralInterface.g:1641:2: rule__XFunctionTypeRef__Group_0_1_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__XFunctionTypeRef__Group_0_1_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XFunctionTypeRef__Group_0_1_1__1"


    // $ANTLR start "rule__XFunctionTypeRef__Group_0_1_1__1__Impl"
    // InternalBehavioralInterface.g:1647:1: rule__XFunctionTypeRef__Group_0_1_1__1__Impl : ( ( rule__XFunctionTypeRef__ParamTypesAssignment_0_1_1_1 ) ) ;
    public final void rule__XFunctionTypeRef__Group_0_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1651:1: ( ( ( rule__XFunctionTypeRef__ParamTypesAssignment_0_1_1_1 ) ) )
            // InternalBehavioralInterface.g:1652:1: ( ( rule__XFunctionTypeRef__ParamTypesAssignment_0_1_1_1 ) )
            {
            // InternalBehavioralInterface.g:1652:1: ( ( rule__XFunctionTypeRef__ParamTypesAssignment_0_1_1_1 ) )
            // InternalBehavioralInterface.g:1653:2: ( rule__XFunctionTypeRef__ParamTypesAssignment_0_1_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXFunctionTypeRefAccess().getParamTypesAssignment_0_1_1_1()); 
            }
            // InternalBehavioralInterface.g:1654:2: ( rule__XFunctionTypeRef__ParamTypesAssignment_0_1_1_1 )
            // InternalBehavioralInterface.g:1654:3: rule__XFunctionTypeRef__ParamTypesAssignment_0_1_1_1
            {
            pushFollow(FOLLOW_2);
            rule__XFunctionTypeRef__ParamTypesAssignment_0_1_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getXFunctionTypeRefAccess().getParamTypesAssignment_0_1_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XFunctionTypeRef__Group_0_1_1__1__Impl"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group__0"
    // InternalBehavioralInterface.g:1663:1: rule__JvmParameterizedTypeReference__Group__0 : rule__JvmParameterizedTypeReference__Group__0__Impl rule__JvmParameterizedTypeReference__Group__1 ;
    public final void rule__JvmParameterizedTypeReference__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1667:1: ( rule__JvmParameterizedTypeReference__Group__0__Impl rule__JvmParameterizedTypeReference__Group__1 )
            // InternalBehavioralInterface.g:1668:2: rule__JvmParameterizedTypeReference__Group__0__Impl rule__JvmParameterizedTypeReference__Group__1
            {
            pushFollow(FOLLOW_20);
            rule__JvmParameterizedTypeReference__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group__0"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group__0__Impl"
    // InternalBehavioralInterface.g:1675:1: rule__JvmParameterizedTypeReference__Group__0__Impl : ( ( rule__JvmParameterizedTypeReference__TypeAssignment_0 ) ) ;
    public final void rule__JvmParameterizedTypeReference__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1679:1: ( ( ( rule__JvmParameterizedTypeReference__TypeAssignment_0 ) ) )
            // InternalBehavioralInterface.g:1680:1: ( ( rule__JvmParameterizedTypeReference__TypeAssignment_0 ) )
            {
            // InternalBehavioralInterface.g:1680:1: ( ( rule__JvmParameterizedTypeReference__TypeAssignment_0 ) )
            // InternalBehavioralInterface.g:1681:2: ( rule__JvmParameterizedTypeReference__TypeAssignment_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getTypeAssignment_0()); 
            }
            // InternalBehavioralInterface.g:1682:2: ( rule__JvmParameterizedTypeReference__TypeAssignment_0 )
            // InternalBehavioralInterface.g:1682:3: rule__JvmParameterizedTypeReference__TypeAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__TypeAssignment_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getTypeAssignment_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group__0__Impl"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group__1"
    // InternalBehavioralInterface.g:1690:1: rule__JvmParameterizedTypeReference__Group__1 : rule__JvmParameterizedTypeReference__Group__1__Impl ;
    public final void rule__JvmParameterizedTypeReference__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1694:1: ( rule__JvmParameterizedTypeReference__Group__1__Impl )
            // InternalBehavioralInterface.g:1695:2: rule__JvmParameterizedTypeReference__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group__1"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group__1__Impl"
    // InternalBehavioralInterface.g:1701:1: rule__JvmParameterizedTypeReference__Group__1__Impl : ( ( rule__JvmParameterizedTypeReference__Group_1__0 )? ) ;
    public final void rule__JvmParameterizedTypeReference__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1705:1: ( ( ( rule__JvmParameterizedTypeReference__Group_1__0 )? ) )
            // InternalBehavioralInterface.g:1706:1: ( ( rule__JvmParameterizedTypeReference__Group_1__0 )? )
            {
            // InternalBehavioralInterface.g:1706:1: ( ( rule__JvmParameterizedTypeReference__Group_1__0 )? )
            // InternalBehavioralInterface.g:1707:2: ( rule__JvmParameterizedTypeReference__Group_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getGroup_1()); 
            }
            // InternalBehavioralInterface.g:1708:2: ( rule__JvmParameterizedTypeReference__Group_1__0 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==24) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalBehavioralInterface.g:1708:3: rule__JvmParameterizedTypeReference__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__JvmParameterizedTypeReference__Group_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getGroup_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group__1__Impl"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1__0"
    // InternalBehavioralInterface.g:1717:1: rule__JvmParameterizedTypeReference__Group_1__0 : rule__JvmParameterizedTypeReference__Group_1__0__Impl rule__JvmParameterizedTypeReference__Group_1__1 ;
    public final void rule__JvmParameterizedTypeReference__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1721:1: ( rule__JvmParameterizedTypeReference__Group_1__0__Impl rule__JvmParameterizedTypeReference__Group_1__1 )
            // InternalBehavioralInterface.g:1722:2: rule__JvmParameterizedTypeReference__Group_1__0__Impl rule__JvmParameterizedTypeReference__Group_1__1
            {
            pushFollow(FOLLOW_21);
            rule__JvmParameterizedTypeReference__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1__0"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1__0__Impl"
    // InternalBehavioralInterface.g:1729:1: rule__JvmParameterizedTypeReference__Group_1__0__Impl : ( ( '<' ) ) ;
    public final void rule__JvmParameterizedTypeReference__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1733:1: ( ( ( '<' ) ) )
            // InternalBehavioralInterface.g:1734:1: ( ( '<' ) )
            {
            // InternalBehavioralInterface.g:1734:1: ( ( '<' ) )
            // InternalBehavioralInterface.g:1735:2: ( '<' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getLessThanSignKeyword_1_0()); 
            }
            // InternalBehavioralInterface.g:1736:2: ( '<' )
            // InternalBehavioralInterface.g:1736:3: '<'
            {
            match(input,24,FOLLOW_2); if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getLessThanSignKeyword_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1__0__Impl"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1__1"
    // InternalBehavioralInterface.g:1744:1: rule__JvmParameterizedTypeReference__Group_1__1 : rule__JvmParameterizedTypeReference__Group_1__1__Impl rule__JvmParameterizedTypeReference__Group_1__2 ;
    public final void rule__JvmParameterizedTypeReference__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1748:1: ( rule__JvmParameterizedTypeReference__Group_1__1__Impl rule__JvmParameterizedTypeReference__Group_1__2 )
            // InternalBehavioralInterface.g:1749:2: rule__JvmParameterizedTypeReference__Group_1__1__Impl rule__JvmParameterizedTypeReference__Group_1__2
            {
            pushFollow(FOLLOW_22);
            rule__JvmParameterizedTypeReference__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__Group_1__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1__1"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1__1__Impl"
    // InternalBehavioralInterface.g:1756:1: rule__JvmParameterizedTypeReference__Group_1__1__Impl : ( ( rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_1 ) ) ;
    public final void rule__JvmParameterizedTypeReference__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1760:1: ( ( ( rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_1 ) ) )
            // InternalBehavioralInterface.g:1761:1: ( ( rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_1 ) )
            {
            // InternalBehavioralInterface.g:1761:1: ( ( rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_1 ) )
            // InternalBehavioralInterface.g:1762:2: ( rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getArgumentsAssignment_1_1()); 
            }
            // InternalBehavioralInterface.g:1763:2: ( rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_1 )
            // InternalBehavioralInterface.g:1763:3: rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getArgumentsAssignment_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1__1__Impl"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1__2"
    // InternalBehavioralInterface.g:1771:1: rule__JvmParameterizedTypeReference__Group_1__2 : rule__JvmParameterizedTypeReference__Group_1__2__Impl rule__JvmParameterizedTypeReference__Group_1__3 ;
    public final void rule__JvmParameterizedTypeReference__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1775:1: ( rule__JvmParameterizedTypeReference__Group_1__2__Impl rule__JvmParameterizedTypeReference__Group_1__3 )
            // InternalBehavioralInterface.g:1776:2: rule__JvmParameterizedTypeReference__Group_1__2__Impl rule__JvmParameterizedTypeReference__Group_1__3
            {
            pushFollow(FOLLOW_22);
            rule__JvmParameterizedTypeReference__Group_1__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__Group_1__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1__2"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1__2__Impl"
    // InternalBehavioralInterface.g:1783:1: rule__JvmParameterizedTypeReference__Group_1__2__Impl : ( ( rule__JvmParameterizedTypeReference__Group_1_2__0 )* ) ;
    public final void rule__JvmParameterizedTypeReference__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1787:1: ( ( ( rule__JvmParameterizedTypeReference__Group_1_2__0 )* ) )
            // InternalBehavioralInterface.g:1788:1: ( ( rule__JvmParameterizedTypeReference__Group_1_2__0 )* )
            {
            // InternalBehavioralInterface.g:1788:1: ( ( rule__JvmParameterizedTypeReference__Group_1_2__0 )* )
            // InternalBehavioralInterface.g:1789:2: ( rule__JvmParameterizedTypeReference__Group_1_2__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getGroup_1_2()); 
            }
            // InternalBehavioralInterface.g:1790:2: ( rule__JvmParameterizedTypeReference__Group_1_2__0 )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==20) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // InternalBehavioralInterface.g:1790:3: rule__JvmParameterizedTypeReference__Group_1_2__0
            	    {
            	    pushFollow(FOLLOW_12);
            	    rule__JvmParameterizedTypeReference__Group_1_2__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getGroup_1_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1__2__Impl"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1__3"
    // InternalBehavioralInterface.g:1798:1: rule__JvmParameterizedTypeReference__Group_1__3 : rule__JvmParameterizedTypeReference__Group_1__3__Impl rule__JvmParameterizedTypeReference__Group_1__4 ;
    public final void rule__JvmParameterizedTypeReference__Group_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1802:1: ( rule__JvmParameterizedTypeReference__Group_1__3__Impl rule__JvmParameterizedTypeReference__Group_1__4 )
            // InternalBehavioralInterface.g:1803:2: rule__JvmParameterizedTypeReference__Group_1__3__Impl rule__JvmParameterizedTypeReference__Group_1__4
            {
            pushFollow(FOLLOW_23);
            rule__JvmParameterizedTypeReference__Group_1__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__Group_1__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1__3"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1__3__Impl"
    // InternalBehavioralInterface.g:1810:1: rule__JvmParameterizedTypeReference__Group_1__3__Impl : ( '>' ) ;
    public final void rule__JvmParameterizedTypeReference__Group_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1814:1: ( ( '>' ) )
            // InternalBehavioralInterface.g:1815:1: ( '>' )
            {
            // InternalBehavioralInterface.g:1815:1: ( '>' )
            // InternalBehavioralInterface.g:1816:2: '>'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getGreaterThanSignKeyword_1_3()); 
            }
            match(input,25,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getGreaterThanSignKeyword_1_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1__3__Impl"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1__4"
    // InternalBehavioralInterface.g:1825:1: rule__JvmParameterizedTypeReference__Group_1__4 : rule__JvmParameterizedTypeReference__Group_1__4__Impl ;
    public final void rule__JvmParameterizedTypeReference__Group_1__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1829:1: ( rule__JvmParameterizedTypeReference__Group_1__4__Impl )
            // InternalBehavioralInterface.g:1830:2: rule__JvmParameterizedTypeReference__Group_1__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__Group_1__4__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1__4"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1__4__Impl"
    // InternalBehavioralInterface.g:1836:1: rule__JvmParameterizedTypeReference__Group_1__4__Impl : ( ( rule__JvmParameterizedTypeReference__Group_1_4__0 )* ) ;
    public final void rule__JvmParameterizedTypeReference__Group_1__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1840:1: ( ( ( rule__JvmParameterizedTypeReference__Group_1_4__0 )* ) )
            // InternalBehavioralInterface.g:1841:1: ( ( rule__JvmParameterizedTypeReference__Group_1_4__0 )* )
            {
            // InternalBehavioralInterface.g:1841:1: ( ( rule__JvmParameterizedTypeReference__Group_1_4__0 )* )
            // InternalBehavioralInterface.g:1842:2: ( rule__JvmParameterizedTypeReference__Group_1_4__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getGroup_1_4()); 
            }
            // InternalBehavioralInterface.g:1843:2: ( rule__JvmParameterizedTypeReference__Group_1_4__0 )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==26) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // InternalBehavioralInterface.g:1843:3: rule__JvmParameterizedTypeReference__Group_1_4__0
            	    {
            	    pushFollow(FOLLOW_24);
            	    rule__JvmParameterizedTypeReference__Group_1_4__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getGroup_1_4()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1__4__Impl"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1_2__0"
    // InternalBehavioralInterface.g:1852:1: rule__JvmParameterizedTypeReference__Group_1_2__0 : rule__JvmParameterizedTypeReference__Group_1_2__0__Impl rule__JvmParameterizedTypeReference__Group_1_2__1 ;
    public final void rule__JvmParameterizedTypeReference__Group_1_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1856:1: ( rule__JvmParameterizedTypeReference__Group_1_2__0__Impl rule__JvmParameterizedTypeReference__Group_1_2__1 )
            // InternalBehavioralInterface.g:1857:2: rule__JvmParameterizedTypeReference__Group_1_2__0__Impl rule__JvmParameterizedTypeReference__Group_1_2__1
            {
            pushFollow(FOLLOW_21);
            rule__JvmParameterizedTypeReference__Group_1_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__Group_1_2__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1_2__0"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1_2__0__Impl"
    // InternalBehavioralInterface.g:1864:1: rule__JvmParameterizedTypeReference__Group_1_2__0__Impl : ( ',' ) ;
    public final void rule__JvmParameterizedTypeReference__Group_1_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1868:1: ( ( ',' ) )
            // InternalBehavioralInterface.g:1869:1: ( ',' )
            {
            // InternalBehavioralInterface.g:1869:1: ( ',' )
            // InternalBehavioralInterface.g:1870:2: ','
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getCommaKeyword_1_2_0()); 
            }
            match(input,20,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getCommaKeyword_1_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1_2__0__Impl"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1_2__1"
    // InternalBehavioralInterface.g:1879:1: rule__JvmParameterizedTypeReference__Group_1_2__1 : rule__JvmParameterizedTypeReference__Group_1_2__1__Impl ;
    public final void rule__JvmParameterizedTypeReference__Group_1_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1883:1: ( rule__JvmParameterizedTypeReference__Group_1_2__1__Impl )
            // InternalBehavioralInterface.g:1884:2: rule__JvmParameterizedTypeReference__Group_1_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__Group_1_2__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1_2__1"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1_2__1__Impl"
    // InternalBehavioralInterface.g:1890:1: rule__JvmParameterizedTypeReference__Group_1_2__1__Impl : ( ( rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_2_1 ) ) ;
    public final void rule__JvmParameterizedTypeReference__Group_1_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1894:1: ( ( ( rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_2_1 ) ) )
            // InternalBehavioralInterface.g:1895:1: ( ( rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_2_1 ) )
            {
            // InternalBehavioralInterface.g:1895:1: ( ( rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_2_1 ) )
            // InternalBehavioralInterface.g:1896:2: ( rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_2_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getArgumentsAssignment_1_2_1()); 
            }
            // InternalBehavioralInterface.g:1897:2: ( rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_2_1 )
            // InternalBehavioralInterface.g:1897:3: rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_2_1
            {
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_2_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getArgumentsAssignment_1_2_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1_2__1__Impl"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1_4__0"
    // InternalBehavioralInterface.g:1906:1: rule__JvmParameterizedTypeReference__Group_1_4__0 : rule__JvmParameterizedTypeReference__Group_1_4__0__Impl rule__JvmParameterizedTypeReference__Group_1_4__1 ;
    public final void rule__JvmParameterizedTypeReference__Group_1_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1910:1: ( rule__JvmParameterizedTypeReference__Group_1_4__0__Impl rule__JvmParameterizedTypeReference__Group_1_4__1 )
            // InternalBehavioralInterface.g:1911:2: rule__JvmParameterizedTypeReference__Group_1_4__0__Impl rule__JvmParameterizedTypeReference__Group_1_4__1
            {
            pushFollow(FOLLOW_4);
            rule__JvmParameterizedTypeReference__Group_1_4__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__Group_1_4__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1_4__0"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1_4__0__Impl"
    // InternalBehavioralInterface.g:1918:1: rule__JvmParameterizedTypeReference__Group_1_4__0__Impl : ( ( rule__JvmParameterizedTypeReference__Group_1_4_0__0 ) ) ;
    public final void rule__JvmParameterizedTypeReference__Group_1_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1922:1: ( ( ( rule__JvmParameterizedTypeReference__Group_1_4_0__0 ) ) )
            // InternalBehavioralInterface.g:1923:1: ( ( rule__JvmParameterizedTypeReference__Group_1_4_0__0 ) )
            {
            // InternalBehavioralInterface.g:1923:1: ( ( rule__JvmParameterizedTypeReference__Group_1_4_0__0 ) )
            // InternalBehavioralInterface.g:1924:2: ( rule__JvmParameterizedTypeReference__Group_1_4_0__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getGroup_1_4_0()); 
            }
            // InternalBehavioralInterface.g:1925:2: ( rule__JvmParameterizedTypeReference__Group_1_4_0__0 )
            // InternalBehavioralInterface.g:1925:3: rule__JvmParameterizedTypeReference__Group_1_4_0__0
            {
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__Group_1_4_0__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getGroup_1_4_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1_4__0__Impl"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1_4__1"
    // InternalBehavioralInterface.g:1933:1: rule__JvmParameterizedTypeReference__Group_1_4__1 : rule__JvmParameterizedTypeReference__Group_1_4__1__Impl rule__JvmParameterizedTypeReference__Group_1_4__2 ;
    public final void rule__JvmParameterizedTypeReference__Group_1_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1937:1: ( rule__JvmParameterizedTypeReference__Group_1_4__1__Impl rule__JvmParameterizedTypeReference__Group_1_4__2 )
            // InternalBehavioralInterface.g:1938:2: rule__JvmParameterizedTypeReference__Group_1_4__1__Impl rule__JvmParameterizedTypeReference__Group_1_4__2
            {
            pushFollow(FOLLOW_20);
            rule__JvmParameterizedTypeReference__Group_1_4__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__Group_1_4__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1_4__1"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1_4__1__Impl"
    // InternalBehavioralInterface.g:1945:1: rule__JvmParameterizedTypeReference__Group_1_4__1__Impl : ( ( rule__JvmParameterizedTypeReference__TypeAssignment_1_4_1 ) ) ;
    public final void rule__JvmParameterizedTypeReference__Group_1_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1949:1: ( ( ( rule__JvmParameterizedTypeReference__TypeAssignment_1_4_1 ) ) )
            // InternalBehavioralInterface.g:1950:1: ( ( rule__JvmParameterizedTypeReference__TypeAssignment_1_4_1 ) )
            {
            // InternalBehavioralInterface.g:1950:1: ( ( rule__JvmParameterizedTypeReference__TypeAssignment_1_4_1 ) )
            // InternalBehavioralInterface.g:1951:2: ( rule__JvmParameterizedTypeReference__TypeAssignment_1_4_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getTypeAssignment_1_4_1()); 
            }
            // InternalBehavioralInterface.g:1952:2: ( rule__JvmParameterizedTypeReference__TypeAssignment_1_4_1 )
            // InternalBehavioralInterface.g:1952:3: rule__JvmParameterizedTypeReference__TypeAssignment_1_4_1
            {
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__TypeAssignment_1_4_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getTypeAssignment_1_4_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1_4__1__Impl"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1_4__2"
    // InternalBehavioralInterface.g:1960:1: rule__JvmParameterizedTypeReference__Group_1_4__2 : rule__JvmParameterizedTypeReference__Group_1_4__2__Impl ;
    public final void rule__JvmParameterizedTypeReference__Group_1_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1964:1: ( rule__JvmParameterizedTypeReference__Group_1_4__2__Impl )
            // InternalBehavioralInterface.g:1965:2: rule__JvmParameterizedTypeReference__Group_1_4__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__Group_1_4__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1_4__2"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1_4__2__Impl"
    // InternalBehavioralInterface.g:1971:1: rule__JvmParameterizedTypeReference__Group_1_4__2__Impl : ( ( rule__JvmParameterizedTypeReference__Group_1_4_2__0 )? ) ;
    public final void rule__JvmParameterizedTypeReference__Group_1_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1975:1: ( ( ( rule__JvmParameterizedTypeReference__Group_1_4_2__0 )? ) )
            // InternalBehavioralInterface.g:1976:1: ( ( rule__JvmParameterizedTypeReference__Group_1_4_2__0 )? )
            {
            // InternalBehavioralInterface.g:1976:1: ( ( rule__JvmParameterizedTypeReference__Group_1_4_2__0 )? )
            // InternalBehavioralInterface.g:1977:2: ( rule__JvmParameterizedTypeReference__Group_1_4_2__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getGroup_1_4_2()); 
            }
            // InternalBehavioralInterface.g:1978:2: ( rule__JvmParameterizedTypeReference__Group_1_4_2__0 )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==24) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalBehavioralInterface.g:1978:3: rule__JvmParameterizedTypeReference__Group_1_4_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__JvmParameterizedTypeReference__Group_1_4_2__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getGroup_1_4_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1_4__2__Impl"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1_4_0__0"
    // InternalBehavioralInterface.g:1987:1: rule__JvmParameterizedTypeReference__Group_1_4_0__0 : rule__JvmParameterizedTypeReference__Group_1_4_0__0__Impl ;
    public final void rule__JvmParameterizedTypeReference__Group_1_4_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:1991:1: ( rule__JvmParameterizedTypeReference__Group_1_4_0__0__Impl )
            // InternalBehavioralInterface.g:1992:2: rule__JvmParameterizedTypeReference__Group_1_4_0__0__Impl
            {
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__Group_1_4_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1_4_0__0"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1_4_0__0__Impl"
    // InternalBehavioralInterface.g:1998:1: rule__JvmParameterizedTypeReference__Group_1_4_0__0__Impl : ( ( rule__JvmParameterizedTypeReference__Group_1_4_0_0__0 ) ) ;
    public final void rule__JvmParameterizedTypeReference__Group_1_4_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2002:1: ( ( ( rule__JvmParameterizedTypeReference__Group_1_4_0_0__0 ) ) )
            // InternalBehavioralInterface.g:2003:1: ( ( rule__JvmParameterizedTypeReference__Group_1_4_0_0__0 ) )
            {
            // InternalBehavioralInterface.g:2003:1: ( ( rule__JvmParameterizedTypeReference__Group_1_4_0_0__0 ) )
            // InternalBehavioralInterface.g:2004:2: ( rule__JvmParameterizedTypeReference__Group_1_4_0_0__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getGroup_1_4_0_0()); 
            }
            // InternalBehavioralInterface.g:2005:2: ( rule__JvmParameterizedTypeReference__Group_1_4_0_0__0 )
            // InternalBehavioralInterface.g:2005:3: rule__JvmParameterizedTypeReference__Group_1_4_0_0__0
            {
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__Group_1_4_0_0__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getGroup_1_4_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1_4_0__0__Impl"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1_4_0_0__0"
    // InternalBehavioralInterface.g:2014:1: rule__JvmParameterizedTypeReference__Group_1_4_0_0__0 : rule__JvmParameterizedTypeReference__Group_1_4_0_0__0__Impl rule__JvmParameterizedTypeReference__Group_1_4_0_0__1 ;
    public final void rule__JvmParameterizedTypeReference__Group_1_4_0_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2018:1: ( rule__JvmParameterizedTypeReference__Group_1_4_0_0__0__Impl rule__JvmParameterizedTypeReference__Group_1_4_0_0__1 )
            // InternalBehavioralInterface.g:2019:2: rule__JvmParameterizedTypeReference__Group_1_4_0_0__0__Impl rule__JvmParameterizedTypeReference__Group_1_4_0_0__1
            {
            pushFollow(FOLLOW_23);
            rule__JvmParameterizedTypeReference__Group_1_4_0_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__Group_1_4_0_0__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1_4_0_0__0"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1_4_0_0__0__Impl"
    // InternalBehavioralInterface.g:2026:1: rule__JvmParameterizedTypeReference__Group_1_4_0_0__0__Impl : ( () ) ;
    public final void rule__JvmParameterizedTypeReference__Group_1_4_0_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2030:1: ( ( () ) )
            // InternalBehavioralInterface.g:2031:1: ( () )
            {
            // InternalBehavioralInterface.g:2031:1: ( () )
            // InternalBehavioralInterface.g:2032:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getJvmInnerTypeReferenceOuterAction_1_4_0_0_0()); 
            }
            // InternalBehavioralInterface.g:2033:2: ()
            // InternalBehavioralInterface.g:2033:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getJvmInnerTypeReferenceOuterAction_1_4_0_0_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1_4_0_0__0__Impl"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1_4_0_0__1"
    // InternalBehavioralInterface.g:2041:1: rule__JvmParameterizedTypeReference__Group_1_4_0_0__1 : rule__JvmParameterizedTypeReference__Group_1_4_0_0__1__Impl ;
    public final void rule__JvmParameterizedTypeReference__Group_1_4_0_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2045:1: ( rule__JvmParameterizedTypeReference__Group_1_4_0_0__1__Impl )
            // InternalBehavioralInterface.g:2046:2: rule__JvmParameterizedTypeReference__Group_1_4_0_0__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__Group_1_4_0_0__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1_4_0_0__1"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1_4_0_0__1__Impl"
    // InternalBehavioralInterface.g:2052:1: rule__JvmParameterizedTypeReference__Group_1_4_0_0__1__Impl : ( '.' ) ;
    public final void rule__JvmParameterizedTypeReference__Group_1_4_0_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2056:1: ( ( '.' ) )
            // InternalBehavioralInterface.g:2057:1: ( '.' )
            {
            // InternalBehavioralInterface.g:2057:1: ( '.' )
            // InternalBehavioralInterface.g:2058:2: '.'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getFullStopKeyword_1_4_0_0_1()); 
            }
            match(input,26,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getFullStopKeyword_1_4_0_0_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1_4_0_0__1__Impl"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1_4_2__0"
    // InternalBehavioralInterface.g:2068:1: rule__JvmParameterizedTypeReference__Group_1_4_2__0 : rule__JvmParameterizedTypeReference__Group_1_4_2__0__Impl rule__JvmParameterizedTypeReference__Group_1_4_2__1 ;
    public final void rule__JvmParameterizedTypeReference__Group_1_4_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2072:1: ( rule__JvmParameterizedTypeReference__Group_1_4_2__0__Impl rule__JvmParameterizedTypeReference__Group_1_4_2__1 )
            // InternalBehavioralInterface.g:2073:2: rule__JvmParameterizedTypeReference__Group_1_4_2__0__Impl rule__JvmParameterizedTypeReference__Group_1_4_2__1
            {
            pushFollow(FOLLOW_21);
            rule__JvmParameterizedTypeReference__Group_1_4_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__Group_1_4_2__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1_4_2__0"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1_4_2__0__Impl"
    // InternalBehavioralInterface.g:2080:1: rule__JvmParameterizedTypeReference__Group_1_4_2__0__Impl : ( ( '<' ) ) ;
    public final void rule__JvmParameterizedTypeReference__Group_1_4_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2084:1: ( ( ( '<' ) ) )
            // InternalBehavioralInterface.g:2085:1: ( ( '<' ) )
            {
            // InternalBehavioralInterface.g:2085:1: ( ( '<' ) )
            // InternalBehavioralInterface.g:2086:2: ( '<' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getLessThanSignKeyword_1_4_2_0()); 
            }
            // InternalBehavioralInterface.g:2087:2: ( '<' )
            // InternalBehavioralInterface.g:2087:3: '<'
            {
            match(input,24,FOLLOW_2); if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getLessThanSignKeyword_1_4_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1_4_2__0__Impl"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1_4_2__1"
    // InternalBehavioralInterface.g:2095:1: rule__JvmParameterizedTypeReference__Group_1_4_2__1 : rule__JvmParameterizedTypeReference__Group_1_4_2__1__Impl rule__JvmParameterizedTypeReference__Group_1_4_2__2 ;
    public final void rule__JvmParameterizedTypeReference__Group_1_4_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2099:1: ( rule__JvmParameterizedTypeReference__Group_1_4_2__1__Impl rule__JvmParameterizedTypeReference__Group_1_4_2__2 )
            // InternalBehavioralInterface.g:2100:2: rule__JvmParameterizedTypeReference__Group_1_4_2__1__Impl rule__JvmParameterizedTypeReference__Group_1_4_2__2
            {
            pushFollow(FOLLOW_22);
            rule__JvmParameterizedTypeReference__Group_1_4_2__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__Group_1_4_2__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1_4_2__1"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1_4_2__1__Impl"
    // InternalBehavioralInterface.g:2107:1: rule__JvmParameterizedTypeReference__Group_1_4_2__1__Impl : ( ( rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_4_2_1 ) ) ;
    public final void rule__JvmParameterizedTypeReference__Group_1_4_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2111:1: ( ( ( rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_4_2_1 ) ) )
            // InternalBehavioralInterface.g:2112:1: ( ( rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_4_2_1 ) )
            {
            // InternalBehavioralInterface.g:2112:1: ( ( rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_4_2_1 ) )
            // InternalBehavioralInterface.g:2113:2: ( rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_4_2_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getArgumentsAssignment_1_4_2_1()); 
            }
            // InternalBehavioralInterface.g:2114:2: ( rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_4_2_1 )
            // InternalBehavioralInterface.g:2114:3: rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_4_2_1
            {
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_4_2_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getArgumentsAssignment_1_4_2_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1_4_2__1__Impl"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1_4_2__2"
    // InternalBehavioralInterface.g:2122:1: rule__JvmParameterizedTypeReference__Group_1_4_2__2 : rule__JvmParameterizedTypeReference__Group_1_4_2__2__Impl rule__JvmParameterizedTypeReference__Group_1_4_2__3 ;
    public final void rule__JvmParameterizedTypeReference__Group_1_4_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2126:1: ( rule__JvmParameterizedTypeReference__Group_1_4_2__2__Impl rule__JvmParameterizedTypeReference__Group_1_4_2__3 )
            // InternalBehavioralInterface.g:2127:2: rule__JvmParameterizedTypeReference__Group_1_4_2__2__Impl rule__JvmParameterizedTypeReference__Group_1_4_2__3
            {
            pushFollow(FOLLOW_22);
            rule__JvmParameterizedTypeReference__Group_1_4_2__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__Group_1_4_2__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1_4_2__2"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1_4_2__2__Impl"
    // InternalBehavioralInterface.g:2134:1: rule__JvmParameterizedTypeReference__Group_1_4_2__2__Impl : ( ( rule__JvmParameterizedTypeReference__Group_1_4_2_2__0 )* ) ;
    public final void rule__JvmParameterizedTypeReference__Group_1_4_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2138:1: ( ( ( rule__JvmParameterizedTypeReference__Group_1_4_2_2__0 )* ) )
            // InternalBehavioralInterface.g:2139:1: ( ( rule__JvmParameterizedTypeReference__Group_1_4_2_2__0 )* )
            {
            // InternalBehavioralInterface.g:2139:1: ( ( rule__JvmParameterizedTypeReference__Group_1_4_2_2__0 )* )
            // InternalBehavioralInterface.g:2140:2: ( rule__JvmParameterizedTypeReference__Group_1_4_2_2__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getGroup_1_4_2_2()); 
            }
            // InternalBehavioralInterface.g:2141:2: ( rule__JvmParameterizedTypeReference__Group_1_4_2_2__0 )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==20) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalBehavioralInterface.g:2141:3: rule__JvmParameterizedTypeReference__Group_1_4_2_2__0
            	    {
            	    pushFollow(FOLLOW_12);
            	    rule__JvmParameterizedTypeReference__Group_1_4_2_2__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getGroup_1_4_2_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1_4_2__2__Impl"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1_4_2__3"
    // InternalBehavioralInterface.g:2149:1: rule__JvmParameterizedTypeReference__Group_1_4_2__3 : rule__JvmParameterizedTypeReference__Group_1_4_2__3__Impl ;
    public final void rule__JvmParameterizedTypeReference__Group_1_4_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2153:1: ( rule__JvmParameterizedTypeReference__Group_1_4_2__3__Impl )
            // InternalBehavioralInterface.g:2154:2: rule__JvmParameterizedTypeReference__Group_1_4_2__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__Group_1_4_2__3__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1_4_2__3"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1_4_2__3__Impl"
    // InternalBehavioralInterface.g:2160:1: rule__JvmParameterizedTypeReference__Group_1_4_2__3__Impl : ( '>' ) ;
    public final void rule__JvmParameterizedTypeReference__Group_1_4_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2164:1: ( ( '>' ) )
            // InternalBehavioralInterface.g:2165:1: ( '>' )
            {
            // InternalBehavioralInterface.g:2165:1: ( '>' )
            // InternalBehavioralInterface.g:2166:2: '>'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getGreaterThanSignKeyword_1_4_2_3()); 
            }
            match(input,25,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getGreaterThanSignKeyword_1_4_2_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1_4_2__3__Impl"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1_4_2_2__0"
    // InternalBehavioralInterface.g:2176:1: rule__JvmParameterizedTypeReference__Group_1_4_2_2__0 : rule__JvmParameterizedTypeReference__Group_1_4_2_2__0__Impl rule__JvmParameterizedTypeReference__Group_1_4_2_2__1 ;
    public final void rule__JvmParameterizedTypeReference__Group_1_4_2_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2180:1: ( rule__JvmParameterizedTypeReference__Group_1_4_2_2__0__Impl rule__JvmParameterizedTypeReference__Group_1_4_2_2__1 )
            // InternalBehavioralInterface.g:2181:2: rule__JvmParameterizedTypeReference__Group_1_4_2_2__0__Impl rule__JvmParameterizedTypeReference__Group_1_4_2_2__1
            {
            pushFollow(FOLLOW_21);
            rule__JvmParameterizedTypeReference__Group_1_4_2_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__Group_1_4_2_2__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1_4_2_2__0"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1_4_2_2__0__Impl"
    // InternalBehavioralInterface.g:2188:1: rule__JvmParameterizedTypeReference__Group_1_4_2_2__0__Impl : ( ',' ) ;
    public final void rule__JvmParameterizedTypeReference__Group_1_4_2_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2192:1: ( ( ',' ) )
            // InternalBehavioralInterface.g:2193:1: ( ',' )
            {
            // InternalBehavioralInterface.g:2193:1: ( ',' )
            // InternalBehavioralInterface.g:2194:2: ','
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getCommaKeyword_1_4_2_2_0()); 
            }
            match(input,20,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getCommaKeyword_1_4_2_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1_4_2_2__0__Impl"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1_4_2_2__1"
    // InternalBehavioralInterface.g:2203:1: rule__JvmParameterizedTypeReference__Group_1_4_2_2__1 : rule__JvmParameterizedTypeReference__Group_1_4_2_2__1__Impl ;
    public final void rule__JvmParameterizedTypeReference__Group_1_4_2_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2207:1: ( rule__JvmParameterizedTypeReference__Group_1_4_2_2__1__Impl )
            // InternalBehavioralInterface.g:2208:2: rule__JvmParameterizedTypeReference__Group_1_4_2_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__Group_1_4_2_2__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1_4_2_2__1"


    // $ANTLR start "rule__JvmParameterizedTypeReference__Group_1_4_2_2__1__Impl"
    // InternalBehavioralInterface.g:2214:1: rule__JvmParameterizedTypeReference__Group_1_4_2_2__1__Impl : ( ( rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_4_2_2_1 ) ) ;
    public final void rule__JvmParameterizedTypeReference__Group_1_4_2_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2218:1: ( ( ( rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_4_2_2_1 ) ) )
            // InternalBehavioralInterface.g:2219:1: ( ( rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_4_2_2_1 ) )
            {
            // InternalBehavioralInterface.g:2219:1: ( ( rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_4_2_2_1 ) )
            // InternalBehavioralInterface.g:2220:2: ( rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_4_2_2_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getArgumentsAssignment_1_4_2_2_1()); 
            }
            // InternalBehavioralInterface.g:2221:2: ( rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_4_2_2_1 )
            // InternalBehavioralInterface.g:2221:3: rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_4_2_2_1
            {
            pushFollow(FOLLOW_2);
            rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_4_2_2_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getArgumentsAssignment_1_4_2_2_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__Group_1_4_2_2__1__Impl"


    // $ANTLR start "rule__JvmWildcardTypeReference__Group__0"
    // InternalBehavioralInterface.g:2230:1: rule__JvmWildcardTypeReference__Group__0 : rule__JvmWildcardTypeReference__Group__0__Impl rule__JvmWildcardTypeReference__Group__1 ;
    public final void rule__JvmWildcardTypeReference__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2234:1: ( rule__JvmWildcardTypeReference__Group__0__Impl rule__JvmWildcardTypeReference__Group__1 )
            // InternalBehavioralInterface.g:2235:2: rule__JvmWildcardTypeReference__Group__0__Impl rule__JvmWildcardTypeReference__Group__1
            {
            pushFollow(FOLLOW_21);
            rule__JvmWildcardTypeReference__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__JvmWildcardTypeReference__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmWildcardTypeReference__Group__0"


    // $ANTLR start "rule__JvmWildcardTypeReference__Group__0__Impl"
    // InternalBehavioralInterface.g:2242:1: rule__JvmWildcardTypeReference__Group__0__Impl : ( () ) ;
    public final void rule__JvmWildcardTypeReference__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2246:1: ( ( () ) )
            // InternalBehavioralInterface.g:2247:1: ( () )
            {
            // InternalBehavioralInterface.g:2247:1: ( () )
            // InternalBehavioralInterface.g:2248:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmWildcardTypeReferenceAccess().getJvmWildcardTypeReferenceAction_0()); 
            }
            // InternalBehavioralInterface.g:2249:2: ()
            // InternalBehavioralInterface.g:2249:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmWildcardTypeReferenceAccess().getJvmWildcardTypeReferenceAction_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmWildcardTypeReference__Group__0__Impl"


    // $ANTLR start "rule__JvmWildcardTypeReference__Group__1"
    // InternalBehavioralInterface.g:2257:1: rule__JvmWildcardTypeReference__Group__1 : rule__JvmWildcardTypeReference__Group__1__Impl rule__JvmWildcardTypeReference__Group__2 ;
    public final void rule__JvmWildcardTypeReference__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2261:1: ( rule__JvmWildcardTypeReference__Group__1__Impl rule__JvmWildcardTypeReference__Group__2 )
            // InternalBehavioralInterface.g:2262:2: rule__JvmWildcardTypeReference__Group__1__Impl rule__JvmWildcardTypeReference__Group__2
            {
            pushFollow(FOLLOW_25);
            rule__JvmWildcardTypeReference__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__JvmWildcardTypeReference__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmWildcardTypeReference__Group__1"


    // $ANTLR start "rule__JvmWildcardTypeReference__Group__1__Impl"
    // InternalBehavioralInterface.g:2269:1: rule__JvmWildcardTypeReference__Group__1__Impl : ( '?' ) ;
    public final void rule__JvmWildcardTypeReference__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2273:1: ( ( '?' ) )
            // InternalBehavioralInterface.g:2274:1: ( '?' )
            {
            // InternalBehavioralInterface.g:2274:1: ( '?' )
            // InternalBehavioralInterface.g:2275:2: '?'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmWildcardTypeReferenceAccess().getQuestionMarkKeyword_1()); 
            }
            match(input,27,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmWildcardTypeReferenceAccess().getQuestionMarkKeyword_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmWildcardTypeReference__Group__1__Impl"


    // $ANTLR start "rule__JvmWildcardTypeReference__Group__2"
    // InternalBehavioralInterface.g:2284:1: rule__JvmWildcardTypeReference__Group__2 : rule__JvmWildcardTypeReference__Group__2__Impl ;
    public final void rule__JvmWildcardTypeReference__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2288:1: ( rule__JvmWildcardTypeReference__Group__2__Impl )
            // InternalBehavioralInterface.g:2289:2: rule__JvmWildcardTypeReference__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__JvmWildcardTypeReference__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmWildcardTypeReference__Group__2"


    // $ANTLR start "rule__JvmWildcardTypeReference__Group__2__Impl"
    // InternalBehavioralInterface.g:2295:1: rule__JvmWildcardTypeReference__Group__2__Impl : ( ( rule__JvmWildcardTypeReference__Alternatives_2 )? ) ;
    public final void rule__JvmWildcardTypeReference__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2299:1: ( ( ( rule__JvmWildcardTypeReference__Alternatives_2 )? ) )
            // InternalBehavioralInterface.g:2300:1: ( ( rule__JvmWildcardTypeReference__Alternatives_2 )? )
            {
            // InternalBehavioralInterface.g:2300:1: ( ( rule__JvmWildcardTypeReference__Alternatives_2 )? )
            // InternalBehavioralInterface.g:2301:2: ( rule__JvmWildcardTypeReference__Alternatives_2 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmWildcardTypeReferenceAccess().getAlternatives_2()); 
            }
            // InternalBehavioralInterface.g:2302:2: ( rule__JvmWildcardTypeReference__Alternatives_2 )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==28||LA22_0==30) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalBehavioralInterface.g:2302:3: rule__JvmWildcardTypeReference__Alternatives_2
                    {
                    pushFollow(FOLLOW_2);
                    rule__JvmWildcardTypeReference__Alternatives_2();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmWildcardTypeReferenceAccess().getAlternatives_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmWildcardTypeReference__Group__2__Impl"


    // $ANTLR start "rule__JvmWildcardTypeReference__Group_2_0__0"
    // InternalBehavioralInterface.g:2311:1: rule__JvmWildcardTypeReference__Group_2_0__0 : rule__JvmWildcardTypeReference__Group_2_0__0__Impl rule__JvmWildcardTypeReference__Group_2_0__1 ;
    public final void rule__JvmWildcardTypeReference__Group_2_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2315:1: ( rule__JvmWildcardTypeReference__Group_2_0__0__Impl rule__JvmWildcardTypeReference__Group_2_0__1 )
            // InternalBehavioralInterface.g:2316:2: rule__JvmWildcardTypeReference__Group_2_0__0__Impl rule__JvmWildcardTypeReference__Group_2_0__1
            {
            pushFollow(FOLLOW_26);
            rule__JvmWildcardTypeReference__Group_2_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__JvmWildcardTypeReference__Group_2_0__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmWildcardTypeReference__Group_2_0__0"


    // $ANTLR start "rule__JvmWildcardTypeReference__Group_2_0__0__Impl"
    // InternalBehavioralInterface.g:2323:1: rule__JvmWildcardTypeReference__Group_2_0__0__Impl : ( ( rule__JvmWildcardTypeReference__ConstraintsAssignment_2_0_0 ) ) ;
    public final void rule__JvmWildcardTypeReference__Group_2_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2327:1: ( ( ( rule__JvmWildcardTypeReference__ConstraintsAssignment_2_0_0 ) ) )
            // InternalBehavioralInterface.g:2328:1: ( ( rule__JvmWildcardTypeReference__ConstraintsAssignment_2_0_0 ) )
            {
            // InternalBehavioralInterface.g:2328:1: ( ( rule__JvmWildcardTypeReference__ConstraintsAssignment_2_0_0 ) )
            // InternalBehavioralInterface.g:2329:2: ( rule__JvmWildcardTypeReference__ConstraintsAssignment_2_0_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmWildcardTypeReferenceAccess().getConstraintsAssignment_2_0_0()); 
            }
            // InternalBehavioralInterface.g:2330:2: ( rule__JvmWildcardTypeReference__ConstraintsAssignment_2_0_0 )
            // InternalBehavioralInterface.g:2330:3: rule__JvmWildcardTypeReference__ConstraintsAssignment_2_0_0
            {
            pushFollow(FOLLOW_2);
            rule__JvmWildcardTypeReference__ConstraintsAssignment_2_0_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmWildcardTypeReferenceAccess().getConstraintsAssignment_2_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmWildcardTypeReference__Group_2_0__0__Impl"


    // $ANTLR start "rule__JvmWildcardTypeReference__Group_2_0__1"
    // InternalBehavioralInterface.g:2338:1: rule__JvmWildcardTypeReference__Group_2_0__1 : rule__JvmWildcardTypeReference__Group_2_0__1__Impl ;
    public final void rule__JvmWildcardTypeReference__Group_2_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2342:1: ( rule__JvmWildcardTypeReference__Group_2_0__1__Impl )
            // InternalBehavioralInterface.g:2343:2: rule__JvmWildcardTypeReference__Group_2_0__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__JvmWildcardTypeReference__Group_2_0__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmWildcardTypeReference__Group_2_0__1"


    // $ANTLR start "rule__JvmWildcardTypeReference__Group_2_0__1__Impl"
    // InternalBehavioralInterface.g:2349:1: rule__JvmWildcardTypeReference__Group_2_0__1__Impl : ( ( rule__JvmWildcardTypeReference__ConstraintsAssignment_2_0_1 )* ) ;
    public final void rule__JvmWildcardTypeReference__Group_2_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2353:1: ( ( ( rule__JvmWildcardTypeReference__ConstraintsAssignment_2_0_1 )* ) )
            // InternalBehavioralInterface.g:2354:1: ( ( rule__JvmWildcardTypeReference__ConstraintsAssignment_2_0_1 )* )
            {
            // InternalBehavioralInterface.g:2354:1: ( ( rule__JvmWildcardTypeReference__ConstraintsAssignment_2_0_1 )* )
            // InternalBehavioralInterface.g:2355:2: ( rule__JvmWildcardTypeReference__ConstraintsAssignment_2_0_1 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmWildcardTypeReferenceAccess().getConstraintsAssignment_2_0_1()); 
            }
            // InternalBehavioralInterface.g:2356:2: ( rule__JvmWildcardTypeReference__ConstraintsAssignment_2_0_1 )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==29) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // InternalBehavioralInterface.g:2356:3: rule__JvmWildcardTypeReference__ConstraintsAssignment_2_0_1
            	    {
            	    pushFollow(FOLLOW_27);
            	    rule__JvmWildcardTypeReference__ConstraintsAssignment_2_0_1();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmWildcardTypeReferenceAccess().getConstraintsAssignment_2_0_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmWildcardTypeReference__Group_2_0__1__Impl"


    // $ANTLR start "rule__JvmWildcardTypeReference__Group_2_1__0"
    // InternalBehavioralInterface.g:2365:1: rule__JvmWildcardTypeReference__Group_2_1__0 : rule__JvmWildcardTypeReference__Group_2_1__0__Impl rule__JvmWildcardTypeReference__Group_2_1__1 ;
    public final void rule__JvmWildcardTypeReference__Group_2_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2369:1: ( rule__JvmWildcardTypeReference__Group_2_1__0__Impl rule__JvmWildcardTypeReference__Group_2_1__1 )
            // InternalBehavioralInterface.g:2370:2: rule__JvmWildcardTypeReference__Group_2_1__0__Impl rule__JvmWildcardTypeReference__Group_2_1__1
            {
            pushFollow(FOLLOW_26);
            rule__JvmWildcardTypeReference__Group_2_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__JvmWildcardTypeReference__Group_2_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmWildcardTypeReference__Group_2_1__0"


    // $ANTLR start "rule__JvmWildcardTypeReference__Group_2_1__0__Impl"
    // InternalBehavioralInterface.g:2377:1: rule__JvmWildcardTypeReference__Group_2_1__0__Impl : ( ( rule__JvmWildcardTypeReference__ConstraintsAssignment_2_1_0 ) ) ;
    public final void rule__JvmWildcardTypeReference__Group_2_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2381:1: ( ( ( rule__JvmWildcardTypeReference__ConstraintsAssignment_2_1_0 ) ) )
            // InternalBehavioralInterface.g:2382:1: ( ( rule__JvmWildcardTypeReference__ConstraintsAssignment_2_1_0 ) )
            {
            // InternalBehavioralInterface.g:2382:1: ( ( rule__JvmWildcardTypeReference__ConstraintsAssignment_2_1_0 ) )
            // InternalBehavioralInterface.g:2383:2: ( rule__JvmWildcardTypeReference__ConstraintsAssignment_2_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmWildcardTypeReferenceAccess().getConstraintsAssignment_2_1_0()); 
            }
            // InternalBehavioralInterface.g:2384:2: ( rule__JvmWildcardTypeReference__ConstraintsAssignment_2_1_0 )
            // InternalBehavioralInterface.g:2384:3: rule__JvmWildcardTypeReference__ConstraintsAssignment_2_1_0
            {
            pushFollow(FOLLOW_2);
            rule__JvmWildcardTypeReference__ConstraintsAssignment_2_1_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmWildcardTypeReferenceAccess().getConstraintsAssignment_2_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmWildcardTypeReference__Group_2_1__0__Impl"


    // $ANTLR start "rule__JvmWildcardTypeReference__Group_2_1__1"
    // InternalBehavioralInterface.g:2392:1: rule__JvmWildcardTypeReference__Group_2_1__1 : rule__JvmWildcardTypeReference__Group_2_1__1__Impl ;
    public final void rule__JvmWildcardTypeReference__Group_2_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2396:1: ( rule__JvmWildcardTypeReference__Group_2_1__1__Impl )
            // InternalBehavioralInterface.g:2397:2: rule__JvmWildcardTypeReference__Group_2_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__JvmWildcardTypeReference__Group_2_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmWildcardTypeReference__Group_2_1__1"


    // $ANTLR start "rule__JvmWildcardTypeReference__Group_2_1__1__Impl"
    // InternalBehavioralInterface.g:2403:1: rule__JvmWildcardTypeReference__Group_2_1__1__Impl : ( ( rule__JvmWildcardTypeReference__ConstraintsAssignment_2_1_1 )* ) ;
    public final void rule__JvmWildcardTypeReference__Group_2_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2407:1: ( ( ( rule__JvmWildcardTypeReference__ConstraintsAssignment_2_1_1 )* ) )
            // InternalBehavioralInterface.g:2408:1: ( ( rule__JvmWildcardTypeReference__ConstraintsAssignment_2_1_1 )* )
            {
            // InternalBehavioralInterface.g:2408:1: ( ( rule__JvmWildcardTypeReference__ConstraintsAssignment_2_1_1 )* )
            // InternalBehavioralInterface.g:2409:2: ( rule__JvmWildcardTypeReference__ConstraintsAssignment_2_1_1 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmWildcardTypeReferenceAccess().getConstraintsAssignment_2_1_1()); 
            }
            // InternalBehavioralInterface.g:2410:2: ( rule__JvmWildcardTypeReference__ConstraintsAssignment_2_1_1 )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==29) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // InternalBehavioralInterface.g:2410:3: rule__JvmWildcardTypeReference__ConstraintsAssignment_2_1_1
            	    {
            	    pushFollow(FOLLOW_27);
            	    rule__JvmWildcardTypeReference__ConstraintsAssignment_2_1_1();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmWildcardTypeReferenceAccess().getConstraintsAssignment_2_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmWildcardTypeReference__Group_2_1__1__Impl"


    // $ANTLR start "rule__JvmUpperBound__Group__0"
    // InternalBehavioralInterface.g:2419:1: rule__JvmUpperBound__Group__0 : rule__JvmUpperBound__Group__0__Impl rule__JvmUpperBound__Group__1 ;
    public final void rule__JvmUpperBound__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2423:1: ( rule__JvmUpperBound__Group__0__Impl rule__JvmUpperBound__Group__1 )
            // InternalBehavioralInterface.g:2424:2: rule__JvmUpperBound__Group__0__Impl rule__JvmUpperBound__Group__1
            {
            pushFollow(FOLLOW_17);
            rule__JvmUpperBound__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__JvmUpperBound__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmUpperBound__Group__0"


    // $ANTLR start "rule__JvmUpperBound__Group__0__Impl"
    // InternalBehavioralInterface.g:2431:1: rule__JvmUpperBound__Group__0__Impl : ( 'extends' ) ;
    public final void rule__JvmUpperBound__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2435:1: ( ( 'extends' ) )
            // InternalBehavioralInterface.g:2436:1: ( 'extends' )
            {
            // InternalBehavioralInterface.g:2436:1: ( 'extends' )
            // InternalBehavioralInterface.g:2437:2: 'extends'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmUpperBoundAccess().getExtendsKeyword_0()); 
            }
            match(input,28,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmUpperBoundAccess().getExtendsKeyword_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmUpperBound__Group__0__Impl"


    // $ANTLR start "rule__JvmUpperBound__Group__1"
    // InternalBehavioralInterface.g:2446:1: rule__JvmUpperBound__Group__1 : rule__JvmUpperBound__Group__1__Impl ;
    public final void rule__JvmUpperBound__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2450:1: ( rule__JvmUpperBound__Group__1__Impl )
            // InternalBehavioralInterface.g:2451:2: rule__JvmUpperBound__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__JvmUpperBound__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmUpperBound__Group__1"


    // $ANTLR start "rule__JvmUpperBound__Group__1__Impl"
    // InternalBehavioralInterface.g:2457:1: rule__JvmUpperBound__Group__1__Impl : ( ( rule__JvmUpperBound__TypeReferenceAssignment_1 ) ) ;
    public final void rule__JvmUpperBound__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2461:1: ( ( ( rule__JvmUpperBound__TypeReferenceAssignment_1 ) ) )
            // InternalBehavioralInterface.g:2462:1: ( ( rule__JvmUpperBound__TypeReferenceAssignment_1 ) )
            {
            // InternalBehavioralInterface.g:2462:1: ( ( rule__JvmUpperBound__TypeReferenceAssignment_1 ) )
            // InternalBehavioralInterface.g:2463:2: ( rule__JvmUpperBound__TypeReferenceAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmUpperBoundAccess().getTypeReferenceAssignment_1()); 
            }
            // InternalBehavioralInterface.g:2464:2: ( rule__JvmUpperBound__TypeReferenceAssignment_1 )
            // InternalBehavioralInterface.g:2464:3: rule__JvmUpperBound__TypeReferenceAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__JvmUpperBound__TypeReferenceAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmUpperBoundAccess().getTypeReferenceAssignment_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmUpperBound__Group__1__Impl"


    // $ANTLR start "rule__JvmUpperBoundAnded__Group__0"
    // InternalBehavioralInterface.g:2473:1: rule__JvmUpperBoundAnded__Group__0 : rule__JvmUpperBoundAnded__Group__0__Impl rule__JvmUpperBoundAnded__Group__1 ;
    public final void rule__JvmUpperBoundAnded__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2477:1: ( rule__JvmUpperBoundAnded__Group__0__Impl rule__JvmUpperBoundAnded__Group__1 )
            // InternalBehavioralInterface.g:2478:2: rule__JvmUpperBoundAnded__Group__0__Impl rule__JvmUpperBoundAnded__Group__1
            {
            pushFollow(FOLLOW_17);
            rule__JvmUpperBoundAnded__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__JvmUpperBoundAnded__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmUpperBoundAnded__Group__0"


    // $ANTLR start "rule__JvmUpperBoundAnded__Group__0__Impl"
    // InternalBehavioralInterface.g:2485:1: rule__JvmUpperBoundAnded__Group__0__Impl : ( '&' ) ;
    public final void rule__JvmUpperBoundAnded__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2489:1: ( ( '&' ) )
            // InternalBehavioralInterface.g:2490:1: ( '&' )
            {
            // InternalBehavioralInterface.g:2490:1: ( '&' )
            // InternalBehavioralInterface.g:2491:2: '&'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmUpperBoundAndedAccess().getAmpersandKeyword_0()); 
            }
            match(input,29,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmUpperBoundAndedAccess().getAmpersandKeyword_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmUpperBoundAnded__Group__0__Impl"


    // $ANTLR start "rule__JvmUpperBoundAnded__Group__1"
    // InternalBehavioralInterface.g:2500:1: rule__JvmUpperBoundAnded__Group__1 : rule__JvmUpperBoundAnded__Group__1__Impl ;
    public final void rule__JvmUpperBoundAnded__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2504:1: ( rule__JvmUpperBoundAnded__Group__1__Impl )
            // InternalBehavioralInterface.g:2505:2: rule__JvmUpperBoundAnded__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__JvmUpperBoundAnded__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmUpperBoundAnded__Group__1"


    // $ANTLR start "rule__JvmUpperBoundAnded__Group__1__Impl"
    // InternalBehavioralInterface.g:2511:1: rule__JvmUpperBoundAnded__Group__1__Impl : ( ( rule__JvmUpperBoundAnded__TypeReferenceAssignment_1 ) ) ;
    public final void rule__JvmUpperBoundAnded__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2515:1: ( ( ( rule__JvmUpperBoundAnded__TypeReferenceAssignment_1 ) ) )
            // InternalBehavioralInterface.g:2516:1: ( ( rule__JvmUpperBoundAnded__TypeReferenceAssignment_1 ) )
            {
            // InternalBehavioralInterface.g:2516:1: ( ( rule__JvmUpperBoundAnded__TypeReferenceAssignment_1 ) )
            // InternalBehavioralInterface.g:2517:2: ( rule__JvmUpperBoundAnded__TypeReferenceAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmUpperBoundAndedAccess().getTypeReferenceAssignment_1()); 
            }
            // InternalBehavioralInterface.g:2518:2: ( rule__JvmUpperBoundAnded__TypeReferenceAssignment_1 )
            // InternalBehavioralInterface.g:2518:3: rule__JvmUpperBoundAnded__TypeReferenceAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__JvmUpperBoundAnded__TypeReferenceAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmUpperBoundAndedAccess().getTypeReferenceAssignment_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmUpperBoundAnded__Group__1__Impl"


    // $ANTLR start "rule__JvmLowerBound__Group__0"
    // InternalBehavioralInterface.g:2527:1: rule__JvmLowerBound__Group__0 : rule__JvmLowerBound__Group__0__Impl rule__JvmLowerBound__Group__1 ;
    public final void rule__JvmLowerBound__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2531:1: ( rule__JvmLowerBound__Group__0__Impl rule__JvmLowerBound__Group__1 )
            // InternalBehavioralInterface.g:2532:2: rule__JvmLowerBound__Group__0__Impl rule__JvmLowerBound__Group__1
            {
            pushFollow(FOLLOW_17);
            rule__JvmLowerBound__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__JvmLowerBound__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmLowerBound__Group__0"


    // $ANTLR start "rule__JvmLowerBound__Group__0__Impl"
    // InternalBehavioralInterface.g:2539:1: rule__JvmLowerBound__Group__0__Impl : ( 'super' ) ;
    public final void rule__JvmLowerBound__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2543:1: ( ( 'super' ) )
            // InternalBehavioralInterface.g:2544:1: ( 'super' )
            {
            // InternalBehavioralInterface.g:2544:1: ( 'super' )
            // InternalBehavioralInterface.g:2545:2: 'super'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmLowerBoundAccess().getSuperKeyword_0()); 
            }
            match(input,30,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmLowerBoundAccess().getSuperKeyword_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmLowerBound__Group__0__Impl"


    // $ANTLR start "rule__JvmLowerBound__Group__1"
    // InternalBehavioralInterface.g:2554:1: rule__JvmLowerBound__Group__1 : rule__JvmLowerBound__Group__1__Impl ;
    public final void rule__JvmLowerBound__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2558:1: ( rule__JvmLowerBound__Group__1__Impl )
            // InternalBehavioralInterface.g:2559:2: rule__JvmLowerBound__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__JvmLowerBound__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmLowerBound__Group__1"


    // $ANTLR start "rule__JvmLowerBound__Group__1__Impl"
    // InternalBehavioralInterface.g:2565:1: rule__JvmLowerBound__Group__1__Impl : ( ( rule__JvmLowerBound__TypeReferenceAssignment_1 ) ) ;
    public final void rule__JvmLowerBound__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2569:1: ( ( ( rule__JvmLowerBound__TypeReferenceAssignment_1 ) ) )
            // InternalBehavioralInterface.g:2570:1: ( ( rule__JvmLowerBound__TypeReferenceAssignment_1 ) )
            {
            // InternalBehavioralInterface.g:2570:1: ( ( rule__JvmLowerBound__TypeReferenceAssignment_1 ) )
            // InternalBehavioralInterface.g:2571:2: ( rule__JvmLowerBound__TypeReferenceAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmLowerBoundAccess().getTypeReferenceAssignment_1()); 
            }
            // InternalBehavioralInterface.g:2572:2: ( rule__JvmLowerBound__TypeReferenceAssignment_1 )
            // InternalBehavioralInterface.g:2572:3: rule__JvmLowerBound__TypeReferenceAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__JvmLowerBound__TypeReferenceAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmLowerBoundAccess().getTypeReferenceAssignment_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmLowerBound__Group__1__Impl"


    // $ANTLR start "rule__JvmLowerBoundAnded__Group__0"
    // InternalBehavioralInterface.g:2581:1: rule__JvmLowerBoundAnded__Group__0 : rule__JvmLowerBoundAnded__Group__0__Impl rule__JvmLowerBoundAnded__Group__1 ;
    public final void rule__JvmLowerBoundAnded__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2585:1: ( rule__JvmLowerBoundAnded__Group__0__Impl rule__JvmLowerBoundAnded__Group__1 )
            // InternalBehavioralInterface.g:2586:2: rule__JvmLowerBoundAnded__Group__0__Impl rule__JvmLowerBoundAnded__Group__1
            {
            pushFollow(FOLLOW_17);
            rule__JvmLowerBoundAnded__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__JvmLowerBoundAnded__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmLowerBoundAnded__Group__0"


    // $ANTLR start "rule__JvmLowerBoundAnded__Group__0__Impl"
    // InternalBehavioralInterface.g:2593:1: rule__JvmLowerBoundAnded__Group__0__Impl : ( '&' ) ;
    public final void rule__JvmLowerBoundAnded__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2597:1: ( ( '&' ) )
            // InternalBehavioralInterface.g:2598:1: ( '&' )
            {
            // InternalBehavioralInterface.g:2598:1: ( '&' )
            // InternalBehavioralInterface.g:2599:2: '&'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmLowerBoundAndedAccess().getAmpersandKeyword_0()); 
            }
            match(input,29,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmLowerBoundAndedAccess().getAmpersandKeyword_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmLowerBoundAnded__Group__0__Impl"


    // $ANTLR start "rule__JvmLowerBoundAnded__Group__1"
    // InternalBehavioralInterface.g:2608:1: rule__JvmLowerBoundAnded__Group__1 : rule__JvmLowerBoundAnded__Group__1__Impl ;
    public final void rule__JvmLowerBoundAnded__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2612:1: ( rule__JvmLowerBoundAnded__Group__1__Impl )
            // InternalBehavioralInterface.g:2613:2: rule__JvmLowerBoundAnded__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__JvmLowerBoundAnded__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmLowerBoundAnded__Group__1"


    // $ANTLR start "rule__JvmLowerBoundAnded__Group__1__Impl"
    // InternalBehavioralInterface.g:2619:1: rule__JvmLowerBoundAnded__Group__1__Impl : ( ( rule__JvmLowerBoundAnded__TypeReferenceAssignment_1 ) ) ;
    public final void rule__JvmLowerBoundAnded__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2623:1: ( ( ( rule__JvmLowerBoundAnded__TypeReferenceAssignment_1 ) ) )
            // InternalBehavioralInterface.g:2624:1: ( ( rule__JvmLowerBoundAnded__TypeReferenceAssignment_1 ) )
            {
            // InternalBehavioralInterface.g:2624:1: ( ( rule__JvmLowerBoundAnded__TypeReferenceAssignment_1 ) )
            // InternalBehavioralInterface.g:2625:2: ( rule__JvmLowerBoundAnded__TypeReferenceAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmLowerBoundAndedAccess().getTypeReferenceAssignment_1()); 
            }
            // InternalBehavioralInterface.g:2626:2: ( rule__JvmLowerBoundAnded__TypeReferenceAssignment_1 )
            // InternalBehavioralInterface.g:2626:3: rule__JvmLowerBoundAnded__TypeReferenceAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__JvmLowerBoundAnded__TypeReferenceAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmLowerBoundAndedAccess().getTypeReferenceAssignment_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmLowerBoundAnded__Group__1__Impl"


    // $ANTLR start "rule__QualifiedName__Group__0"
    // InternalBehavioralInterface.g:2635:1: rule__QualifiedName__Group__0 : rule__QualifiedName__Group__0__Impl rule__QualifiedName__Group__1 ;
    public final void rule__QualifiedName__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2639:1: ( rule__QualifiedName__Group__0__Impl rule__QualifiedName__Group__1 )
            // InternalBehavioralInterface.g:2640:2: rule__QualifiedName__Group__0__Impl rule__QualifiedName__Group__1
            {
            pushFollow(FOLLOW_23);
            rule__QualifiedName__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__QualifiedName__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group__0"


    // $ANTLR start "rule__QualifiedName__Group__0__Impl"
    // InternalBehavioralInterface.g:2647:1: rule__QualifiedName__Group__0__Impl : ( ruleValidID ) ;
    public final void rule__QualifiedName__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2651:1: ( ( ruleValidID ) )
            // InternalBehavioralInterface.g:2652:1: ( ruleValidID )
            {
            // InternalBehavioralInterface.g:2652:1: ( ruleValidID )
            // InternalBehavioralInterface.g:2653:2: ruleValidID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getQualifiedNameAccess().getValidIDParserRuleCall_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleValidID();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getQualifiedNameAccess().getValidIDParserRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group__0__Impl"


    // $ANTLR start "rule__QualifiedName__Group__1"
    // InternalBehavioralInterface.g:2662:1: rule__QualifiedName__Group__1 : rule__QualifiedName__Group__1__Impl ;
    public final void rule__QualifiedName__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2666:1: ( rule__QualifiedName__Group__1__Impl )
            // InternalBehavioralInterface.g:2667:2: rule__QualifiedName__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__QualifiedName__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group__1"


    // $ANTLR start "rule__QualifiedName__Group__1__Impl"
    // InternalBehavioralInterface.g:2673:1: rule__QualifiedName__Group__1__Impl : ( ( rule__QualifiedName__Group_1__0 )* ) ;
    public final void rule__QualifiedName__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2677:1: ( ( ( rule__QualifiedName__Group_1__0 )* ) )
            // InternalBehavioralInterface.g:2678:1: ( ( rule__QualifiedName__Group_1__0 )* )
            {
            // InternalBehavioralInterface.g:2678:1: ( ( rule__QualifiedName__Group_1__0 )* )
            // InternalBehavioralInterface.g:2679:2: ( rule__QualifiedName__Group_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getQualifiedNameAccess().getGroup_1()); 
            }
            // InternalBehavioralInterface.g:2680:2: ( rule__QualifiedName__Group_1__0 )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==26) ) {
                    int LA25_2 = input.LA(2);

                    if ( (LA25_2==RULE_ID) ) {
                        alt25=1;
                    }


                }


                switch (alt25) {
            	case 1 :
            	    // InternalBehavioralInterface.g:2680:3: rule__QualifiedName__Group_1__0
            	    {
            	    pushFollow(FOLLOW_24);
            	    rule__QualifiedName__Group_1__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getQualifiedNameAccess().getGroup_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group__1__Impl"


    // $ANTLR start "rule__QualifiedName__Group_1__0"
    // InternalBehavioralInterface.g:2689:1: rule__QualifiedName__Group_1__0 : rule__QualifiedName__Group_1__0__Impl rule__QualifiedName__Group_1__1 ;
    public final void rule__QualifiedName__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2693:1: ( rule__QualifiedName__Group_1__0__Impl rule__QualifiedName__Group_1__1 )
            // InternalBehavioralInterface.g:2694:2: rule__QualifiedName__Group_1__0__Impl rule__QualifiedName__Group_1__1
            {
            pushFollow(FOLLOW_4);
            rule__QualifiedName__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__QualifiedName__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group_1__0"


    // $ANTLR start "rule__QualifiedName__Group_1__0__Impl"
    // InternalBehavioralInterface.g:2701:1: rule__QualifiedName__Group_1__0__Impl : ( '.' ) ;
    public final void rule__QualifiedName__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2705:1: ( ( '.' ) )
            // InternalBehavioralInterface.g:2706:1: ( '.' )
            {
            // InternalBehavioralInterface.g:2706:1: ( '.' )
            // InternalBehavioralInterface.g:2707:2: '.'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0()); 
            }
            match(input,26,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group_1__0__Impl"


    // $ANTLR start "rule__QualifiedName__Group_1__1"
    // InternalBehavioralInterface.g:2716:1: rule__QualifiedName__Group_1__1 : rule__QualifiedName__Group_1__1__Impl ;
    public final void rule__QualifiedName__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2720:1: ( rule__QualifiedName__Group_1__1__Impl )
            // InternalBehavioralInterface.g:2721:2: rule__QualifiedName__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__QualifiedName__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group_1__1"


    // $ANTLR start "rule__QualifiedName__Group_1__1__Impl"
    // InternalBehavioralInterface.g:2727:1: rule__QualifiedName__Group_1__1__Impl : ( ruleValidID ) ;
    public final void rule__QualifiedName__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2731:1: ( ( ruleValidID ) )
            // InternalBehavioralInterface.g:2732:1: ( ruleValidID )
            {
            // InternalBehavioralInterface.g:2732:1: ( ruleValidID )
            // InternalBehavioralInterface.g:2733:2: ruleValidID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getQualifiedNameAccess().getValidIDParserRuleCall_1_1()); 
            }
            pushFollow(FOLLOW_2);
            ruleValidID();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getQualifiedNameAccess().getValidIDParserRuleCall_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group_1__1__Impl"


    // $ANTLR start "rule__QualifiedNameWithWildcard__Group__0"
    // InternalBehavioralInterface.g:2743:1: rule__QualifiedNameWithWildcard__Group__0 : rule__QualifiedNameWithWildcard__Group__0__Impl rule__QualifiedNameWithWildcard__Group__1 ;
    public final void rule__QualifiedNameWithWildcard__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2747:1: ( rule__QualifiedNameWithWildcard__Group__0__Impl rule__QualifiedNameWithWildcard__Group__1 )
            // InternalBehavioralInterface.g:2748:2: rule__QualifiedNameWithWildcard__Group__0__Impl rule__QualifiedNameWithWildcard__Group__1
            {
            pushFollow(FOLLOW_23);
            rule__QualifiedNameWithWildcard__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__QualifiedNameWithWildcard__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedNameWithWildcard__Group__0"


    // $ANTLR start "rule__QualifiedNameWithWildcard__Group__0__Impl"
    // InternalBehavioralInterface.g:2755:1: rule__QualifiedNameWithWildcard__Group__0__Impl : ( ruleQualifiedName ) ;
    public final void rule__QualifiedNameWithWildcard__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2759:1: ( ( ruleQualifiedName ) )
            // InternalBehavioralInterface.g:2760:1: ( ruleQualifiedName )
            {
            // InternalBehavioralInterface.g:2760:1: ( ruleQualifiedName )
            // InternalBehavioralInterface.g:2761:2: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getQualifiedNameWithWildcardAccess().getQualifiedNameParserRuleCall_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getQualifiedNameWithWildcardAccess().getQualifiedNameParserRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedNameWithWildcard__Group__0__Impl"


    // $ANTLR start "rule__QualifiedNameWithWildcard__Group__1"
    // InternalBehavioralInterface.g:2770:1: rule__QualifiedNameWithWildcard__Group__1 : rule__QualifiedNameWithWildcard__Group__1__Impl rule__QualifiedNameWithWildcard__Group__2 ;
    public final void rule__QualifiedNameWithWildcard__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2774:1: ( rule__QualifiedNameWithWildcard__Group__1__Impl rule__QualifiedNameWithWildcard__Group__2 )
            // InternalBehavioralInterface.g:2775:2: rule__QualifiedNameWithWildcard__Group__1__Impl rule__QualifiedNameWithWildcard__Group__2
            {
            pushFollow(FOLLOW_13);
            rule__QualifiedNameWithWildcard__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__QualifiedNameWithWildcard__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedNameWithWildcard__Group__1"


    // $ANTLR start "rule__QualifiedNameWithWildcard__Group__1__Impl"
    // InternalBehavioralInterface.g:2782:1: rule__QualifiedNameWithWildcard__Group__1__Impl : ( '.' ) ;
    public final void rule__QualifiedNameWithWildcard__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2786:1: ( ( '.' ) )
            // InternalBehavioralInterface.g:2787:1: ( '.' )
            {
            // InternalBehavioralInterface.g:2787:1: ( '.' )
            // InternalBehavioralInterface.g:2788:2: '.'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getQualifiedNameWithWildcardAccess().getFullStopKeyword_1()); 
            }
            match(input,26,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getQualifiedNameWithWildcardAccess().getFullStopKeyword_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedNameWithWildcard__Group__1__Impl"


    // $ANTLR start "rule__QualifiedNameWithWildcard__Group__2"
    // InternalBehavioralInterface.g:2797:1: rule__QualifiedNameWithWildcard__Group__2 : rule__QualifiedNameWithWildcard__Group__2__Impl ;
    public final void rule__QualifiedNameWithWildcard__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2801:1: ( rule__QualifiedNameWithWildcard__Group__2__Impl )
            // InternalBehavioralInterface.g:2802:2: rule__QualifiedNameWithWildcard__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__QualifiedNameWithWildcard__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedNameWithWildcard__Group__2"


    // $ANTLR start "rule__QualifiedNameWithWildcard__Group__2__Impl"
    // InternalBehavioralInterface.g:2808:1: rule__QualifiedNameWithWildcard__Group__2__Impl : ( '*' ) ;
    public final void rule__QualifiedNameWithWildcard__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2812:1: ( ( '*' ) )
            // InternalBehavioralInterface.g:2813:1: ( '*' )
            {
            // InternalBehavioralInterface.g:2813:1: ( '*' )
            // InternalBehavioralInterface.g:2814:2: '*'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getQualifiedNameWithWildcardAccess().getAsteriskKeyword_2()); 
            }
            match(input,31,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getQualifiedNameWithWildcardAccess().getAsteriskKeyword_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedNameWithWildcard__Group__2__Impl"


    // $ANTLR start "rule__XImportDeclaration__Group__0"
    // InternalBehavioralInterface.g:2824:1: rule__XImportDeclaration__Group__0 : rule__XImportDeclaration__Group__0__Impl rule__XImportDeclaration__Group__1 ;
    public final void rule__XImportDeclaration__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2828:1: ( rule__XImportDeclaration__Group__0__Impl rule__XImportDeclaration__Group__1 )
            // InternalBehavioralInterface.g:2829:2: rule__XImportDeclaration__Group__0__Impl rule__XImportDeclaration__Group__1
            {
            pushFollow(FOLLOW_28);
            rule__XImportDeclaration__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__XImportDeclaration__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XImportDeclaration__Group__0"


    // $ANTLR start "rule__XImportDeclaration__Group__0__Impl"
    // InternalBehavioralInterface.g:2836:1: rule__XImportDeclaration__Group__0__Impl : ( 'import' ) ;
    public final void rule__XImportDeclaration__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2840:1: ( ( 'import' ) )
            // InternalBehavioralInterface.g:2841:1: ( 'import' )
            {
            // InternalBehavioralInterface.g:2841:1: ( 'import' )
            // InternalBehavioralInterface.g:2842:2: 'import'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXImportDeclarationAccess().getImportKeyword_0()); 
            }
            match(input,32,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getXImportDeclarationAccess().getImportKeyword_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XImportDeclaration__Group__0__Impl"


    // $ANTLR start "rule__XImportDeclaration__Group__1"
    // InternalBehavioralInterface.g:2851:1: rule__XImportDeclaration__Group__1 : rule__XImportDeclaration__Group__1__Impl rule__XImportDeclaration__Group__2 ;
    public final void rule__XImportDeclaration__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2855:1: ( rule__XImportDeclaration__Group__1__Impl rule__XImportDeclaration__Group__2 )
            // InternalBehavioralInterface.g:2856:2: rule__XImportDeclaration__Group__1__Impl rule__XImportDeclaration__Group__2
            {
            pushFollow(FOLLOW_29);
            rule__XImportDeclaration__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__XImportDeclaration__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XImportDeclaration__Group__1"


    // $ANTLR start "rule__XImportDeclaration__Group__1__Impl"
    // InternalBehavioralInterface.g:2863:1: rule__XImportDeclaration__Group__1__Impl : ( ( rule__XImportDeclaration__Alternatives_1 ) ) ;
    public final void rule__XImportDeclaration__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2867:1: ( ( ( rule__XImportDeclaration__Alternatives_1 ) ) )
            // InternalBehavioralInterface.g:2868:1: ( ( rule__XImportDeclaration__Alternatives_1 ) )
            {
            // InternalBehavioralInterface.g:2868:1: ( ( rule__XImportDeclaration__Alternatives_1 ) )
            // InternalBehavioralInterface.g:2869:2: ( rule__XImportDeclaration__Alternatives_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXImportDeclarationAccess().getAlternatives_1()); 
            }
            // InternalBehavioralInterface.g:2870:2: ( rule__XImportDeclaration__Alternatives_1 )
            // InternalBehavioralInterface.g:2870:3: rule__XImportDeclaration__Alternatives_1
            {
            pushFollow(FOLLOW_2);
            rule__XImportDeclaration__Alternatives_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getXImportDeclarationAccess().getAlternatives_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XImportDeclaration__Group__1__Impl"


    // $ANTLR start "rule__XImportDeclaration__Group__2"
    // InternalBehavioralInterface.g:2878:1: rule__XImportDeclaration__Group__2 : rule__XImportDeclaration__Group__2__Impl ;
    public final void rule__XImportDeclaration__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2882:1: ( rule__XImportDeclaration__Group__2__Impl )
            // InternalBehavioralInterface.g:2883:2: rule__XImportDeclaration__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__XImportDeclaration__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XImportDeclaration__Group__2"


    // $ANTLR start "rule__XImportDeclaration__Group__2__Impl"
    // InternalBehavioralInterface.g:2889:1: rule__XImportDeclaration__Group__2__Impl : ( ( ';' )? ) ;
    public final void rule__XImportDeclaration__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2893:1: ( ( ( ';' )? ) )
            // InternalBehavioralInterface.g:2894:1: ( ( ';' )? )
            {
            // InternalBehavioralInterface.g:2894:1: ( ( ';' )? )
            // InternalBehavioralInterface.g:2895:2: ( ';' )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXImportDeclarationAccess().getSemicolonKeyword_2()); 
            }
            // InternalBehavioralInterface.g:2896:2: ( ';' )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==33) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // InternalBehavioralInterface.g:2896:3: ';'
                    {
                    match(input,33,FOLLOW_2); if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getXImportDeclarationAccess().getSemicolonKeyword_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XImportDeclaration__Group__2__Impl"


    // $ANTLR start "rule__XImportDeclaration__Group_1_0__0"
    // InternalBehavioralInterface.g:2905:1: rule__XImportDeclaration__Group_1_0__0 : rule__XImportDeclaration__Group_1_0__0__Impl rule__XImportDeclaration__Group_1_0__1 ;
    public final void rule__XImportDeclaration__Group_1_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2909:1: ( rule__XImportDeclaration__Group_1_0__0__Impl rule__XImportDeclaration__Group_1_0__1 )
            // InternalBehavioralInterface.g:2910:2: rule__XImportDeclaration__Group_1_0__0__Impl rule__XImportDeclaration__Group_1_0__1
            {
            pushFollow(FOLLOW_30);
            rule__XImportDeclaration__Group_1_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__XImportDeclaration__Group_1_0__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XImportDeclaration__Group_1_0__0"


    // $ANTLR start "rule__XImportDeclaration__Group_1_0__0__Impl"
    // InternalBehavioralInterface.g:2917:1: rule__XImportDeclaration__Group_1_0__0__Impl : ( ( rule__XImportDeclaration__StaticAssignment_1_0_0 ) ) ;
    public final void rule__XImportDeclaration__Group_1_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2921:1: ( ( ( rule__XImportDeclaration__StaticAssignment_1_0_0 ) ) )
            // InternalBehavioralInterface.g:2922:1: ( ( rule__XImportDeclaration__StaticAssignment_1_0_0 ) )
            {
            // InternalBehavioralInterface.g:2922:1: ( ( rule__XImportDeclaration__StaticAssignment_1_0_0 ) )
            // InternalBehavioralInterface.g:2923:2: ( rule__XImportDeclaration__StaticAssignment_1_0_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXImportDeclarationAccess().getStaticAssignment_1_0_0()); 
            }
            // InternalBehavioralInterface.g:2924:2: ( rule__XImportDeclaration__StaticAssignment_1_0_0 )
            // InternalBehavioralInterface.g:2924:3: rule__XImportDeclaration__StaticAssignment_1_0_0
            {
            pushFollow(FOLLOW_2);
            rule__XImportDeclaration__StaticAssignment_1_0_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getXImportDeclarationAccess().getStaticAssignment_1_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XImportDeclaration__Group_1_0__0__Impl"


    // $ANTLR start "rule__XImportDeclaration__Group_1_0__1"
    // InternalBehavioralInterface.g:2932:1: rule__XImportDeclaration__Group_1_0__1 : rule__XImportDeclaration__Group_1_0__1__Impl rule__XImportDeclaration__Group_1_0__2 ;
    public final void rule__XImportDeclaration__Group_1_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2936:1: ( rule__XImportDeclaration__Group_1_0__1__Impl rule__XImportDeclaration__Group_1_0__2 )
            // InternalBehavioralInterface.g:2937:2: rule__XImportDeclaration__Group_1_0__1__Impl rule__XImportDeclaration__Group_1_0__2
            {
            pushFollow(FOLLOW_30);
            rule__XImportDeclaration__Group_1_0__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__XImportDeclaration__Group_1_0__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XImportDeclaration__Group_1_0__1"


    // $ANTLR start "rule__XImportDeclaration__Group_1_0__1__Impl"
    // InternalBehavioralInterface.g:2944:1: rule__XImportDeclaration__Group_1_0__1__Impl : ( ( rule__XImportDeclaration__ExtensionAssignment_1_0_1 )? ) ;
    public final void rule__XImportDeclaration__Group_1_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2948:1: ( ( ( rule__XImportDeclaration__ExtensionAssignment_1_0_1 )? ) )
            // InternalBehavioralInterface.g:2949:1: ( ( rule__XImportDeclaration__ExtensionAssignment_1_0_1 )? )
            {
            // InternalBehavioralInterface.g:2949:1: ( ( rule__XImportDeclaration__ExtensionAssignment_1_0_1 )? )
            // InternalBehavioralInterface.g:2950:2: ( rule__XImportDeclaration__ExtensionAssignment_1_0_1 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXImportDeclarationAccess().getExtensionAssignment_1_0_1()); 
            }
            // InternalBehavioralInterface.g:2951:2: ( rule__XImportDeclaration__ExtensionAssignment_1_0_1 )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==35) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // InternalBehavioralInterface.g:2951:3: rule__XImportDeclaration__ExtensionAssignment_1_0_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__XImportDeclaration__ExtensionAssignment_1_0_1();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getXImportDeclarationAccess().getExtensionAssignment_1_0_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XImportDeclaration__Group_1_0__1__Impl"


    // $ANTLR start "rule__XImportDeclaration__Group_1_0__2"
    // InternalBehavioralInterface.g:2959:1: rule__XImportDeclaration__Group_1_0__2 : rule__XImportDeclaration__Group_1_0__2__Impl rule__XImportDeclaration__Group_1_0__3 ;
    public final void rule__XImportDeclaration__Group_1_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2963:1: ( rule__XImportDeclaration__Group_1_0__2__Impl rule__XImportDeclaration__Group_1_0__3 )
            // InternalBehavioralInterface.g:2964:2: rule__XImportDeclaration__Group_1_0__2__Impl rule__XImportDeclaration__Group_1_0__3
            {
            pushFollow(FOLLOW_31);
            rule__XImportDeclaration__Group_1_0__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__XImportDeclaration__Group_1_0__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XImportDeclaration__Group_1_0__2"


    // $ANTLR start "rule__XImportDeclaration__Group_1_0__2__Impl"
    // InternalBehavioralInterface.g:2971:1: rule__XImportDeclaration__Group_1_0__2__Impl : ( ( rule__XImportDeclaration__ImportedTypeAssignment_1_0_2 ) ) ;
    public final void rule__XImportDeclaration__Group_1_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2975:1: ( ( ( rule__XImportDeclaration__ImportedTypeAssignment_1_0_2 ) ) )
            // InternalBehavioralInterface.g:2976:1: ( ( rule__XImportDeclaration__ImportedTypeAssignment_1_0_2 ) )
            {
            // InternalBehavioralInterface.g:2976:1: ( ( rule__XImportDeclaration__ImportedTypeAssignment_1_0_2 ) )
            // InternalBehavioralInterface.g:2977:2: ( rule__XImportDeclaration__ImportedTypeAssignment_1_0_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXImportDeclarationAccess().getImportedTypeAssignment_1_0_2()); 
            }
            // InternalBehavioralInterface.g:2978:2: ( rule__XImportDeclaration__ImportedTypeAssignment_1_0_2 )
            // InternalBehavioralInterface.g:2978:3: rule__XImportDeclaration__ImportedTypeAssignment_1_0_2
            {
            pushFollow(FOLLOW_2);
            rule__XImportDeclaration__ImportedTypeAssignment_1_0_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getXImportDeclarationAccess().getImportedTypeAssignment_1_0_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XImportDeclaration__Group_1_0__2__Impl"


    // $ANTLR start "rule__XImportDeclaration__Group_1_0__3"
    // InternalBehavioralInterface.g:2986:1: rule__XImportDeclaration__Group_1_0__3 : rule__XImportDeclaration__Group_1_0__3__Impl ;
    public final void rule__XImportDeclaration__Group_1_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:2990:1: ( rule__XImportDeclaration__Group_1_0__3__Impl )
            // InternalBehavioralInterface.g:2991:2: rule__XImportDeclaration__Group_1_0__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__XImportDeclaration__Group_1_0__3__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XImportDeclaration__Group_1_0__3"


    // $ANTLR start "rule__XImportDeclaration__Group_1_0__3__Impl"
    // InternalBehavioralInterface.g:2997:1: rule__XImportDeclaration__Group_1_0__3__Impl : ( ( rule__XImportDeclaration__Alternatives_1_0_3 ) ) ;
    public final void rule__XImportDeclaration__Group_1_0__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3001:1: ( ( ( rule__XImportDeclaration__Alternatives_1_0_3 ) ) )
            // InternalBehavioralInterface.g:3002:1: ( ( rule__XImportDeclaration__Alternatives_1_0_3 ) )
            {
            // InternalBehavioralInterface.g:3002:1: ( ( rule__XImportDeclaration__Alternatives_1_0_3 ) )
            // InternalBehavioralInterface.g:3003:2: ( rule__XImportDeclaration__Alternatives_1_0_3 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXImportDeclarationAccess().getAlternatives_1_0_3()); 
            }
            // InternalBehavioralInterface.g:3004:2: ( rule__XImportDeclaration__Alternatives_1_0_3 )
            // InternalBehavioralInterface.g:3004:3: rule__XImportDeclaration__Alternatives_1_0_3
            {
            pushFollow(FOLLOW_2);
            rule__XImportDeclaration__Alternatives_1_0_3();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getXImportDeclarationAccess().getAlternatives_1_0_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XImportDeclaration__Group_1_0__3__Impl"


    // $ANTLR start "rule__QualifiedNameInStaticImport__Group__0"
    // InternalBehavioralInterface.g:3013:1: rule__QualifiedNameInStaticImport__Group__0 : rule__QualifiedNameInStaticImport__Group__0__Impl rule__QualifiedNameInStaticImport__Group__1 ;
    public final void rule__QualifiedNameInStaticImport__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3017:1: ( rule__QualifiedNameInStaticImport__Group__0__Impl rule__QualifiedNameInStaticImport__Group__1 )
            // InternalBehavioralInterface.g:3018:2: rule__QualifiedNameInStaticImport__Group__0__Impl rule__QualifiedNameInStaticImport__Group__1
            {
            pushFollow(FOLLOW_23);
            rule__QualifiedNameInStaticImport__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__QualifiedNameInStaticImport__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedNameInStaticImport__Group__0"


    // $ANTLR start "rule__QualifiedNameInStaticImport__Group__0__Impl"
    // InternalBehavioralInterface.g:3025:1: rule__QualifiedNameInStaticImport__Group__0__Impl : ( ruleValidID ) ;
    public final void rule__QualifiedNameInStaticImport__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3029:1: ( ( ruleValidID ) )
            // InternalBehavioralInterface.g:3030:1: ( ruleValidID )
            {
            // InternalBehavioralInterface.g:3030:1: ( ruleValidID )
            // InternalBehavioralInterface.g:3031:2: ruleValidID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getQualifiedNameInStaticImportAccess().getValidIDParserRuleCall_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleValidID();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getQualifiedNameInStaticImportAccess().getValidIDParserRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedNameInStaticImport__Group__0__Impl"


    // $ANTLR start "rule__QualifiedNameInStaticImport__Group__1"
    // InternalBehavioralInterface.g:3040:1: rule__QualifiedNameInStaticImport__Group__1 : rule__QualifiedNameInStaticImport__Group__1__Impl ;
    public final void rule__QualifiedNameInStaticImport__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3044:1: ( rule__QualifiedNameInStaticImport__Group__1__Impl )
            // InternalBehavioralInterface.g:3045:2: rule__QualifiedNameInStaticImport__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__QualifiedNameInStaticImport__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedNameInStaticImport__Group__1"


    // $ANTLR start "rule__QualifiedNameInStaticImport__Group__1__Impl"
    // InternalBehavioralInterface.g:3051:1: rule__QualifiedNameInStaticImport__Group__1__Impl : ( '.' ) ;
    public final void rule__QualifiedNameInStaticImport__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3055:1: ( ( '.' ) )
            // InternalBehavioralInterface.g:3056:1: ( '.' )
            {
            // InternalBehavioralInterface.g:3056:1: ( '.' )
            // InternalBehavioralInterface.g:3057:2: '.'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getQualifiedNameInStaticImportAccess().getFullStopKeyword_1()); 
            }
            match(input,26,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getQualifiedNameInStaticImportAccess().getFullStopKeyword_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedNameInStaticImport__Group__1__Impl"


    // $ANTLR start "rule__BehavioralInterface__NameAssignment_1"
    // InternalBehavioralInterface.g:3067:1: rule__BehavioralInterface__NameAssignment_1 : ( ruleQualifiedName ) ;
    public final void rule__BehavioralInterface__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3071:1: ( ( ruleQualifiedName ) )
            // InternalBehavioralInterface.g:3072:2: ( ruleQualifiedName )
            {
            // InternalBehavioralInterface.g:3072:2: ( ruleQualifiedName )
            // InternalBehavioralInterface.g:3073:3: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBehavioralInterfaceAccess().getNameQualifiedNameParserRuleCall_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getBehavioralInterfaceAccess().getNameQualifiedNameParserRuleCall_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BehavioralInterface__NameAssignment_1"


    // $ANTLR start "rule__BehavioralInterface__EventsAssignment_2"
    // InternalBehavioralInterface.g:3082:1: rule__BehavioralInterface__EventsAssignment_2 : ( ruleEvent ) ;
    public final void rule__BehavioralInterface__EventsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3086:1: ( ( ruleEvent ) )
            // InternalBehavioralInterface.g:3087:2: ( ruleEvent )
            {
            // InternalBehavioralInterface.g:3087:2: ( ruleEvent )
            // InternalBehavioralInterface.g:3088:3: ruleEvent
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBehavioralInterfaceAccess().getEventsEventParserRuleCall_2_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleEvent();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getBehavioralInterfaceAccess().getEventsEventParserRuleCall_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BehavioralInterface__EventsAssignment_2"


    // $ANTLR start "rule__Event__TypeAssignment_0"
    // InternalBehavioralInterface.g:3097:1: rule__Event__TypeAssignment_0 : ( ruleEventType ) ;
    public final void rule__Event__TypeAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3101:1: ( ( ruleEventType ) )
            // InternalBehavioralInterface.g:3102:2: ( ruleEventType )
            {
            // InternalBehavioralInterface.g:3102:2: ( ruleEventType )
            // InternalBehavioralInterface.g:3103:3: ruleEventType
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventAccess().getTypeEventTypeEnumRuleCall_0_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleEventType();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventAccess().getTypeEventTypeEnumRuleCall_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__TypeAssignment_0"


    // $ANTLR start "rule__Event__NameAssignment_2"
    // InternalBehavioralInterface.g:3112:1: rule__Event__NameAssignment_2 : ( ruleQualifiedName ) ;
    public final void rule__Event__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3116:1: ( ( ruleQualifiedName ) )
            // InternalBehavioralInterface.g:3117:2: ( ruleQualifiedName )
            {
            // InternalBehavioralInterface.g:3117:2: ( ruleQualifiedName )
            // InternalBehavioralInterface.g:3118:3: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventAccess().getNameQualifiedNameParserRuleCall_2_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventAccess().getNameQualifiedNameParserRuleCall_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__NameAssignment_2"


    // $ANTLR start "rule__Event__ParamsAssignment_4_3"
    // InternalBehavioralInterface.g:3127:1: rule__Event__ParamsAssignment_4_3 : ( ruleEventParameter ) ;
    public final void rule__Event__ParamsAssignment_4_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3131:1: ( ( ruleEventParameter ) )
            // InternalBehavioralInterface.g:3132:2: ( ruleEventParameter )
            {
            // InternalBehavioralInterface.g:3132:2: ( ruleEventParameter )
            // InternalBehavioralInterface.g:3133:3: ruleEventParameter
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventAccess().getParamsEventParameterParserRuleCall_4_3_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleEventParameter();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventAccess().getParamsEventParameterParserRuleCall_4_3_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__ParamsAssignment_4_3"


    // $ANTLR start "rule__Event__ParamsAssignment_4_4_1"
    // InternalBehavioralInterface.g:3142:1: rule__Event__ParamsAssignment_4_4_1 : ( ruleEventParameter ) ;
    public final void rule__Event__ParamsAssignment_4_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3146:1: ( ( ruleEventParameter ) )
            // InternalBehavioralInterface.g:3147:2: ( ruleEventParameter )
            {
            // InternalBehavioralInterface.g:3147:2: ( ruleEventParameter )
            // InternalBehavioralInterface.g:3148:3: ruleEventParameter
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventAccess().getParamsEventParameterParserRuleCall_4_4_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleEventParameter();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventAccess().getParamsEventParameterParserRuleCall_4_4_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Event__ParamsAssignment_4_4_1"


    // $ANTLR start "rule__EventParameter__NameAssignment_0"
    // InternalBehavioralInterface.g:3157:1: rule__EventParameter__NameAssignment_0 : ( ruleQualifiedName ) ;
    public final void rule__EventParameter__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3161:1: ( ( ruleQualifiedName ) )
            // InternalBehavioralInterface.g:3162:2: ( ruleQualifiedName )
            {
            // InternalBehavioralInterface.g:3162:2: ( ruleQualifiedName )
            // InternalBehavioralInterface.g:3163:3: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventParameterAccess().getNameQualifiedNameParserRuleCall_0_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventParameterAccess().getNameQualifiedNameParserRuleCall_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EventParameter__NameAssignment_0"


    // $ANTLR start "rule__EventParameter__TypeAssignment_2"
    // InternalBehavioralInterface.g:3172:1: rule__EventParameter__TypeAssignment_2 : ( ruleQualifiedName ) ;
    public final void rule__EventParameter__TypeAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3176:1: ( ( ruleQualifiedName ) )
            // InternalBehavioralInterface.g:3177:2: ( ruleQualifiedName )
            {
            // InternalBehavioralInterface.g:3177:2: ( ruleQualifiedName )
            // InternalBehavioralInterface.g:3178:3: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventParameterAccess().getTypeQualifiedNameParserRuleCall_2_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventParameterAccess().getTypeQualifiedNameParserRuleCall_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EventParameter__TypeAssignment_2"


    // $ANTLR start "rule__EventParameter__ManyAssignment_3"
    // InternalBehavioralInterface.g:3187:1: rule__EventParameter__ManyAssignment_3 : ( ( '*' ) ) ;
    public final void rule__EventParameter__ManyAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3191:1: ( ( ( '*' ) ) )
            // InternalBehavioralInterface.g:3192:2: ( ( '*' ) )
            {
            // InternalBehavioralInterface.g:3192:2: ( ( '*' ) )
            // InternalBehavioralInterface.g:3193:3: ( '*' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventParameterAccess().getManyAsteriskKeyword_3_0()); 
            }
            // InternalBehavioralInterface.g:3194:3: ( '*' )
            // InternalBehavioralInterface.g:3195:4: '*'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEventParameterAccess().getManyAsteriskKeyword_3_0()); 
            }
            match(input,31,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventParameterAccess().getManyAsteriskKeyword_3_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEventParameterAccess().getManyAsteriskKeyword_3_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EventParameter__ManyAssignment_3"


    // $ANTLR start "rule__XFunctionTypeRef__ParamTypesAssignment_0_1_0"
    // InternalBehavioralInterface.g:3206:1: rule__XFunctionTypeRef__ParamTypesAssignment_0_1_0 : ( ruleJvmTypeReference ) ;
    public final void rule__XFunctionTypeRef__ParamTypesAssignment_0_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3210:1: ( ( ruleJvmTypeReference ) )
            // InternalBehavioralInterface.g:3211:2: ( ruleJvmTypeReference )
            {
            // InternalBehavioralInterface.g:3211:2: ( ruleJvmTypeReference )
            // InternalBehavioralInterface.g:3212:3: ruleJvmTypeReference
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXFunctionTypeRefAccess().getParamTypesJvmTypeReferenceParserRuleCall_0_1_0_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleJvmTypeReference();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getXFunctionTypeRefAccess().getParamTypesJvmTypeReferenceParserRuleCall_0_1_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XFunctionTypeRef__ParamTypesAssignment_0_1_0"


    // $ANTLR start "rule__XFunctionTypeRef__ParamTypesAssignment_0_1_1_1"
    // InternalBehavioralInterface.g:3221:1: rule__XFunctionTypeRef__ParamTypesAssignment_0_1_1_1 : ( ruleJvmTypeReference ) ;
    public final void rule__XFunctionTypeRef__ParamTypesAssignment_0_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3225:1: ( ( ruleJvmTypeReference ) )
            // InternalBehavioralInterface.g:3226:2: ( ruleJvmTypeReference )
            {
            // InternalBehavioralInterface.g:3226:2: ( ruleJvmTypeReference )
            // InternalBehavioralInterface.g:3227:3: ruleJvmTypeReference
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXFunctionTypeRefAccess().getParamTypesJvmTypeReferenceParserRuleCall_0_1_1_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleJvmTypeReference();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getXFunctionTypeRefAccess().getParamTypesJvmTypeReferenceParserRuleCall_0_1_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XFunctionTypeRef__ParamTypesAssignment_0_1_1_1"


    // $ANTLR start "rule__XFunctionTypeRef__ReturnTypeAssignment_2"
    // InternalBehavioralInterface.g:3236:1: rule__XFunctionTypeRef__ReturnTypeAssignment_2 : ( ruleJvmTypeReference ) ;
    public final void rule__XFunctionTypeRef__ReturnTypeAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3240:1: ( ( ruleJvmTypeReference ) )
            // InternalBehavioralInterface.g:3241:2: ( ruleJvmTypeReference )
            {
            // InternalBehavioralInterface.g:3241:2: ( ruleJvmTypeReference )
            // InternalBehavioralInterface.g:3242:3: ruleJvmTypeReference
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXFunctionTypeRefAccess().getReturnTypeJvmTypeReferenceParserRuleCall_2_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleJvmTypeReference();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getXFunctionTypeRefAccess().getReturnTypeJvmTypeReferenceParserRuleCall_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XFunctionTypeRef__ReturnTypeAssignment_2"


    // $ANTLR start "rule__JvmParameterizedTypeReference__TypeAssignment_0"
    // InternalBehavioralInterface.g:3251:1: rule__JvmParameterizedTypeReference__TypeAssignment_0 : ( ( ruleQualifiedName ) ) ;
    public final void rule__JvmParameterizedTypeReference__TypeAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3255:1: ( ( ( ruleQualifiedName ) ) )
            // InternalBehavioralInterface.g:3256:2: ( ( ruleQualifiedName ) )
            {
            // InternalBehavioralInterface.g:3256:2: ( ( ruleQualifiedName ) )
            // InternalBehavioralInterface.g:3257:3: ( ruleQualifiedName )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getTypeJvmTypeCrossReference_0_0()); 
            }
            // InternalBehavioralInterface.g:3258:3: ( ruleQualifiedName )
            // InternalBehavioralInterface.g:3259:4: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getTypeJvmTypeQualifiedNameParserRuleCall_0_0_1()); 
            }
            pushFollow(FOLLOW_2);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getTypeJvmTypeQualifiedNameParserRuleCall_0_0_1()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getTypeJvmTypeCrossReference_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__TypeAssignment_0"


    // $ANTLR start "rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_1"
    // InternalBehavioralInterface.g:3270:1: rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_1 : ( ruleJvmArgumentTypeReference ) ;
    public final void rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3274:1: ( ( ruleJvmArgumentTypeReference ) )
            // InternalBehavioralInterface.g:3275:2: ( ruleJvmArgumentTypeReference )
            {
            // InternalBehavioralInterface.g:3275:2: ( ruleJvmArgumentTypeReference )
            // InternalBehavioralInterface.g:3276:3: ruleJvmArgumentTypeReference
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getArgumentsJvmArgumentTypeReferenceParserRuleCall_1_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleJvmArgumentTypeReference();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getArgumentsJvmArgumentTypeReferenceParserRuleCall_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_1"


    // $ANTLR start "rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_2_1"
    // InternalBehavioralInterface.g:3285:1: rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_2_1 : ( ruleJvmArgumentTypeReference ) ;
    public final void rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3289:1: ( ( ruleJvmArgumentTypeReference ) )
            // InternalBehavioralInterface.g:3290:2: ( ruleJvmArgumentTypeReference )
            {
            // InternalBehavioralInterface.g:3290:2: ( ruleJvmArgumentTypeReference )
            // InternalBehavioralInterface.g:3291:3: ruleJvmArgumentTypeReference
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getArgumentsJvmArgumentTypeReferenceParserRuleCall_1_2_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleJvmArgumentTypeReference();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getArgumentsJvmArgumentTypeReferenceParserRuleCall_1_2_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_2_1"


    // $ANTLR start "rule__JvmParameterizedTypeReference__TypeAssignment_1_4_1"
    // InternalBehavioralInterface.g:3300:1: rule__JvmParameterizedTypeReference__TypeAssignment_1_4_1 : ( ( ruleValidID ) ) ;
    public final void rule__JvmParameterizedTypeReference__TypeAssignment_1_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3304:1: ( ( ( ruleValidID ) ) )
            // InternalBehavioralInterface.g:3305:2: ( ( ruleValidID ) )
            {
            // InternalBehavioralInterface.g:3305:2: ( ( ruleValidID ) )
            // InternalBehavioralInterface.g:3306:3: ( ruleValidID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getTypeJvmTypeCrossReference_1_4_1_0()); 
            }
            // InternalBehavioralInterface.g:3307:3: ( ruleValidID )
            // InternalBehavioralInterface.g:3308:4: ruleValidID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getTypeJvmTypeValidIDParserRuleCall_1_4_1_0_1()); 
            }
            pushFollow(FOLLOW_2);
            ruleValidID();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getTypeJvmTypeValidIDParserRuleCall_1_4_1_0_1()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getTypeJvmTypeCrossReference_1_4_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__TypeAssignment_1_4_1"


    // $ANTLR start "rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_4_2_1"
    // InternalBehavioralInterface.g:3319:1: rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_4_2_1 : ( ruleJvmArgumentTypeReference ) ;
    public final void rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_4_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3323:1: ( ( ruleJvmArgumentTypeReference ) )
            // InternalBehavioralInterface.g:3324:2: ( ruleJvmArgumentTypeReference )
            {
            // InternalBehavioralInterface.g:3324:2: ( ruleJvmArgumentTypeReference )
            // InternalBehavioralInterface.g:3325:3: ruleJvmArgumentTypeReference
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getArgumentsJvmArgumentTypeReferenceParserRuleCall_1_4_2_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleJvmArgumentTypeReference();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getArgumentsJvmArgumentTypeReferenceParserRuleCall_1_4_2_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_4_2_1"


    // $ANTLR start "rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_4_2_2_1"
    // InternalBehavioralInterface.g:3334:1: rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_4_2_2_1 : ( ruleJvmArgumentTypeReference ) ;
    public final void rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_4_2_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3338:1: ( ( ruleJvmArgumentTypeReference ) )
            // InternalBehavioralInterface.g:3339:2: ( ruleJvmArgumentTypeReference )
            {
            // InternalBehavioralInterface.g:3339:2: ( ruleJvmArgumentTypeReference )
            // InternalBehavioralInterface.g:3340:3: ruleJvmArgumentTypeReference
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmParameterizedTypeReferenceAccess().getArgumentsJvmArgumentTypeReferenceParserRuleCall_1_4_2_2_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleJvmArgumentTypeReference();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmParameterizedTypeReferenceAccess().getArgumentsJvmArgumentTypeReferenceParserRuleCall_1_4_2_2_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmParameterizedTypeReference__ArgumentsAssignment_1_4_2_2_1"


    // $ANTLR start "rule__JvmWildcardTypeReference__ConstraintsAssignment_2_0_0"
    // InternalBehavioralInterface.g:3349:1: rule__JvmWildcardTypeReference__ConstraintsAssignment_2_0_0 : ( ruleJvmUpperBound ) ;
    public final void rule__JvmWildcardTypeReference__ConstraintsAssignment_2_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3353:1: ( ( ruleJvmUpperBound ) )
            // InternalBehavioralInterface.g:3354:2: ( ruleJvmUpperBound )
            {
            // InternalBehavioralInterface.g:3354:2: ( ruleJvmUpperBound )
            // InternalBehavioralInterface.g:3355:3: ruleJvmUpperBound
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmWildcardTypeReferenceAccess().getConstraintsJvmUpperBoundParserRuleCall_2_0_0_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleJvmUpperBound();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmWildcardTypeReferenceAccess().getConstraintsJvmUpperBoundParserRuleCall_2_0_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmWildcardTypeReference__ConstraintsAssignment_2_0_0"


    // $ANTLR start "rule__JvmWildcardTypeReference__ConstraintsAssignment_2_0_1"
    // InternalBehavioralInterface.g:3364:1: rule__JvmWildcardTypeReference__ConstraintsAssignment_2_0_1 : ( ruleJvmUpperBoundAnded ) ;
    public final void rule__JvmWildcardTypeReference__ConstraintsAssignment_2_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3368:1: ( ( ruleJvmUpperBoundAnded ) )
            // InternalBehavioralInterface.g:3369:2: ( ruleJvmUpperBoundAnded )
            {
            // InternalBehavioralInterface.g:3369:2: ( ruleJvmUpperBoundAnded )
            // InternalBehavioralInterface.g:3370:3: ruleJvmUpperBoundAnded
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmWildcardTypeReferenceAccess().getConstraintsJvmUpperBoundAndedParserRuleCall_2_0_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleJvmUpperBoundAnded();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmWildcardTypeReferenceAccess().getConstraintsJvmUpperBoundAndedParserRuleCall_2_0_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmWildcardTypeReference__ConstraintsAssignment_2_0_1"


    // $ANTLR start "rule__JvmWildcardTypeReference__ConstraintsAssignment_2_1_0"
    // InternalBehavioralInterface.g:3379:1: rule__JvmWildcardTypeReference__ConstraintsAssignment_2_1_0 : ( ruleJvmLowerBound ) ;
    public final void rule__JvmWildcardTypeReference__ConstraintsAssignment_2_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3383:1: ( ( ruleJvmLowerBound ) )
            // InternalBehavioralInterface.g:3384:2: ( ruleJvmLowerBound )
            {
            // InternalBehavioralInterface.g:3384:2: ( ruleJvmLowerBound )
            // InternalBehavioralInterface.g:3385:3: ruleJvmLowerBound
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmWildcardTypeReferenceAccess().getConstraintsJvmLowerBoundParserRuleCall_2_1_0_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleJvmLowerBound();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmWildcardTypeReferenceAccess().getConstraintsJvmLowerBoundParserRuleCall_2_1_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmWildcardTypeReference__ConstraintsAssignment_2_1_0"


    // $ANTLR start "rule__JvmWildcardTypeReference__ConstraintsAssignment_2_1_1"
    // InternalBehavioralInterface.g:3394:1: rule__JvmWildcardTypeReference__ConstraintsAssignment_2_1_1 : ( ruleJvmLowerBoundAnded ) ;
    public final void rule__JvmWildcardTypeReference__ConstraintsAssignment_2_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3398:1: ( ( ruleJvmLowerBoundAnded ) )
            // InternalBehavioralInterface.g:3399:2: ( ruleJvmLowerBoundAnded )
            {
            // InternalBehavioralInterface.g:3399:2: ( ruleJvmLowerBoundAnded )
            // InternalBehavioralInterface.g:3400:3: ruleJvmLowerBoundAnded
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmWildcardTypeReferenceAccess().getConstraintsJvmLowerBoundAndedParserRuleCall_2_1_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleJvmLowerBoundAnded();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmWildcardTypeReferenceAccess().getConstraintsJvmLowerBoundAndedParserRuleCall_2_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmWildcardTypeReference__ConstraintsAssignment_2_1_1"


    // $ANTLR start "rule__JvmUpperBound__TypeReferenceAssignment_1"
    // InternalBehavioralInterface.g:3409:1: rule__JvmUpperBound__TypeReferenceAssignment_1 : ( ruleJvmTypeReference ) ;
    public final void rule__JvmUpperBound__TypeReferenceAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3413:1: ( ( ruleJvmTypeReference ) )
            // InternalBehavioralInterface.g:3414:2: ( ruleJvmTypeReference )
            {
            // InternalBehavioralInterface.g:3414:2: ( ruleJvmTypeReference )
            // InternalBehavioralInterface.g:3415:3: ruleJvmTypeReference
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmUpperBoundAccess().getTypeReferenceJvmTypeReferenceParserRuleCall_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleJvmTypeReference();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmUpperBoundAccess().getTypeReferenceJvmTypeReferenceParserRuleCall_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmUpperBound__TypeReferenceAssignment_1"


    // $ANTLR start "rule__JvmUpperBoundAnded__TypeReferenceAssignment_1"
    // InternalBehavioralInterface.g:3424:1: rule__JvmUpperBoundAnded__TypeReferenceAssignment_1 : ( ruleJvmTypeReference ) ;
    public final void rule__JvmUpperBoundAnded__TypeReferenceAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3428:1: ( ( ruleJvmTypeReference ) )
            // InternalBehavioralInterface.g:3429:2: ( ruleJvmTypeReference )
            {
            // InternalBehavioralInterface.g:3429:2: ( ruleJvmTypeReference )
            // InternalBehavioralInterface.g:3430:3: ruleJvmTypeReference
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmUpperBoundAndedAccess().getTypeReferenceJvmTypeReferenceParserRuleCall_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleJvmTypeReference();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmUpperBoundAndedAccess().getTypeReferenceJvmTypeReferenceParserRuleCall_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmUpperBoundAnded__TypeReferenceAssignment_1"


    // $ANTLR start "rule__JvmLowerBound__TypeReferenceAssignment_1"
    // InternalBehavioralInterface.g:3439:1: rule__JvmLowerBound__TypeReferenceAssignment_1 : ( ruleJvmTypeReference ) ;
    public final void rule__JvmLowerBound__TypeReferenceAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3443:1: ( ( ruleJvmTypeReference ) )
            // InternalBehavioralInterface.g:3444:2: ( ruleJvmTypeReference )
            {
            // InternalBehavioralInterface.g:3444:2: ( ruleJvmTypeReference )
            // InternalBehavioralInterface.g:3445:3: ruleJvmTypeReference
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmLowerBoundAccess().getTypeReferenceJvmTypeReferenceParserRuleCall_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleJvmTypeReference();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmLowerBoundAccess().getTypeReferenceJvmTypeReferenceParserRuleCall_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmLowerBound__TypeReferenceAssignment_1"


    // $ANTLR start "rule__JvmLowerBoundAnded__TypeReferenceAssignment_1"
    // InternalBehavioralInterface.g:3454:1: rule__JvmLowerBoundAnded__TypeReferenceAssignment_1 : ( ruleJvmTypeReference ) ;
    public final void rule__JvmLowerBoundAnded__TypeReferenceAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3458:1: ( ( ruleJvmTypeReference ) )
            // InternalBehavioralInterface.g:3459:2: ( ruleJvmTypeReference )
            {
            // InternalBehavioralInterface.g:3459:2: ( ruleJvmTypeReference )
            // InternalBehavioralInterface.g:3460:3: ruleJvmTypeReference
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getJvmLowerBoundAndedAccess().getTypeReferenceJvmTypeReferenceParserRuleCall_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleJvmTypeReference();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getJvmLowerBoundAndedAccess().getTypeReferenceJvmTypeReferenceParserRuleCall_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JvmLowerBoundAnded__TypeReferenceAssignment_1"


    // $ANTLR start "rule__XImportDeclaration__StaticAssignment_1_0_0"
    // InternalBehavioralInterface.g:3469:1: rule__XImportDeclaration__StaticAssignment_1_0_0 : ( ( 'static' ) ) ;
    public final void rule__XImportDeclaration__StaticAssignment_1_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3473:1: ( ( ( 'static' ) ) )
            // InternalBehavioralInterface.g:3474:2: ( ( 'static' ) )
            {
            // InternalBehavioralInterface.g:3474:2: ( ( 'static' ) )
            // InternalBehavioralInterface.g:3475:3: ( 'static' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXImportDeclarationAccess().getStaticStaticKeyword_1_0_0_0()); 
            }
            // InternalBehavioralInterface.g:3476:3: ( 'static' )
            // InternalBehavioralInterface.g:3477:4: 'static'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXImportDeclarationAccess().getStaticStaticKeyword_1_0_0_0()); 
            }
            match(input,34,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getXImportDeclarationAccess().getStaticStaticKeyword_1_0_0_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getXImportDeclarationAccess().getStaticStaticKeyword_1_0_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XImportDeclaration__StaticAssignment_1_0_0"


    // $ANTLR start "rule__XImportDeclaration__ExtensionAssignment_1_0_1"
    // InternalBehavioralInterface.g:3488:1: rule__XImportDeclaration__ExtensionAssignment_1_0_1 : ( ( 'extension' ) ) ;
    public final void rule__XImportDeclaration__ExtensionAssignment_1_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3492:1: ( ( ( 'extension' ) ) )
            // InternalBehavioralInterface.g:3493:2: ( ( 'extension' ) )
            {
            // InternalBehavioralInterface.g:3493:2: ( ( 'extension' ) )
            // InternalBehavioralInterface.g:3494:3: ( 'extension' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXImportDeclarationAccess().getExtensionExtensionKeyword_1_0_1_0()); 
            }
            // InternalBehavioralInterface.g:3495:3: ( 'extension' )
            // InternalBehavioralInterface.g:3496:4: 'extension'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXImportDeclarationAccess().getExtensionExtensionKeyword_1_0_1_0()); 
            }
            match(input,35,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getXImportDeclarationAccess().getExtensionExtensionKeyword_1_0_1_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getXImportDeclarationAccess().getExtensionExtensionKeyword_1_0_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XImportDeclaration__ExtensionAssignment_1_0_1"


    // $ANTLR start "rule__XImportDeclaration__ImportedTypeAssignment_1_0_2"
    // InternalBehavioralInterface.g:3507:1: rule__XImportDeclaration__ImportedTypeAssignment_1_0_2 : ( ( ruleQualifiedNameInStaticImport ) ) ;
    public final void rule__XImportDeclaration__ImportedTypeAssignment_1_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3511:1: ( ( ( ruleQualifiedNameInStaticImport ) ) )
            // InternalBehavioralInterface.g:3512:2: ( ( ruleQualifiedNameInStaticImport ) )
            {
            // InternalBehavioralInterface.g:3512:2: ( ( ruleQualifiedNameInStaticImport ) )
            // InternalBehavioralInterface.g:3513:3: ( ruleQualifiedNameInStaticImport )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXImportDeclarationAccess().getImportedTypeJvmDeclaredTypeCrossReference_1_0_2_0()); 
            }
            // InternalBehavioralInterface.g:3514:3: ( ruleQualifiedNameInStaticImport )
            // InternalBehavioralInterface.g:3515:4: ruleQualifiedNameInStaticImport
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXImportDeclarationAccess().getImportedTypeJvmDeclaredTypeQualifiedNameInStaticImportParserRuleCall_1_0_2_0_1()); 
            }
            pushFollow(FOLLOW_2);
            ruleQualifiedNameInStaticImport();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getXImportDeclarationAccess().getImportedTypeJvmDeclaredTypeQualifiedNameInStaticImportParserRuleCall_1_0_2_0_1()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getXImportDeclarationAccess().getImportedTypeJvmDeclaredTypeCrossReference_1_0_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XImportDeclaration__ImportedTypeAssignment_1_0_2"


    // $ANTLR start "rule__XImportDeclaration__WildcardAssignment_1_0_3_0"
    // InternalBehavioralInterface.g:3526:1: rule__XImportDeclaration__WildcardAssignment_1_0_3_0 : ( ( '*' ) ) ;
    public final void rule__XImportDeclaration__WildcardAssignment_1_0_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3530:1: ( ( ( '*' ) ) )
            // InternalBehavioralInterface.g:3531:2: ( ( '*' ) )
            {
            // InternalBehavioralInterface.g:3531:2: ( ( '*' ) )
            // InternalBehavioralInterface.g:3532:3: ( '*' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXImportDeclarationAccess().getWildcardAsteriskKeyword_1_0_3_0_0()); 
            }
            // InternalBehavioralInterface.g:3533:3: ( '*' )
            // InternalBehavioralInterface.g:3534:4: '*'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXImportDeclarationAccess().getWildcardAsteriskKeyword_1_0_3_0_0()); 
            }
            match(input,31,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getXImportDeclarationAccess().getWildcardAsteriskKeyword_1_0_3_0_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getXImportDeclarationAccess().getWildcardAsteriskKeyword_1_0_3_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XImportDeclaration__WildcardAssignment_1_0_3_0"


    // $ANTLR start "rule__XImportDeclaration__MemberNameAssignment_1_0_3_1"
    // InternalBehavioralInterface.g:3545:1: rule__XImportDeclaration__MemberNameAssignment_1_0_3_1 : ( ruleValidID ) ;
    public final void rule__XImportDeclaration__MemberNameAssignment_1_0_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3549:1: ( ( ruleValidID ) )
            // InternalBehavioralInterface.g:3550:2: ( ruleValidID )
            {
            // InternalBehavioralInterface.g:3550:2: ( ruleValidID )
            // InternalBehavioralInterface.g:3551:3: ruleValidID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXImportDeclarationAccess().getMemberNameValidIDParserRuleCall_1_0_3_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleValidID();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getXImportDeclarationAccess().getMemberNameValidIDParserRuleCall_1_0_3_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XImportDeclaration__MemberNameAssignment_1_0_3_1"


    // $ANTLR start "rule__XImportDeclaration__ImportedTypeAssignment_1_1"
    // InternalBehavioralInterface.g:3560:1: rule__XImportDeclaration__ImportedTypeAssignment_1_1 : ( ( ruleQualifiedName ) ) ;
    public final void rule__XImportDeclaration__ImportedTypeAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3564:1: ( ( ( ruleQualifiedName ) ) )
            // InternalBehavioralInterface.g:3565:2: ( ( ruleQualifiedName ) )
            {
            // InternalBehavioralInterface.g:3565:2: ( ( ruleQualifiedName ) )
            // InternalBehavioralInterface.g:3566:3: ( ruleQualifiedName )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXImportDeclarationAccess().getImportedTypeJvmDeclaredTypeCrossReference_1_1_0()); 
            }
            // InternalBehavioralInterface.g:3567:3: ( ruleQualifiedName )
            // InternalBehavioralInterface.g:3568:4: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXImportDeclarationAccess().getImportedTypeJvmDeclaredTypeQualifiedNameParserRuleCall_1_1_0_1()); 
            }
            pushFollow(FOLLOW_2);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getXImportDeclarationAccess().getImportedTypeJvmDeclaredTypeQualifiedNameParserRuleCall_1_1_0_1()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getXImportDeclarationAccess().getImportedTypeJvmDeclaredTypeCrossReference_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XImportDeclaration__ImportedTypeAssignment_1_1"


    // $ANTLR start "rule__XImportDeclaration__ImportedNamespaceAssignment_1_2"
    // InternalBehavioralInterface.g:3579:1: rule__XImportDeclaration__ImportedNamespaceAssignment_1_2 : ( ruleQualifiedNameWithWildcard ) ;
    public final void rule__XImportDeclaration__ImportedNamespaceAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBehavioralInterface.g:3583:1: ( ( ruleQualifiedNameWithWildcard ) )
            // InternalBehavioralInterface.g:3584:2: ( ruleQualifiedNameWithWildcard )
            {
            // InternalBehavioralInterface.g:3584:2: ( ruleQualifiedNameWithWildcard )
            // InternalBehavioralInterface.g:3585:3: ruleQualifiedNameWithWildcard
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getXImportDeclarationAccess().getImportedNamespaceQualifiedNameWithWildcardParserRuleCall_1_2_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleQualifiedNameWithWildcard();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getXImportDeclarationAccess().getImportedNamespaceQualifiedNameWithWildcardParserRuleCall_1_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XImportDeclaration__ImportedNamespaceAssignment_1_2"

    // Delegated rules


    protected DFA5 dfa5 = new DFA5(this);
    static final String dfa_1s = "\7\uffff";
    static final String dfa_2s = "\2\uffff\1\4\2\uffff\1\4\1\uffff";
    static final String dfa_3s = "\1\4\1\uffff\1\32\1\4\1\uffff\1\32\1\uffff";
    static final String dfa_4s = "\1\42\1\uffff\1\41\1\37\1\uffff\1\41\1\uffff";
    static final String dfa_5s = "\1\uffff\1\1\2\uffff\1\2\1\uffff\1\3";
    static final String dfa_6s = "\7\uffff}>";
    static final String[] dfa_7s = {
            "\1\2\35\uffff\1\1",
            "",
            "\1\3\6\uffff\1\4",
            "\1\5\32\uffff\1\6",
            "",
            "\1\3\6\uffff\1\4",
            ""
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final short[] dfa_2 = DFA.unpackEncodedString(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final char[] dfa_4 = DFA.unpackEncodedStringToUnsignedChars(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[] dfa_6 = DFA.unpackEncodedString(dfa_6s);
    static final short[][] dfa_7 = unpackEncodedStringArray(dfa_7s);

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = dfa_1;
            this.eof = dfa_2;
            this.min = dfa_3;
            this.max = dfa_4;
            this.accept = dfa_5;
            this.special = dfa_6;
            this.transition = dfa_7;
        }
        public String getDescription() {
            return "589:1: rule__XImportDeclaration__Alternatives_1 : ( ( ( rule__XImportDeclaration__Group_1_0__0 ) ) | ( ( rule__XImportDeclaration__ImportedTypeAssignment_1_1 ) ) | ( ( rule__XImportDeclaration__ImportedNamespaceAssignment_1_2 ) ) );";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000005C00L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000005C02L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000180000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000600010L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000E00010L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000008600010L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000002100000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000050000000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000400000010L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000000800000010L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000000080000010L});

}