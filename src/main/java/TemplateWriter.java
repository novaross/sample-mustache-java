import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.StringWriter;

@Data
@Slf4j
public class TemplateWriter {

    private TemplateContext templateContext;

    public void writeTemplate() {
        MustacheFactory mustacheFactory = new DefaultMustacheFactory();
        Mustache mustache = mustacheFactory.compile("template/PersonUsage.mustache");
        StringWriter writer = new StringWriter();
        try {
            mustache.execute(writer, templateContext).flush();
            String data = writer.toString();
            log.debug("Data: {}", data);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
