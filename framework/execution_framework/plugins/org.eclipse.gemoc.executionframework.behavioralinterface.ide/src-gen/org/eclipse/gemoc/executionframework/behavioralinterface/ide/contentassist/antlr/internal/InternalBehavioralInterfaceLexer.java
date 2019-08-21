package org.eclipse.gemoc.executionframework.behavioralinterface.ide.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalBehavioralInterfaceLexer extends Lexer {
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

    public InternalBehavioralInterfaceLexer() {;} 
    public InternalBehavioralInterfaceLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalBehavioralInterfaceLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "InternalBehavioralInterface.g"; }

    // $ANTLR start "T__10"
    public final void mT__10() throws RecognitionException {
        try {
            int _type = T__10;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:11:7: ( 'both' )
            // InternalBehavioralInterface.g:11:9: 'both'
            {
            match("both"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__10"

    // $ANTLR start "T__11"
    public final void mT__11() throws RecognitionException {
        try {
            int _type = T__11;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:12:7: ( 'accepted' )
            // InternalBehavioralInterface.g:12:9: 'accepted'
            {
            match("accepted"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__11"

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:13:7: ( 'exposed' )
            // InternalBehavioralInterface.g:13:9: 'exposed'
            {
            match("exposed"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:14:7: ( 'BehavioralInterface' )
            // InternalBehavioralInterface.g:14:9: 'BehavioralInterface'
            {
            match("BehavioralInterface"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:15:7: ( 'event' )
            // InternalBehavioralInterface.g:15:9: 'event'
            {
            match("event"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:16:7: ( ':' )
            // InternalBehavioralInterface.g:16:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:17:7: ( 'parameters' )
            // InternalBehavioralInterface.g:17:9: 'parameters'
            {
            match("parameters"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:18:7: ( '=' )
            // InternalBehavioralInterface.g:18:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:19:7: ( '[' )
            // InternalBehavioralInterface.g:19:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:20:7: ( ']' )
            // InternalBehavioralInterface.g:20:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:21:7: ( ',' )
            // InternalBehavioralInterface.g:21:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:22:7: ( '=>' )
            // InternalBehavioralInterface.g:22:9: '=>'
            {
            match("=>"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:23:7: ( '(' )
            // InternalBehavioralInterface.g:23:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:24:7: ( ')' )
            // InternalBehavioralInterface.g:24:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:25:7: ( '<' )
            // InternalBehavioralInterface.g:25:9: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:26:7: ( '>' )
            // InternalBehavioralInterface.g:26:9: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:27:7: ( '.' )
            // InternalBehavioralInterface.g:27:9: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:28:7: ( '?' )
            // InternalBehavioralInterface.g:28:9: '?'
            {
            match('?'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:29:7: ( 'extends' )
            // InternalBehavioralInterface.g:29:9: 'extends'
            {
            match("extends"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:30:7: ( '&' )
            // InternalBehavioralInterface.g:30:9: '&'
            {
            match('&'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:31:7: ( 'super' )
            // InternalBehavioralInterface.g:31:9: 'super'
            {
            match("super"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:32:7: ( '*' )
            // InternalBehavioralInterface.g:32:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:33:7: ( 'import' )
            // InternalBehavioralInterface.g:33:9: 'import'
            {
            match("import"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:34:7: ( ';' )
            // InternalBehavioralInterface.g:34:9: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:35:7: ( 'static' )
            // InternalBehavioralInterface.g:35:9: 'static'
            {
            match("static"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "T__35"
    public final void mT__35() throws RecognitionException {
        try {
            int _type = T__35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:36:7: ( 'extension' )
            // InternalBehavioralInterface.g:36:9: 'extension'
            {
            match("extension"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__35"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:3594:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '$' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '$' | '_' | '0' .. '9' )* )
            // InternalBehavioralInterface.g:3594:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '$' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '$' | '_' | '0' .. '9' )*
            {
            // InternalBehavioralInterface.g:3594:11: ( '^' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='^') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // InternalBehavioralInterface.g:3594:11: '^'
                    {
                    match('^'); 

                    }
                    break;

            }

            if ( input.LA(1)=='$'||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // InternalBehavioralInterface.g:3594:44: ( 'a' .. 'z' | 'A' .. 'Z' | '$' | '_' | '0' .. '9' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0=='$'||(LA2_0>='0' && LA2_0<='9')||(LA2_0>='A' && LA2_0<='Z')||LA2_0=='_'||(LA2_0>='a' && LA2_0<='z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalBehavioralInterface.g:
            	    {
            	    if ( input.LA(1)=='$'||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ID"

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:3596:13: ( ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* ( '\"' )? | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* ( '\\'' )? ) )
            // InternalBehavioralInterface.g:3596:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* ( '\"' )? | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* ( '\\'' )? )
            {
            // InternalBehavioralInterface.g:3596:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* ( '\"' )? | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* ( '\\'' )? )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0=='\"') ) {
                alt7=1;
            }
            else if ( (LA7_0=='\'') ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // InternalBehavioralInterface.g:3596:16: '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* ( '\"' )?
                    {
                    match('\"'); 
                    // InternalBehavioralInterface.g:3596:20: ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )*
                    loop3:
                    do {
                        int alt3=3;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0=='\\') ) {
                            alt3=1;
                        }
                        else if ( ((LA3_0>='\u0000' && LA3_0<='!')||(LA3_0>='#' && LA3_0<='[')||(LA3_0>=']' && LA3_0<='\uFFFF')) ) {
                            alt3=2;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // InternalBehavioralInterface.g:3596:21: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalBehavioralInterface.g:3596:28: ~ ( ( '\\\\' | '\"' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);

                    // InternalBehavioralInterface.g:3596:44: ( '\"' )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0=='\"') ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // InternalBehavioralInterface.g:3596:44: '\"'
                            {
                            match('\"'); 

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // InternalBehavioralInterface.g:3596:49: '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* ( '\\'' )?
                    {
                    match('\''); 
                    // InternalBehavioralInterface.g:3596:54: ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )*
                    loop5:
                    do {
                        int alt5=3;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0=='\\') ) {
                            alt5=1;
                        }
                        else if ( ((LA5_0>='\u0000' && LA5_0<='&')||(LA5_0>='(' && LA5_0<='[')||(LA5_0>=']' && LA5_0<='\uFFFF')) ) {
                            alt5=2;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // InternalBehavioralInterface.g:3596:55: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalBehavioralInterface.g:3596:62: ~ ( ( '\\\\' | '\\'' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);

                    // InternalBehavioralInterface.g:3596:79: ( '\\'' )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0=='\'') ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // InternalBehavioralInterface.g:3596:79: '\\''
                            {
                            match('\''); 

                            }
                            break;

                    }


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_STRING"

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:3598:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // InternalBehavioralInterface.g:3598:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // InternalBehavioralInterface.g:3598:24: ( options {greedy=false; } : . )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0=='*') ) {
                    int LA8_1 = input.LA(2);

                    if ( (LA8_1=='/') ) {
                        alt8=2;
                    }
                    else if ( ((LA8_1>='\u0000' && LA8_1<='.')||(LA8_1>='0' && LA8_1<='\uFFFF')) ) {
                        alt8=1;
                    }


                }
                else if ( ((LA8_0>='\u0000' && LA8_0<=')')||(LA8_0>='+' && LA8_0<='\uFFFF')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalBehavioralInterface.g:3598:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            match("*/"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ML_COMMENT"

    // $ANTLR start "RULE_SL_COMMENT"
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:3600:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // InternalBehavioralInterface.g:3600:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // InternalBehavioralInterface.g:3600:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>='\u0000' && LA9_0<='\t')||(LA9_0>='\u000B' && LA9_0<='\f')||(LA9_0>='\u000E' && LA9_0<='\uFFFF')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalBehavioralInterface.g:3600:24: ~ ( ( '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            // InternalBehavioralInterface.g:3600:40: ( ( '\\r' )? '\\n' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='\n'||LA11_0=='\r') ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalBehavioralInterface.g:3600:41: ( '\\r' )? '\\n'
                    {
                    // InternalBehavioralInterface.g:3600:41: ( '\\r' )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0=='\r') ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // InternalBehavioralInterface.g:3600:41: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SL_COMMENT"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:3602:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // InternalBehavioralInterface.g:3602:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // InternalBehavioralInterface.g:3602:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>='\t' && LA12_0<='\n')||LA12_0=='\r'||LA12_0==' ') ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalBehavioralInterface.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WS"

    // $ANTLR start "RULE_ANY_OTHER"
    public final void mRULE_ANY_OTHER() throws RecognitionException {
        try {
            int _type = RULE_ANY_OTHER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBehavioralInterface.g:3604:16: ( . )
            // InternalBehavioralInterface.g:3604:18: .
            {
            matchAny(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ANY_OTHER"

    public void mTokens() throws RecognitionException {
        // InternalBehavioralInterface.g:1:8: ( T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | RULE_ID | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt13=32;
        alt13 = dfa13.predict(input);
        switch (alt13) {
            case 1 :
                // InternalBehavioralInterface.g:1:10: T__10
                {
                mT__10(); 

                }
                break;
            case 2 :
                // InternalBehavioralInterface.g:1:16: T__11
                {
                mT__11(); 

                }
                break;
            case 3 :
                // InternalBehavioralInterface.g:1:22: T__12
                {
                mT__12(); 

                }
                break;
            case 4 :
                // InternalBehavioralInterface.g:1:28: T__13
                {
                mT__13(); 

                }
                break;
            case 5 :
                // InternalBehavioralInterface.g:1:34: T__14
                {
                mT__14(); 

                }
                break;
            case 6 :
                // InternalBehavioralInterface.g:1:40: T__15
                {
                mT__15(); 

                }
                break;
            case 7 :
                // InternalBehavioralInterface.g:1:46: T__16
                {
                mT__16(); 

                }
                break;
            case 8 :
                // InternalBehavioralInterface.g:1:52: T__17
                {
                mT__17(); 

                }
                break;
            case 9 :
                // InternalBehavioralInterface.g:1:58: T__18
                {
                mT__18(); 

                }
                break;
            case 10 :
                // InternalBehavioralInterface.g:1:64: T__19
                {
                mT__19(); 

                }
                break;
            case 11 :
                // InternalBehavioralInterface.g:1:70: T__20
                {
                mT__20(); 

                }
                break;
            case 12 :
                // InternalBehavioralInterface.g:1:76: T__21
                {
                mT__21(); 

                }
                break;
            case 13 :
                // InternalBehavioralInterface.g:1:82: T__22
                {
                mT__22(); 

                }
                break;
            case 14 :
                // InternalBehavioralInterface.g:1:88: T__23
                {
                mT__23(); 

                }
                break;
            case 15 :
                // InternalBehavioralInterface.g:1:94: T__24
                {
                mT__24(); 

                }
                break;
            case 16 :
                // InternalBehavioralInterface.g:1:100: T__25
                {
                mT__25(); 

                }
                break;
            case 17 :
                // InternalBehavioralInterface.g:1:106: T__26
                {
                mT__26(); 

                }
                break;
            case 18 :
                // InternalBehavioralInterface.g:1:112: T__27
                {
                mT__27(); 

                }
                break;
            case 19 :
                // InternalBehavioralInterface.g:1:118: T__28
                {
                mT__28(); 

                }
                break;
            case 20 :
                // InternalBehavioralInterface.g:1:124: T__29
                {
                mT__29(); 

                }
                break;
            case 21 :
                // InternalBehavioralInterface.g:1:130: T__30
                {
                mT__30(); 

                }
                break;
            case 22 :
                // InternalBehavioralInterface.g:1:136: T__31
                {
                mT__31(); 

                }
                break;
            case 23 :
                // InternalBehavioralInterface.g:1:142: T__32
                {
                mT__32(); 

                }
                break;
            case 24 :
                // InternalBehavioralInterface.g:1:148: T__33
                {
                mT__33(); 

                }
                break;
            case 25 :
                // InternalBehavioralInterface.g:1:154: T__34
                {
                mT__34(); 

                }
                break;
            case 26 :
                // InternalBehavioralInterface.g:1:160: T__35
                {
                mT__35(); 

                }
                break;
            case 27 :
                // InternalBehavioralInterface.g:1:166: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 28 :
                // InternalBehavioralInterface.g:1:174: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 29 :
                // InternalBehavioralInterface.g:1:186: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 30 :
                // InternalBehavioralInterface.g:1:202: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 31 :
                // InternalBehavioralInterface.g:1:218: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 32 :
                // InternalBehavioralInterface.g:1:226: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA13 dfa13 = new DFA13(this);
    static final String DFA13_eotS =
        "\1\uffff\4\36\1\uffff\1\36\1\46\12\uffff\1\36\1\uffff\1\36\1\uffff\1\34\3\uffff\1\34\2\uffff\1\36\1\uffff\4\36\1\uffff\1\36\14\uffff\2\36\1\uffff\1\36\5\uffff\12\36\1\116\11\36\1\uffff\3\36\1\134\2\36\1\137\6\36\1\uffff\2\36\1\uffff\1\150\1\151\1\36\1\153\1\154\3\36\2\uffff\1\160\2\uffff\3\36\1\uffff\1\164\2\36\1\uffff\1\36\1\170\1\36\1\uffff\7\36\1\u0081\1\uffff";
    static final String DFA13_eofS =
        "\u0082\uffff";
    static final String DFA13_minS =
        "\1\0\1\157\1\143\1\166\1\145\1\uffff\1\141\1\76\12\uffff\1\164\1\uffff\1\155\1\uffff\1\44\3\uffff\1\52\2\uffff\1\164\1\uffff\1\143\1\160\1\145\1\150\1\uffff\1\162\14\uffff\1\160\1\141\1\uffff\1\160\5\uffff\1\150\1\145\1\157\1\145\1\156\2\141\1\145\1\164\1\157\1\44\1\160\1\163\1\156\1\164\1\166\1\155\1\162\1\151\1\162\1\uffff\1\164\1\145\1\144\1\44\1\151\1\145\1\44\1\143\1\164\1\145\1\144\1\163\1\151\1\uffff\1\157\1\164\1\uffff\2\44\1\144\2\44\1\157\1\162\1\145\2\uffff\1\44\2\uffff\1\156\1\141\1\162\1\uffff\1\44\1\154\1\163\1\uffff\1\111\1\44\1\156\1\uffff\1\164\1\145\1\162\1\146\1\141\1\143\1\145\1\44\1\uffff";
    static final String DFA13_maxS =
        "\1\uffff\1\157\1\143\1\170\1\145\1\uffff\1\141\1\76\12\uffff\1\165\1\uffff\1\155\1\uffff\1\172\3\uffff\1\57\2\uffff\1\164\1\uffff\1\143\1\164\1\145\1\150\1\uffff\1\162\14\uffff\1\160\1\141\1\uffff\1\160\5\uffff\1\150\1\145\1\157\1\145\1\156\2\141\1\145\1\164\1\157\1\172\1\160\1\163\1\156\1\164\1\166\1\155\1\162\1\151\1\162\1\uffff\1\164\1\145\1\163\1\172\1\151\1\145\1\172\1\143\1\164\1\145\1\144\1\163\1\151\1\uffff\1\157\1\164\1\uffff\2\172\1\144\2\172\1\157\1\162\1\145\2\uffff\1\172\2\uffff\1\156\1\141\1\162\1\uffff\1\172\1\154\1\163\1\uffff\1\111\1\172\1\156\1\uffff\1\164\1\145\1\162\1\146\1\141\1\143\1\145\1\172\1\uffff";
    static final String DFA13_acceptS =
        "\5\uffff\1\6\2\uffff\1\11\1\12\1\13\1\15\1\16\1\17\1\20\1\21\1\22\1\24\1\uffff\1\26\1\uffff\1\30\1\uffff\1\33\2\34\1\uffff\1\37\1\40\1\uffff\1\33\4\uffff\1\6\1\uffff\1\14\1\10\1\11\1\12\1\13\1\15\1\16\1\17\1\20\1\21\1\22\1\24\2\uffff\1\26\1\uffff\1\30\1\34\1\35\1\36\1\37\24\uffff\1\1\15\uffff\1\5\2\uffff\1\25\10\uffff\1\31\1\27\1\uffff\1\3\1\23\3\uffff\1\2\3\uffff\1\32\3\uffff\1\7\10\uffff\1\4";
    static final String DFA13_specialS =
        "\1\0\u0081\uffff}>";
    static final String[] DFA13_transitionS = {
            "\11\34\2\33\2\34\1\33\22\34\1\33\1\34\1\30\1\34\1\27\1\34\1\21\1\31\1\13\1\14\1\23\1\34\1\12\1\34\1\17\1\32\12\34\1\5\1\25\1\15\1\7\1\16\1\20\1\34\1\27\1\4\30\27\1\10\1\34\1\11\1\26\1\27\1\34\1\2\1\1\2\27\1\3\3\27\1\24\6\27\1\6\2\27\1\22\7\27\uff85\34",
            "\1\35",
            "\1\37",
            "\1\41\1\uffff\1\40",
            "\1\42",
            "",
            "\1\44",
            "\1\45",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\62\1\61",
            "",
            "\1\64",
            "",
            "\1\36\34\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "",
            "",
            "",
            "\1\67\4\uffff\1\70",
            "",
            "",
            "\1\72",
            "",
            "\1\73",
            "\1\74\3\uffff\1\75",
            "\1\76",
            "\1\77",
            "",
            "\1\100",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\101",
            "\1\102",
            "",
            "\1\103",
            "",
            "",
            "",
            "",
            "",
            "\1\104",
            "\1\105",
            "\1\106",
            "\1\107",
            "\1\110",
            "\1\111",
            "\1\112",
            "\1\113",
            "\1\114",
            "\1\115",
            "\1\36\13\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\1\117",
            "\1\120",
            "\1\121",
            "\1\122",
            "\1\123",
            "\1\124",
            "\1\125",
            "\1\126",
            "\1\127",
            "",
            "\1\130",
            "\1\131",
            "\1\132\16\uffff\1\133",
            "\1\36\13\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\1\135",
            "\1\136",
            "\1\36\13\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\1\140",
            "\1\141",
            "\1\142",
            "\1\143",
            "\1\144",
            "\1\145",
            "",
            "\1\146",
            "\1\147",
            "",
            "\1\36\13\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\1\36\13\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\1\152",
            "\1\36\13\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\1\36\13\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\1\155",
            "\1\156",
            "\1\157",
            "",
            "",
            "\1\36\13\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "",
            "",
            "\1\161",
            "\1\162",
            "\1\163",
            "",
            "\1\36\13\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\1\165",
            "\1\166",
            "",
            "\1\167",
            "\1\36\13\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\1\171",
            "",
            "\1\172",
            "\1\173",
            "\1\174",
            "\1\175",
            "\1\176",
            "\1\177",
            "\1\u0080",
            "\1\36\13\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            ""
    };

    static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
    static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
    static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
    static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
    static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
    static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
    static final short[][] DFA13_transition;

    static {
        int numStates = DFA13_transitionS.length;
        DFA13_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
        }
    }

    class DFA13 extends DFA {

        public DFA13(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 13;
            this.eot = DFA13_eot;
            this.eof = DFA13_eof;
            this.min = DFA13_min;
            this.max = DFA13_max;
            this.accept = DFA13_accept;
            this.special = DFA13_special;
            this.transition = DFA13_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | RULE_ID | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA13_0 = input.LA(1);

                        s = -1;
                        if ( (LA13_0=='b') ) {s = 1;}

                        else if ( (LA13_0=='a') ) {s = 2;}

                        else if ( (LA13_0=='e') ) {s = 3;}

                        else if ( (LA13_0=='B') ) {s = 4;}

                        else if ( (LA13_0==':') ) {s = 5;}

                        else if ( (LA13_0=='p') ) {s = 6;}

                        else if ( (LA13_0=='=') ) {s = 7;}

                        else if ( (LA13_0=='[') ) {s = 8;}

                        else if ( (LA13_0==']') ) {s = 9;}

                        else if ( (LA13_0==',') ) {s = 10;}

                        else if ( (LA13_0=='(') ) {s = 11;}

                        else if ( (LA13_0==')') ) {s = 12;}

                        else if ( (LA13_0=='<') ) {s = 13;}

                        else if ( (LA13_0=='>') ) {s = 14;}

                        else if ( (LA13_0=='.') ) {s = 15;}

                        else if ( (LA13_0=='?') ) {s = 16;}

                        else if ( (LA13_0=='&') ) {s = 17;}

                        else if ( (LA13_0=='s') ) {s = 18;}

                        else if ( (LA13_0=='*') ) {s = 19;}

                        else if ( (LA13_0=='i') ) {s = 20;}

                        else if ( (LA13_0==';') ) {s = 21;}

                        else if ( (LA13_0=='^') ) {s = 22;}

                        else if ( (LA13_0=='$'||LA13_0=='A'||(LA13_0>='C' && LA13_0<='Z')||LA13_0=='_'||(LA13_0>='c' && LA13_0<='d')||(LA13_0>='f' && LA13_0<='h')||(LA13_0>='j' && LA13_0<='o')||(LA13_0>='q' && LA13_0<='r')||(LA13_0>='t' && LA13_0<='z')) ) {s = 23;}

                        else if ( (LA13_0=='\"') ) {s = 24;}

                        else if ( (LA13_0=='\'') ) {s = 25;}

                        else if ( (LA13_0=='/') ) {s = 26;}

                        else if ( ((LA13_0>='\t' && LA13_0<='\n')||LA13_0=='\r'||LA13_0==' ') ) {s = 27;}

                        else if ( ((LA13_0>='\u0000' && LA13_0<='\b')||(LA13_0>='\u000B' && LA13_0<='\f')||(LA13_0>='\u000E' && LA13_0<='\u001F')||LA13_0=='!'||LA13_0=='#'||LA13_0=='%'||LA13_0=='+'||LA13_0=='-'||(LA13_0>='0' && LA13_0<='9')||LA13_0=='@'||LA13_0=='\\'||LA13_0=='`'||(LA13_0>='{' && LA13_0<='\uFFFF')) ) {s = 28;}

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 13, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}