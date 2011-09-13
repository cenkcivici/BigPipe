package com.prime.bigpipe.web.component.tag;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.processor.attr.IAttrProcessor;

public class PrimeDialect extends AbstractDialect {

	@Override
	public String getPrefix() {
		return "prime";
	}

	@Override
	public boolean isLenient() {
		return false;
	}

	@Override
	public Set<IAttrProcessor> getAttrProcessors() {
		final Set<IAttrProcessor> attrProcessors = new HashSet<IAttrProcessor>();
		attrProcessors.add(new ComponentAttributeProcessor());
		return attrProcessors;
	}
}
