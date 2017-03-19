package ide.sketch.compiler;
// import java.lang.System;
import ide.sketch.managers.ScreenOutManager;
import java.util.*;
import java.io.*;
import java_cup.runtime.Symbol;


public class Yylex implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 256;
	private final int YY_EOF = 257;

    String tok = "";
    private Symbol Token(int token, Object lexema) throws IOException {
        int linea = yyline+1;
        tok = (String)lexema;
        if (token != sym.EOF)
            InformacionCodigo.guardarInformacionCodigo(linea,tok);
        return new Symbol(token, lexema);
    }
    private Symbol Token(int token) throws IOException {
        return Token(token, yytext());
    }
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int COMENTLINE = 2;
	private final int COMENTCOMPLETE = 1;
	private final int yy_state_dtrans[] = {
		0,
		103,
		105
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NOT_ACCEPT,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NOT_ACCEPT,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NOT_ACCEPT,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NOT_ACCEPT,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NOT_ACCEPT,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NOT_ACCEPT,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NOT_ACCEPT,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NOT_ACCEPT,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NOT_ACCEPT,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NOT_ACCEPT,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NOT_ACCEPT,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NOT_ACCEPT,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NOT_ACCEPT,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NOT_ACCEPT,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NOT_ACCEPT,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NOT_ACCEPT,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NOT_ACCEPT,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NOT_ACCEPT,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NOT_ACCEPT,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NOT_ACCEPT,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NOT_ACCEPT,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NOT_ACCEPT,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NO_ANCHOR,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NO_ANCHOR,
		/* 133 */ YY_NO_ANCHOR,
		/* 134 */ YY_NO_ANCHOR,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NO_ANCHOR,
		/* 137 */ YY_NO_ANCHOR,
		/* 138 */ YY_NO_ANCHOR,
		/* 139 */ YY_NO_ANCHOR,
		/* 140 */ YY_NO_ANCHOR,
		/* 141 */ YY_NO_ANCHOR,
		/* 142 */ YY_NO_ANCHOR,
		/* 143 */ YY_NO_ANCHOR,
		/* 144 */ YY_NO_ANCHOR,
		/* 145 */ YY_NO_ANCHOR,
		/* 146 */ YY_NO_ANCHOR,
		/* 147 */ YY_NO_ANCHOR,
		/* 148 */ YY_NO_ANCHOR,
		/* 149 */ YY_NO_ANCHOR,
		/* 150 */ YY_NO_ANCHOR,
		/* 151 */ YY_NO_ANCHOR,
		/* 152 */ YY_NO_ANCHOR,
		/* 153 */ YY_NO_ANCHOR,
		/* 154 */ YY_NO_ANCHOR,
		/* 155 */ YY_NO_ANCHOR,
		/* 156 */ YY_NO_ANCHOR,
		/* 157 */ YY_NO_ANCHOR,
		/* 158 */ YY_NO_ANCHOR,
		/* 159 */ YY_NO_ANCHOR,
		/* 160 */ YY_NO_ANCHOR,
		/* 161 */ YY_NO_ANCHOR,
		/* 162 */ YY_NO_ANCHOR,
		/* 163 */ YY_NO_ANCHOR,
		/* 164 */ YY_NO_ANCHOR,
		/* 165 */ YY_NO_ANCHOR,
		/* 166 */ YY_NO_ANCHOR,
		/* 167 */ YY_NO_ANCHOR,
		/* 168 */ YY_NO_ANCHOR,
		/* 169 */ YY_NO_ANCHOR,
		/* 170 */ YY_NO_ANCHOR,
		/* 171 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,258,
"60:9,59,58,60,59,58,60:18,31,37,55,27,60,34,38,57,47,48,32,35,20,2,54,33,1," +
"9,4:6,3:2,19,18,28,36,30,60:2,6,8,6:4,29:12,46,29:4,5,29:2,49,56,50,39,29,6" +
"0,11,7,23,26,14,10,45,24,22,29,53,12,29,25,43,29:2,16,13,15,17,42,21,5,44,2" +
"9,51,40,52,41,60:129,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,172,
"0,1,2,3,4,1:3,5,6,7:2,1:7,8,9,4,1:2,10,11,4:2,12,13,4:12,14,1:2,15,1:3,16,1" +
"0,17,18,19,20,1:3,11,21,22,1,23,24,25,26,27,28,12,1,29,13,30,31,32,33,34,35" +
",36,37,38,6,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59," +
"60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84," +
"85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,10" +
"7,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,1" +
"26,127,128")[0];

	private int yy_nxt[][] = unpackFromString(129,61,
"1,2,3,51:2,4:2,133,4,51,108,155,134,156,135,136,165,4,5,6,7,137,53,138,4,13" +
"9,166,8,52,4,59,9,63,66,69,72,10,11,75,63,78,56,140,4:3,167,12,13,14,15,16," +
"17,4,18,54,61,109,19,9,61,-1:62,20,-1:2,20,50,-1,58:2,20,-1:53,56,51:2,-1:4" +
",51,-1:26,55,-1:25,4,-1,4:15,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:29,62,-1:3" +
",65,-1:65,9,-1:8,9,-1:17,9:2,-1:37,69,-1:55,9,-1:8,9,-1:17,19,9,-1:2,20,-1:" +
"2,20,-1:4,20,-1:52,24,-1,24:2,-1,24,-1,24:2,-1:52,25,-1:7,25,-1:52,68:54,28" +
",68:2,-1,68:2,-1,71:56,29,-1,71:2,-1,42,-1,42:15,-1:3,42:6,-1:2,42,-1:12,42" +
":5,-1:6,42,-1:40,46,-1:85,49,-1:3,51,-1,51:2,-1:4,51,-1:79,63,-1:7,69,-1:25" +
",4,-1,4:7,21,4:7,-1:3,4:4,64,4,-1:2,4,-1:12,4:5,-1:6,4,-1:8,68:57,-1,68:2,-" +
"1:30,63,-1:5,69,-1:25,4,-1,4:13,26,4,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:32" +
",74,-1:71,55,-1:25,4,-1,4:12,27,4:2,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:21," +
"77,-1:78,22,23,-1:2,55,-1:25,4,-1,4:11,27,4:3,-1:3,4:6,-1:2,4,-1:12,4:5,-1:" +
"6,4,-1:8,4,-1,4:15,-1:3,4:6,-1:2,4,-1:12,4:3,30,4,-1:6,4,-1:42,56,55,-1:25," +
"4,-1,4:11,31,4:3,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:30,80,-1:73,55,-1,69,-" +
"1:23,4,-1,4:11,32,4:3,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:17,83,-1:81,9,-1:" +
"4,55,-1:3,81,-1:17,9:2,-1:2,4,-1,4:15,-1:3,4:5,27,-1:2,4,-1:12,4:5,-1:6,4,-" +
"1:19,85,-1:49,4,-1,4:11,33,4:3,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:29,87,-1" +
":39,4,-1,4:13,27,4,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:24,89,-1:44,4,-1,4:9" +
",34,4:5,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:32,91,-1:36,4,-1,4:15,-1:3,4:6," +
"-1:2,4,-1:12,4:5,-1:6,35,-1:33,110,-1:35,4,-1,4:12,30,4:2,-1:3,4:6,-1:2,4,-" +
"1:12,4:5,-1:6,4,-1:21,93,-1:47,4,-1,4:15,-1:3,4:6,-1:2,4,-1:12,4:2,30,4:2,-" +
"1:6,4,-1:38,97,-1:30,4,-1,4:11,36,4:3,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:3" +
"5,99,-1:33,4,-1,4:12,37,4:2,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:12,42:4,-1," +
"42:8,-1:3,42:6,-1:2,42,-1:12,42:5,-1:6,42,-1:8,4,-1,4:15,-1:3,4:3,38,4:2,-1" +
":2,4,-1:12,4:5,-1:6,4,-1:12,101:4,-1,101:8,-1:3,101:6,-1:2,101,-1:12,101:5," +
"-1:6,101,-1:8,4,-1,4:15,-1:3,4:4,39,4,-1:2,4,-1:12,4:5,-1:6,4,-1:8,101,-1,1" +
"01:15,-1:3,101:6,-1:2,101,43,-1:11,101:5,-1:6,101,-1:8,4,-1,4:11,30,4:3,-1:" +
"3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:7,1,44:31,45,57,44:27,-1,4,-1,4:15,-1:3,4:" +
"4,27,4,-1:2,4,-1:12,4:5,-1:6,4,-1:7,1,47:9,48,47:5,48,47:8,48,47:30,48,47,4" +
"9,47:2,-1,4,-1,4:12,40,4:2,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:11," +
"41,4:3,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:8,142,143,4:5,-1:3,4:6," +
"-1:2,4,-1:12,4,60,4:3,-1:6,4,-1:8,71:57,-1,71:2,-1:14,95,-1:47,4,-1,4:12,67" +
",4:2,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:15,-1:3,4:4,70,4,-1:2,4,-" +
"1:12,4:5,-1:6,4,-1:8,4,-1,4:10,73,4:4,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:8" +
",4,-1,4:14,76,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:13,79,4,-1:3,4:6" +
",-1:2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:10,82,4:4,-1:3,4:6,-1:2,4,-1:12,4:5,-1" +
":6,4,-1:8,4,-1,4:8,84,4:6,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:9,86" +
",4:5,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:15,-1:3,4,79,4:4,-1:2,4,-" +
"1:12,4:5,-1:6,4,-1:8,4,-1,4:8,88,4:6,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:8," +
"4,-1,4:10,76,4:4,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:8,90,4:6,-1:3" +
",4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:8,92,4:6,-1:3,4:6,-1:2,4,-1:12,4:5" +
",-1:6,4,-1:8,4,-1,4:13,90,4,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:9," +
"94,4:5,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:15,-1:3,4:2,96,4:3,-1:2" +
",4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:15,-1:3,4:2,98,4:3,-1:2,4,-1:12,4:5,-1:6,4," +
"-1:8,4,-1,4:13,100,4,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:9,102,4:5" +
",-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:8,104,4:6,-1:3,4:6,-1:2,4,-1:" +
"12,4:5,-1:6,4,-1:8,4,-1,4:9,106,4:5,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4" +
",-1,4:14,107,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:13,141,4,-1:3,4:6" +
",-1:2,4,-1:12,4,168,111,4:2,-1:6,4,-1:8,4,-1,4:15,-1:3,4:6,-1:2,4,-1:12,4,1" +
"12,4:3,-1:6,4,-1:8,4,-1,4:9,113,4:5,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4" +
",-1,4:13,114,4,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:15,-1:3,4:3,146" +
",4:2,-1:2,4,-1:12,4,115,4:3,-1:6,4,-1:8,4,-1,4:8,116,4:6,-1:3,4:3,117,4:2,-" +
"1:2,4,-1:12,4,171,4:3,-1:6,4,-1:8,4,-1,4:14,118,-1:3,4:6,-1:2,4,-1:12,4:5,-" +
"1:6,4,-1:8,4,-1,4:15,-1:3,4:6,-1:2,4,-1:12,4,119,4:3,-1:6,4,-1:8,4,-1,4:11," +
"120,4:3,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:9,121,4:5,-1:3,4:6,-1:" +
"2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:15,-1:3,4:6,-1:2,4,-1:12,4,122,4:3,-1:6,4," +
"-1:8,4,-1,4:13,123,4,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:15,-1:3,4" +
":6,-1:2,4,-1:12,4,124,4:3,-1:6,4,-1:8,4,-1,4:15,-1:3,4,125,4:4,-1:2,4,-1:12" +
",4:5,-1:6,4,-1:8,4,-1,4:14,126,-1:3,4,112,4:4,-1:2,4,-1:12,4:5,-1:6,4,-1:8," +
"4,-1,4:12,127,4:2,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:14,128,-1:3," +
"4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:4,129,4:10,-1:3,4:6,-1:2,4,-1:12,4:" +
"5,-1:6,4,-1:8,4,-1,4:15,-1:3,4,112,4:4,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:" +
"11,130,4:3,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:14,131,-1:3,4:6,-1:" +
"2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:15,-1:3,4:4,132,4,-1:2,4,-1:12,4:5,-1:6,4," +
"-1:8,4,-1,4:13,144,4,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:12,157,4:" +
"2,-1:3,158,4:2,145,4:2,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:13,147,4,-1:3,4:" +
"6,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:15,-1:3,4,148,4:4,-1:2,4,-1:12,4:5,-1" +
":6,4,-1:8,4,-1,4:12,149,4:2,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:14" +
",150,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:13,151,4,-1:3,4:6,-1:2,4," +
"-1:12,4:5,-1:6,4,-1:8,4,-1,4:9,152,4:5,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:" +
"8,4,-1,4:8,153,4:6,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:15,-1:3,4,1" +
"54,4:4,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:11,159,4:3,-1:3,4:6,-1:2,4,-1:12" +
",4:5,-1:6,4,-1:8,4,-1,4:11,169,4:3,-1:3,4:6,-1:2,4,-1:12,4,160,4:3,-1:6,4,-" +
"1:8,4,-1,4:12,161,4:2,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:8,4,-1,4:15,-1:3," +
"4:6,-1:2,4,-1:12,4,162,4:3,-1:6,4,-1:8,4,-1,4:7,163,4:7,-1:3,4:6,-1:2,4,-1:" +
"12,4:5,-1:6,4,-1:8,4,-1,4:12,164,4:2,-1:3,4:6,-1:2,4,-1:12,4:5,-1:6,4,-1:8," +
"4,-1,4:15,-1:3,4:4,170,4,-1:2,4,-1:12,4:5,-1:6,4,-1:7");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

    {
	//System.out.printf("fin análisis Lexico Sintactico y Semantico OK...!! \n");
    return Token(sym.EOF);}
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{
   return new Symbol(sym.INT, (yyline), (yychar), yytext());
}
					case -3:
						break;
					case 3:
						{
    return new Symbol(sym.BINARYOP, (yyline), (yychar), yytext());
}
					case -4:
						break;
					case 4:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -5:
						break;
					case 5:
						{
    return new Symbol(sym.SEMI, (yyline), (yychar), yytext());
}
					case -6:
						break;
					case 6:
						{
    return new Symbol(sym.COLON, (yyline), (yychar), yytext());
}
					case -7:
						break;
					case 7:
						{
    return new Symbol(sym.COMMA, (yyline), (yychar), yytext());
}
					case -8:
						break;
					case 8:
						{
    ScreenOutManager.getInstance().addLine("Error lexico initial en <" + (yyline+1) + " , " + yychar + 
        "> no se reconoce " + yytext());
    yychar = 0;
}
					case -9:
						break;
					case 9:
						{}
					case -10:
						break;
					case 10:
						{
    return new Symbol(sym.ASSING, (yyline), (yychar), yytext());
}
					case -11:
						break;
					case 11:
						{
    return new Symbol(sym.UNARYOP, (yyline), (yychar), yytext());
}
					case -12:
						break;
					case 12:
						{
    return new Symbol(sym.LP, (yyline), (yychar), yytext());
}
					case -13:
						break;
					case 13:
						{
    return new Symbol(sym.RP, (yyline), (yychar), yytext());
}
					case -14:
						break;
					case 14:
						{
    return new Symbol(sym.LB, (yyline), (yychar), yytext());
}
					case -15:
						break;
					case 15:
						{
   return new Symbol(sym.RB, (yyline), (yychar), yytext());
}
					case -16:
						break;
					case 16:
						{
    return new Symbol(sym.LC, (yyline), (yychar), yytext());
}
					case -17:
						break;
					case 17:
						{
    return new Symbol(sym.RC, (yyline), (yychar), yytext());
}
					case -18:
						break;
					case 18:
						{
    return new Symbol(sym.DOT, (yyline), (yychar), yytext());
}
					case -19:
						break;
					case 19:
						{
    yychar = 0;
}
					case -20:
						break;
					case 20:
						{
    return new Symbol(sym.INT, (yyline), (yychar), yytext());
}
					case -21:
						break;
					case 21:
						{
    return new Symbol(sym.IF, (yyline), (yychar), yytext());
}
					case -22:
						break;
					case 22:
						{
    yybegin(COMENTCOMPLETE);
}
					case -23:
						break;
					case 23:
						{
    yybegin(COMENTLINE);
}
					case -24:
						break;
					case 24:
						{
   return new Symbol(sym.INT, (yyline), (yychar), yytext());
}
					case -25:
						break;
					case 25:
						{
   return new Symbol(sym.INT, (yyline), (yychar), yytext());
}
					case -26:
						break;
					case 26:
						{
    return new Symbol(sym.FOR, (yyline), (yychar), yytext());
}
					case -27:
						break;
					case 27:
						{
    return new Symbol(sym.TYPE, (yyline), (yychar), yytext());
}
					case -28:
						break;
					case 28:
						{
                                     return new Symbol(sym.STRING_LITERAL, (yyline), (yychar), yytext());
                                    }
					case -29:
						break;
					case 29:
						{
                                      return new Symbol(sym.CHAR_LITERAL, (yyline), (yychar), yytext());
                                    }
					case -30:
						break;
					case 30:
						{
    return new Symbol(sym.TYPE, (yyline), (yychar), yytext());
}
					case -31:
						break;
					case 31:
						{
   return new Symbol(sym.ELSE, (yyline), (yychar), yytext());
}
					case -32:
						break;
					case 32:
						{
    return new Symbol(sym.CONSTANTE_LOGICA, (yyline), (yychar), yytext());
}
					case -33:
						break;
					case 33:
						{ return new Symbol(sym.CASE, (yyline), (yychar), yytext());}
					case -34:
						break;
					case 34:
						{ return new Symbol(sym.NULL, (yyline), (yychar), yytext());}
					case -35:
						break;
					case 35:
						{
    return new Symbol(sym.BREAK, (yyline), (yychar), yytext());
}
					case -36:
						break;
					case 36:
						{ return new Symbol(sym.WHILE, (yyline), (yychar), yytext());}
					case -37:
						break;
					case 37:
						{
   return new Symbol(sym.STRUCT, (yyline), (yychar), yytext());
}
					case -38:
						break;
					case 38:
						{ return new Symbol(sym.SWITCH, (yyline), (yychar), yytext());}
					case -39:
						break;
					case 39:
						{
    return new Symbol(sym.RETURN, (yyline), (yychar), yytext());
}
					case -40:
						break;
					case 40:
						{ return new Symbol(sym.DEFAULT, (yyline), (yychar), yytext());}
					case -41:
						break;
					case 41:
						{
   return new Symbol(sym.CONT, (yyline), (yychar), yytext());
}
					case -42:
						break;
					case 42:
						{ return new Symbol(sym.DEFINE, (yyline), (yychar), yytext());}
					case -43:
						break;
					case 43:
						{ return new Symbol(sym.INCLUDE, (yyline), (yychar), yytext());}
					case -44:
						break;
					case 44:
						{}
					case -45:
						break;
					case 45:
						{
    ScreenOutManager.getInstance().addLine("Error lexico en estado comentario completo no se cerro las llaves <" + yyline + " , " + yychar + 
        "> no se reconoce " + yytext());
    yychar = 0;
}
					case -46:
						break;
					case 46:
						{
    yybegin(YYINITIAL);
}
					case -47:
						break;
					case 47:
						{    
}
					case -48:
						break;
					case 48:
						{
    ScreenOutManager.getInstance().addLine("Error lexico en estado linea <" + yyline + " , " + yychar + 
        "> no se reconoce " + yytext());
    yychar = 0;
}
					case -49:
						break;
					case 49:
						{
    yybegin(YYINITIAL);
}
					case -50:
						break;
					case 51:
						{
   return new Symbol(sym.INT, (yyline), (yychar), yytext());
}
					case -51:
						break;
					case 52:
						{
    return new Symbol(sym.BINARYOP, (yyline), (yychar), yytext());
}
					case -52:
						break;
					case 53:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -53:
						break;
					case 54:
						{
    ScreenOutManager.getInstance().addLine("Error lexico initial en <" + yyline + " , " + yychar + 
        "> no se reconoce " + yytext());
    yychar = 0;
}
					case -54:
						break;
					case 55:
						{
    return new Symbol(sym.ASSING, (yyline), (yychar), yytext());
}
					case -55:
						break;
					case 56:
						{
    return new Symbol(sym.UNARYOP, (yyline), (yychar), yytext());
}
					case -56:
						break;
					case 57:
						{
    ScreenOutManager.getInstance().addLine("Error lexico en estado comentario completo no se cerro las llaves <" + yyline + " , " + yychar + 
        "> no se reconoce " + yytext());
    yychar = 0;
}
					case -57:
						break;
					case 59:
						{
    return new Symbol(sym.BINARYOP, (yyline), (yychar), yytext());
}
					case -58:
						break;
					case 60:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -59:
						break;
					case 61:
						{
    ScreenOutManager.getInstance().addLine("Error lexico initial en <" + yyline + " , " + yychar + 
        "> no se reconoce " + yytext());
    yychar = 0;
}
					case -60:
						break;
					case 63:
						{
    return new Symbol(sym.BINARYOP, (yyline), (yychar), yytext());
}
					case -61:
						break;
					case 64:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -62:
						break;
					case 66:
						{
    return new Symbol(sym.BINARYOP, (yyline), (yychar), yytext());
}
					case -63:
						break;
					case 67:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -64:
						break;
					case 69:
						{
    return new Symbol(sym.BINARYOP, (yyline), (yychar), yytext());
}
					case -65:
						break;
					case 70:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -66:
						break;
					case 72:
						{
    return new Symbol(sym.BINARYOP, (yyline), (yychar), yytext());
}
					case -67:
						break;
					case 73:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -68:
						break;
					case 75:
						{
    return new Symbol(sym.BINARYOP, (yyline), (yychar), yytext());
}
					case -69:
						break;
					case 76:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -70:
						break;
					case 78:
						{
    return new Symbol(sym.BINARYOP, (yyline), (yychar), yytext());
}
					case -71:
						break;
					case 79:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -72:
						break;
					case 81:
						{
    return new Symbol(sym.BINARYOP, (yyline), (yychar), yytext());
}
					case -73:
						break;
					case 82:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -74:
						break;
					case 84:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -75:
						break;
					case 86:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -76:
						break;
					case 88:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -77:
						break;
					case 90:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -78:
						break;
					case 92:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -79:
						break;
					case 94:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -80:
						break;
					case 96:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -81:
						break;
					case 98:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -82:
						break;
					case 100:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -83:
						break;
					case 102:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -84:
						break;
					case 104:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -85:
						break;
					case 106:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -86:
						break;
					case 107:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -87:
						break;
					case 108:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -88:
						break;
					case 109:
						{
    ScreenOutManager.getInstance().addLine("Error lexico initial en <" + yyline + " , " + yychar + 
        "> no se reconoce " + yytext());
    yychar = 0;
}
					case -89:
						break;
					case 111:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -90:
						break;
					case 112:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -91:
						break;
					case 113:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -92:
						break;
					case 114:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -93:
						break;
					case 115:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -94:
						break;
					case 116:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -95:
						break;
					case 117:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -96:
						break;
					case 118:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -97:
						break;
					case 119:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -98:
						break;
					case 120:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -99:
						break;
					case 121:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -100:
						break;
					case 122:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -101:
						break;
					case 123:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -102:
						break;
					case 124:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -103:
						break;
					case 125:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -104:
						break;
					case 126:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -105:
						break;
					case 127:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -106:
						break;
					case 128:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -107:
						break;
					case 129:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -108:
						break;
					case 130:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -109:
						break;
					case 131:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -110:
						break;
					case 132:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -111:
						break;
					case 133:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -112:
						break;
					case 134:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -113:
						break;
					case 135:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -114:
						break;
					case 136:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -115:
						break;
					case 137:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -116:
						break;
					case 138:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -117:
						break;
					case 139:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -118:
						break;
					case 140:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -119:
						break;
					case 141:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -120:
						break;
					case 142:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -121:
						break;
					case 143:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -122:
						break;
					case 144:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -123:
						break;
					case 145:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -124:
						break;
					case 146:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -125:
						break;
					case 147:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -126:
						break;
					case 148:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -127:
						break;
					case 149:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -128:
						break;
					case 150:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -129:
						break;
					case 151:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -130:
						break;
					case 152:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -131:
						break;
					case 153:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -132:
						break;
					case 154:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -133:
						break;
					case 155:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -134:
						break;
					case 156:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -135:
						break;
					case 157:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -136:
						break;
					case 158:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -137:
						break;
					case 159:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -138:
						break;
					case 160:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -139:
						break;
					case 161:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -140:
						break;
					case 162:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -141:
						break;
					case 163:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -142:
						break;
					case 164:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -143:
						break;
					case 165:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -144:
						break;
					case 166:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -145:
						break;
					case 167:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -146:
						break;
					case 168:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -147:
						break;
					case 169:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -148:
						break;
					case 170:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -149:
						break;
					case 171:
						{
   return new Symbol(sym.ID, (yyline), (yychar), yytext());
}
					case -150:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
