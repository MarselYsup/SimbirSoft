import org.junit.Assert;
import org.junit.Test;
import ru.simbir.soft.services.HTMLParser;

public class HTMLParserTest {
    private final HTMLParser parser = new HTMLParser();
    @Test
    public void testForParsing() {
        String url = "http://info.cern.ch/hypertext/DataSources/Top.html";
        String expectedLine= "General Overview " +
                "There is no \"top\" to the World-Wide Web. You can look at it from many points of view. If you have no other bias, here are some ways of looking for information. " +
                "by Subject " +
                "A classification by subject of interest. Incomplete but easiest to use. " +
                "by Type " +
                "Looking by type of service (access protocol, etc) may allow to find things if you know what you are looking for. " +
                "If you have to use a \"top\" node, we recommend either this node or the subject list. See also: About the W3 project.";
        Assert.assertEquals(expectedLine, parser.parse(url));
    }
    @Test
    public void testForParsing2() {
        String url = "http://x.com/";
        String expectedLine= "x";
        Assert.assertEquals(expectedLine, parser.parse(url));
    }
    @Test
    public void testForParsing3() {
        String url = "http://www.toad.com/grokmail/index.html";
        String expectedLine="Grokmail -- a mail reader's prioritizer " +
                "Grokmail is prototype research software. A brief description of its motivation is available. " +
                "So far the software is not available, since it's not working well enough that ordinary people could help with it. When we release it, it will be free software, under the GNU General Public License. "
                +
                "Grokmail maintains a database separate from your email, but synchronized with it. Hooks for the MH mail handling system, and the MH-E emacs interface to it, are being built. It's designed to be pluggable into any email reader, eventually.";
        Assert.assertEquals(expectedLine, parser.parse(url));
    }
    @Test
    public void testForParsing4() {
        String url = "http://info.cern.ch/hypertext/WWW/WhatIs.html";
        String expectedLine= "What is HyperText " +
                "Hypertext is text which is not constrained to be linear. " +
                "Hypertext is text which contains links to other texts. The term was coined by Ted Nelson around 1965 (see History ). " +

                "HyperMedia is a term used for hypertext which is not constrained to be text: it can include graphics, video and sound , for example. Apparently Ted Nelson was the first to use this term too. " +

                "Hypertext and HyperMedia are concepts, not products. " +

                "See also: " +

                "A list of terms used in hypertext litterature. " +
                "Conferences " +
                "Commercial (and academic) products " +
                "A newsgroup on hypertext, \"alt.hypertext\" . " +
                "WorldWideWeb is a project which uses hypertext concepts. " +
                "Standards .";
        Assert.assertEquals(expectedLine, parser.parse(url));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testForParsing5() {
        String url = "https://sadsad/sadfsadsa";
        parser.parse(url);
    }

}
