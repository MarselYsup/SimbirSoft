import org.junit.Assert;
import org.junit.Test;
import ru.simbir.soft.services.StringParser;

public class StringParserTest {
    private final StringParser parser = new StringParser();
    @Test
    public void testForParsing1() {
        String actualLine = null;
        Assert.assertEquals(null, parser.parse(actualLine));
    }
    @Test
    public void testForParsing2() {
        String actualLine = "";
        Assert.assertEquals("", parser.parse(actualLine));
    }
    @Test
    public void testForParsing3() {
        String actualLine = "«Спасти» продукт";
        Assert.assertEquals(" Спасти  продукт", parser.parse(actualLine));
    }
    @Test
    public void testForParsing4() {
        String actualLine = "English,Japan,Germany,Russian";
        Assert.assertEquals("English Japan Germany Russian", parser.parse(actualLine));
    }
    @Test
    public void testForParsing5() {
        String actualLine = "@,!&???";
        Assert.assertEquals("       ", parser.parse(actualLine));
    }
    @Test
    public void testForParsing6() {
        String actualLine = "Вы видите четкий результат на всех этапах" +
                " в предсказуемые сроки";
        Assert.assertEquals("Вы видите четкий результат на всех этапах" +
                " в предсказуемые сроки", parser.parse(actualLine));
    }
}
