package com.tortuousroad.framework.spring.web.bind.support;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

/**
 * IntConvertEditor
 */
public class IntConvertEditor extends PropertyEditorSupport {
	
	/* (non-Javadoc)
	 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text)) {
			try {
				setValue(Integer.parseInt(text));
			} catch (Exception e) {
				IllegalArgumentException iae = new IllegalArgumentException("Could not parse int: " + e.getMessage());
				iae.initCause(e);
				throw iae;
			}
		}
	}


}
