package zenuo.gogo.core.processor.impl;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zenuo.gogo.TestEnvironment;
import zenuo.gogo.core.config.Constants;
import zenuo.gogo.exception.SearchException;
import zenuo.gogo.model.SearchResponse;
import zenuo.gogo.util.JsonUtils;

import java.io.IOException;
import java.util.regex.Matcher;

public class GoogleSearchResultProviderImplTest extends TestEnvironment {

    @Autowired
    private GoogleSearchResultProviderImpl searchResultProvider;

    @Test
    public void request() throws IOException {

        final Document document = searchResultProvider.httpGet("udp", 1);
        if (document != null) {
            System.out.println(document.html());
        }
    }

    @Test
    public void search() throws SearchException {
        final SearchResponse response = searchResultProvider.search0("udp", 1);
        if (response.getEntries() != null) {
            response.getEntries().forEach(e -> System.out.println(e.getName()));
        }
    }

    @Test
    public void response() throws SearchException {
        final SearchResponse searchResponse = searchResultProvider.search0("udp", 2);
        final String json = JsonUtils.toJson(searchResponse);
        System.out.println(json);
    }

    @Test
    public void stats() {
        final Matcher matcher = Constants.STATS_RESULTS_PATTERN.matcher("About 41,400,000 results (0.31 seconds)");
        System.out.println(matcher.find());
        System.out.println(matcher.groupCount());
        System.out.println(matcher.group(1));
        System.out.println(matcher.group(2));
    }
}
