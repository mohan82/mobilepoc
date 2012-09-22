package mustachejava.springframework.web.viewresolvers;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheException;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: mohan
 * Date: 22/09/12
 * Time: 6:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class SpringTemplateMustacheFactory extends DefaultMustacheFactory implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;
    private String prefix;
    private String suffix;

    public SpringTemplateMustacheFactory() {
    }

    public SpringTemplateMustacheFactory(String prefix, String suffix) {
        this.suffix = suffix;
        this.prefix = prefix;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    String getPrefix(String fileName) {
        if (!fileName.startsWith(prefix)) {
            fileName = prefix + fileName;
        }
        return fileName;
    }

    String getSuffix(String fileName) {
        if (!fileName.endsWith(suffix)) {
            fileName = fileName + suffix;
        }
        return fileName;
    }

    public boolean doesMustacheResourceExist(String fileName) {
        String appendedFileName = getSuffix(getPrefix(fileName));
        Resource resource = resourceLoader.getResource(appendedFileName);
        if (resource.exists())
            return true;
        else
            return false;

    }

    @Override
    public Reader getReader(String fileName) {
        String appendedFileName = getSuffix(getPrefix(fileName));
        Resource resource = resourceLoader.getResource(appendedFileName);
        try {
            if (resource.exists()) {
                return new BufferedReader(new InputStreamReader(resource.getInputStream()));
            }
        } catch (IOException e) {
            throw new MustacheException("Failed to load template: "
                    + fileName, e);
        }
        throw new MustacheException("No template exists named: " + fileName);
    }
}
