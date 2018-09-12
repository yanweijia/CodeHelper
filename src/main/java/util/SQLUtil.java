package util;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;


/**
 * sql format util
 * ref: http://www.mamicode.com/info-detail-1429225.html
 *
 * @Create on 2018/06/03
 */
public class SQLUtil {
    private static final Set<String> BEGIN_CLAUSES = new HashSet<>();
    private static final Set<String> END_CLAUSES = new HashSet<>();
    private static final Set<String> LOGICAL = new HashSet<>();
    private static final Set<String> QUANTIFIERS = new HashSet<>();
    private static final Set<String> DML = new HashSet<>();
    private static final Set<String> MISC = new HashSet<>();
    static final String indentString = "    ";
    static final String initial = "\n    ";

    public static String format(String source) {
        return new FormatProcess(source).perform().trim();
    }

    static {
        BEGIN_CLAUSES.add("left");
        BEGIN_CLAUSES.add("right");
        BEGIN_CLAUSES.add("inner");
        BEGIN_CLAUSES.add("outer");
        BEGIN_CLAUSES.add("group");
        BEGIN_CLAUSES.add("order");

        END_CLAUSES.add("where");
        END_CLAUSES.add("set");
        END_CLAUSES.add("having");
        END_CLAUSES.add("join");
        END_CLAUSES.add("from");
        END_CLAUSES.add("by");
        END_CLAUSES.add("join");
        END_CLAUSES.add("into");
        END_CLAUSES.add("union");

        LOGICAL.add("and");
        LOGICAL.add("or");
        LOGICAL.add("when");
        LOGICAL.add("else");
        LOGICAL.add("end");

        QUANTIFIERS.add("in");
        QUANTIFIERS.add("all");
        QUANTIFIERS.add("exists");
        QUANTIFIERS.add("some");
        QUANTIFIERS.add("any");

        DML.add("insert");
        DML.add("update");
        DML.add("delete");

        MISC.add("select");
        MISC.add("on");
    }

    private static class FormatProcess {
        boolean beginLine = true;
        boolean afterBeginBeforeEnd = false;
        boolean afterByOrSetOrFromOrSelect = false;
        boolean afterValues = false;
        boolean afterOn = false;
        boolean afterBetween = false;
        boolean afterInsert = false;
        int inFunction = 0;
        int parensSinceSelect = 0;
        private LinkedList<Integer> parenCounts = new LinkedList<Integer>();
        private LinkedList<Boolean> afterByOrFromOrSelects = new LinkedList<Boolean>();

        int indent = 1;

        StringBuffer result = new StringBuffer();
        StringTokenizer tokens;
        String lastToken;
        String token;
        String lcToken;

        public FormatProcess(String sql) {
            tokens = new StringTokenizer(sql, "()+*/-=<>‘`\"[], \n\r\f\t", true);
        }

        public String perform() {
            result.append("\n    ");

            while (tokens.hasMoreTokens()) {
                token = tokens.nextToken();
                lcToken = token.toLowerCase();

                if ("‘".equals(token)) {
                    String t;
                    do {
                        t = tokens.nextToken();
                        token += t;
                    } while ((!"‘".equals(t)) && (tokens.hasMoreTokens()));
                } else if ("\"".equals(token)) {
                    String t;
                    do {
                        t = tokens.nextToken();
                        token += t;
                    } while (!"\"".equals(t));
                }

                if ((afterByOrSetOrFromOrSelect) && (",".equals(token))) {
                    commaAfterByOrFromOrSelect();
                } else if ((afterOn) && (",".equals(token))) {
                    commaAfterOn();
                } else if ("(".equals(token)) {
                    openParen();
                } else if (")".equals(token)) {
                    closeParen();
                } else if (BEGIN_CLAUSES.contains(lcToken)) {
                    beginNewClause();
                } else if (END_CLAUSES.contains(lcToken)) {
                    endNewClause();
                } else if ("select".equals(lcToken)) {
                    select();
                } else if (DML.contains(lcToken)) {
                    updateOrInsertOrDelete();
                } else if ("values".equals(lcToken)) {
                    values();
                } else if ("on".equals(lcToken)) {
                    on();
                } else if ((afterBetween) && (lcToken.equals("and"))) {
                    misc();
                    afterBetween = false;
                } else if (LOGICAL.contains(lcToken)) {
                    logical();
                } else if (isWhitespace(token)) {
                    white();
                } else {
                    misc();
                }

                if (!isWhitespace(token)) {
                    lastToken = lcToken;
                }
            }

            return result.toString();
        }

        private void commaAfterOn() {
            out();
            indent -= 1;
            newline();
            afterOn = false;
            afterByOrSetOrFromOrSelect = true;
        }

        private void commaAfterByOrFromOrSelect() {
            out();
            newline();
        }

        private void logical() {
            if ("end".equals(lcToken)) {
                indent -= 1;
            }
            newline();
            out();
            beginLine = false;
        }

        private void on() {
            indent += 1;
            afterOn = true;
            newline();
            out();
            beginLine = false;
        }

        private void misc() {
            out();
            if ("between".equals(lcToken)) {
                afterBetween = true;
            }
            if (afterInsert) {
                newline();
                afterInsert = false;
            } else {
                beginLine = false;
                if ("case".equals(lcToken)) {
                    indent += 1;
                }
            }
        }

        private void white() {
            if (!beginLine) {
                result.append(" ");
            }
        }

        private void updateOrInsertOrDelete() {
            out();
            indent += 1;
            beginLine = false;
            if ("update".equals(lcToken)) {
                newline();
            }
            if ("insert".equals(lcToken)) {
                afterInsert = true;
            }
        }

        private void select() {
            out();
            indent += 1;
            newline();
            parenCounts.addLast(new Integer(parensSinceSelect));
            afterByOrFromOrSelects.addLast(Boolean.valueOf(afterByOrSetOrFromOrSelect));
            parensSinceSelect = 0;
            afterByOrSetOrFromOrSelect = true;
        }

        private void out() {
            result.append(token);
        }

        private void endNewClause() {
            if (!afterBeginBeforeEnd) {
                indent -= 1;
                if (afterOn) {
                    indent -= 1;
                    afterOn = false;
                }
                newline();
            }
            out();
            if (!"union".equals(lcToken)) {
                indent += 1;
            }
            newline();
            afterBeginBeforeEnd = false;
            afterByOrSetOrFromOrSelect = (("by".equals(lcToken)) || ("set".equals(lcToken))
                    || ("from".equals(lcToken)));
        }

        private void beginNewClause() {
            if (!afterBeginBeforeEnd) {
                if (afterOn) {
                    indent -= 1;
                    afterOn = false;
                }
                indent -= 1;
                newline();
            }
            out();
            beginLine = false;
            afterBeginBeforeEnd = true;
        }

        private void values() {
            indent -= 1;
            newline();
            out();
            indent += 1;
            newline();
            afterValues = true;
        }

        private void closeParen() {
            parensSinceSelect -= 1;
            if (parensSinceSelect < 0) {
                indent -= 1;
                parensSinceSelect = ((Integer) parenCounts.removeLast()).intValue();
                afterByOrSetOrFromOrSelect = ((Boolean) afterByOrFromOrSelects.removeLast()).booleanValue();
            }
            if (inFunction > 0) {
                inFunction -= 1;
                out();
            } else {
                if (!afterByOrSetOrFromOrSelect) {
                    indent -= 1;
                    newline();
                }
                out();
            }
            beginLine = false;
        }

        private void openParen() {
            if ((isFunctionName(lastToken)) || (inFunction > 0)) {
                inFunction += 1;
            }
            beginLine = false;
            if (inFunction > 0) {
                out();
            } else {
                out();
                if (!afterByOrSetOrFromOrSelect) {
                    indent += 1;
                    newline();
                    beginLine = true;
                }
            }
            parensSinceSelect += 1;
        }

        private static boolean isFunctionName(String tok) {
            char begin = tok.charAt(0);
            boolean isIdentifier = (Character.isJavaIdentifierStart(begin)) || ('"' == begin);
            return (isIdentifier) && (!LOGICAL.contains(tok))
                    && (!END_CLAUSES.contains(tok))
                    && (!QUANTIFIERS.contains(tok)) && (!DML.contains(tok))
                    && (!MISC.contains(tok));
        }

        private static boolean isWhitespace(String token) {
            return " \n\r\f\t".indexOf(token) >= 0;
        }

        private void newline() {
            result.append("\n");
            for (int i = 0; i < indent; i++) {
                result.append("    ");
            }
            beginLine = true;
        }
    }

    public static void main(String[] args) {
        System.out.println(SQLUtil
                .format("select aa,bb,cc,dd from ta1,(select ee,ff,gg from ta2 where ee=ff) ta3 where aa=bb and cc=dd group by dd order by createtime desc limit 3 "));
    }
}
