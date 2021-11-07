package context;

import org.jmantic.api.context.UncheckedScContext;
import org.jmantic.scmemory.model.element.link.LinkContentType;
import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.model.element.link.ScLinkFloat;
import org.jmantic.scmemory.model.element.link.ScLinkInteger;
import org.jmantic.scmemory.model.element.link.ScLinkString;
import org.jmantic.scmemory.websocketmemory.sync.SyncScMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author artrayme
 * 11/6/21
 */
public class ScLinkOperationsTest {
    private UncheckedScContext scContext;

    @BeforeEach
    public void setUp() throws URISyntaxException {
        scContext = new UncheckedScContext(SyncScMemory.getSyncScMemory(new URI("ws://localhost:8090/ws_json")));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleIntegerLink() {
        int content = 5;
        ScLinkInteger link = scContext.createIntegerLink(LinkType.LINK, content);
        assertEquals(LinkType.LINK, link.getType());
        assertEquals(LinkContentType.INTEGER, link.getContentType());
        assertEquals(content, link.getContent());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleFloatLink() {
        float content = 42.1f;
        ScLinkFloat link = scContext.createFloatLink(LinkType.LINK, content);
        assertEquals(LinkType.LINK, link.getType());
        assertEquals(LinkContentType.FLOAT, link.getContentType());
        assertEquals(content, link.getContent());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleStringLink() {
        String content = "Hello";
        ScLinkString link = scContext.createStringLink(LinkType.LINK, content);
        assertEquals(LinkType.LINK, link.getType());
        assertEquals(LinkContentType.STRING, link.getContentType());
        assertEquals(content, link.getContent());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createMultipleLinks() {
        int content1 = 2;
        int content2 = 3;
        ScLinkInteger link1 = scContext.createIntegerLink(LinkType.LINK, content1);
        ScLinkInteger link2 = scContext.createIntegerLink(LinkType.LINK, content2);
        assertEquals(link1.getType(), link2.getType());
        assertEquals(link1.getContentType(), link2.getContentType());
        assertEquals(content1 + content2, link1.getContent() + link2.getContent());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void deleteLink() {
        int content = 5;
        ScLinkInteger link = scContext.createIntegerLink(LinkType.LINK, content);
        boolean result = scContext.deleteElement(link);
        assertTrue(result);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void getContentFromIntegerLink() {
        int content = 5;
        ScLinkInteger link = scContext.createIntegerLink(LinkType.LINK, content);
        assertEquals(content, scContext.getIntegerLinkContent(link));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void getContentFromFloatLink() {
        float content = 42.1f;
        ScLinkFloat link = scContext.createFloatLink(LinkType.LINK, content);
        assertEquals(content, scContext.getFloatLinkContent(link));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void getContentFromStringLink() {
        String content = "Hello";
        ScLinkString link = scContext.createStringLink(LinkType.LINK, content);
        assertEquals(content, scContext.getStringLinkContent(link));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void setContentToIntegerLink() {
        int oldContent = 5;
        int newContent = 13;
        ScLinkInteger link = scContext.createIntegerLink(LinkType.LINK, oldContent);
        scContext.setIntegerLinkContent(link, newContent);
        assertEquals(newContent, link.getContent());
        assertEquals(newContent, scContext.getIntegerLinkContent(link));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void setContentToFloatLink() {
        float oldContent = 42.1f;
        float newContent = 123.456f;
        ScLinkFloat link = scContext.createFloatLink(LinkType.LINK, oldContent);
        scContext.setFloatLinkContent(link, newContent);
        assertEquals(newContent, link.getContent());
        assertEquals(newContent, scContext.getFloatLinkContent(link));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void setContentToStringLink() {
        String oldContent = "Hello";
        String newContent = "World";
        ScLinkString link = scContext.createStringLink(LinkType.LINK, oldContent);
        scContext.setStringLinkContent(link, newContent);
        assertEquals(newContent, link.getContent());
        assertEquals(newContent, scContext.getStringLinkContent(link));
    }
}
