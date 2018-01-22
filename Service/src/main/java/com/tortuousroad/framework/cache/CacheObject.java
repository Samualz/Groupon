package com.tortuousroad.framework.cache;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * CacheObject
 */
public abstract class CacheObject implements Serializable {

	private static final long serialVersionUID = 3752520699855146119L;

	@Getter @Setter private Object id;

}
