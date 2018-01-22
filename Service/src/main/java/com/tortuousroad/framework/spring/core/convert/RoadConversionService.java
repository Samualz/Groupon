package com.tortuousroad.framework.spring.core.convert;

import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.core.convert.support.DefaultConversionService;

import com.tortuousroad.framework.spring.core.convert.support.ObjectToStringConverter;
import com.tortuousroad.framework.spring.core.convert.support.StringToNumberConverterFactory;

/**
 * RoadConversionService
 */
public class RoadConversionService extends DefaultConversionService {
	
	public RoadConversionService() {
		super();
		addDefaultConverters(this);
	}


	/**
	 * Add converters appropriate for most environments.
	 * @param converterRegistry the registry of converters to add to (must also be castable to ConversionService)
	 * @throws ClassCastException if the converterRegistry could not be cast to a ConversionService
	 */
	public static void addDefaultConverters(ConverterRegistry converterRegistry) {
		DefaultConversionService.addDefaultConverters(converterRegistry);
		addNumberConverters(converterRegistry);
	}

	// internal helpers
	private static void addNumberConverters(ConverterRegistry converterRegistry) {
		converterRegistry.removeConvertible(String.class, Number.class);
		converterRegistry.addConverterFactory(new StringToNumberConverterFactory());
		converterRegistry.addConverter(Number.class, String.class, new ObjectToStringConverter());
	}

}
