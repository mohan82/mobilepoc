package mustachejava.springframework.web.viewresolvers;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created with IntelliJ IDEA.
 * User: mohan
 * Date: 22/09/12
 * Time: 6:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class ViewReader implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;
    private String prefix;
    private String suffix;

    public ViewReader() {
    }

    public  ViewReader(String prefix, String suffix) {
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

    protected Reader getReader(String fileName) throws Exception {
        String appendedFileName = getSuffix(getPrefix(fileName));
        Resource resource = resourceLoader.getResource(appendedFileName);
        if (resource.exists()) {
            return new BufferedReader(new InputStreamReader(resource.getInputStream()));
        }
        throw new FileNotFoundException(appendedFileName);
    }

}
