package com.prime.bigpipe.web.component.tag;

import java.util.Set;

import net.sf.json.JSONObject;

import org.springframework.context.ApplicationContext;
import org.thymeleaf.Arguments;
import org.thymeleaf.processor.applicability.AttrApplicability;
import org.thymeleaf.processor.attr.AbstractUnescapedTextChildModifierAttrProcessor;
import org.thymeleaf.spring3.context.SpringWebContext;
import org.thymeleaf.templateresolver.TemplateResolution;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.prime.bigpipe.web.component.BigPipeExecutionService;

public class BigPipeFlushAttributeProcessor extends AbstractUnescapedTextChildModifierAttrProcessor {

	@Override
	public Set<AttrApplicability> getAttributeApplicabilities() {
		return AttrApplicability.createSetForAttrName("flush");
	}

	@Override
	public Integer getPrecedence() {
		return Integer.valueOf(3000);
	}

	@Override
	protected final String getText(final Arguments arguments, final TemplateResolution templateResolution, final Document document, final Element element, final Attr attribute, final String attributeName,
			final String componentName) {

		final ApplicationContext appContext = ((SpringWebContext) arguments.getContext()).getApplicationContext();
		final BigPipeExecutionService bigPipeExecutionService = appContext.getBean(BigPipeExecutionService.class);

		StringBuilder builder = new StringBuilder();
		try {
			while (bigPipeExecutionService.getCountOfRunnables() > 0) {
				JSONObject jsonObject = bigPipeExecutionService.getNextFinishedComponent();

				builder.append("<script>bigpipe.registerComponent(");
				builder.append(jsonObject.toString());
				builder.append(");</script>");

				bigPipeExecutionService.oneLessRunnable();
			}
			return builder.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
