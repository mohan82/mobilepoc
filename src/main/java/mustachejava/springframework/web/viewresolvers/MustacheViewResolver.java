/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mustachejava.springframework.web.viewresolvers;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

import java.io.Reader;


/**
 * @author Mohan Ambalavanan
 */
public class MustacheViewResolver extends AbstractTemplateViewResolver implements ViewResolver, ServletContextAware, InitializingBean {

    private SpringTemplateMustacheFactory springTemplateMustacheFactory;

    public MustacheViewResolver(SpringTemplateMustacheFactory springTemplateMustacheFactory) {
        this();
        this.springTemplateMustacheFactory = springTemplateMustacheFactory;
    }

    public MustacheViewResolver() {
        setViewClass(MustacheView.class);
    }

    @Override
    protected Class<?> requiredViewClass() {
        return MustacheView.class;

    }


    @Override
    protected AbstractUrlBasedView buildView(String viewName) throws Exception {

        final MustacheView view = (MustacheView) super.buildView(viewName);
        String fileName = view.getUrl();
        if (springTemplateMustacheFactory.doesMustacheResourceExist(fileName)) {
            Reader reader = springTemplateMustacheFactory.getReader(fileName);
            Mustache mustache = springTemplateMustacheFactory.compile(reader, viewName);
            view.setMustache(mustache);
            return view;
        } else {
            //let other resolvers take care if we cant resolve it.
            return null;
        }
    }


    @Override
    public void afterPropertiesSet() throws Exception {
    }
}
