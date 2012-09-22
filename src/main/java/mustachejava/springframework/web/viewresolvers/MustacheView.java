package mustachejava.springframework.web.viewresolvers;

import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.mustachejava.Mustache;
import org.springframework.web.servlet.view.AbstractTemplateView;


/**
 * @author Mohan Ambalavanan
 */
public class MustacheView extends AbstractTemplateView {

    private Mustache mustache;

    public void setMustache(Mustache mustache) {
        this.mustache = mustache;
    }


    @Override
    protected void renderMergedTemplateModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType(getContentType());
        final Writer writer = response.getWriter();
        try {
            mustache.execute(writer, model);
        } finally {
            writer.flush();
        }
    }

}
