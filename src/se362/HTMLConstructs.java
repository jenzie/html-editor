package se362;

/**
 * Enum for HTML Constructs.
 * Each entry has a start and end tag
 * @author Kevin Mulligan, kam9115@rit.edu
 *
 */
public enum HTMLConstructs {
    HTML("<html>", "</html>"), HEAD("<head>","</head>"),
	PRE("<pre>", "</pre>"),BODY("<body>", "</body>"),
	TITLE("<title>", "</title>"), H1("<h1>", "</h1>"),
	H2("<h2>", "</h2>"), H3("<h3>", "</h3>"), H4("<h4>", "</h4>"),
	H5("<h5>", "</h5>"), H6("<h6>", "</h6>"), B("<b>", "</b>"),
	I("<i>", "</i>"), U("<u>", "</u>"), TT("<tt>", "</tt>"),
	CITE("<cite>", "</cite>"), EM("<em>", "</em>"), P("<p>", "</p>"),
	STRONG("<strong>", "</strong>"),DL("<dl>", "</dl>"),
	OL("<ol>", "</ol>"), UL("<ul>", "</ul>"),
	LI("<li>", "</li>"), TABLE("<table>", "</table>"),
	TR("<tr>", "</tr>"), TD("<td>", "</td>"), TH("<th>", "</th>"),
	FORM("<form>", "</form>"), FRAMESET("<frameset>", "</frameset>"),
	NOFRAMES("<noframes>", "</noframes>"),;

	/**
	BR("<br>"), DT("<dt>"), DD("<dd>"), HR("<hr>"), FRAME("<frame>"),
	OPTION("<option>"),
	 */

    private final String openTag;
    private final String closeTag;
    
    HTMLConstructs(String open, String close) {
        this.openTag = open;
        this.closeTag = close;
    }
    
    public String getOpenTag() {
        return this.openTag;
    }
    
    public String getCloseTag() {
        return this.closeTag;
    }
}
