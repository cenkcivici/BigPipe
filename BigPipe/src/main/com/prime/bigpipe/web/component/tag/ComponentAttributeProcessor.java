package com.prime.bigpipe.web.component.tag;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.thymeleaf.Arguments;
import org.thymeleaf.processor.applicability.AttrApplicability;
import org.thymeleaf.processor.attr.AbstractUnescapedTextChildModifierAttrProcessor;
import org.thymeleaf.spring3.context.SpringWebContext;
import org.thymeleaf.templateresolver.TemplateResolution;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.prime.bigpipe.web.component.ComponentContextHolder;
import com.prime.bigpipe.web.component.ComponentDispatcher;

public class ComponentAttributeProcessor extends AbstractUnescapedTextChildModifierAttrProcessor {

	@Override
	public Set<AttrApplicability> getAttributeApplicabilities() {
		return AttrApplicability.createSetForAttrName("component");
	}

	@Override
	public Integer getPrecedence() {
		return Integer.valueOf(2000);
	}

	@Override
	protected final String getText(final Arguments arguments, final TemplateResolution templateResolution, final Document document, final Element element, final Attr attribute, final String attributeName,
			final String componentName) {

		final ApplicationContext appContext = ((SpringWebContext) arguments.getContext()).getApplicationContext();
		final ComponentDispatcher dispatcher = appContext.getBean(ComponentDispatcher.class);
		final ComponentContextHolder contextHolder = appContext.getBean(ComponentContextHolder.class);

		HttpServletRequest request = contextHolder.getRequest();
		Map<String, Object> params = new HashMap<String, Object>();

		String content = dispatcher.dispatch(componentName, params, request);
		if (StringUtils.isNotEmpty(content)) {
			content = content.replaceFirst("<!DOCTYPE\\s+html\\s*>", "");
		}
		return content;
	}

}
