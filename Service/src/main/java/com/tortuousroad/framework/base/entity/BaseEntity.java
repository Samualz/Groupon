package com.tortuousroad.framework.base.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 实体基类
 */
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = -6001916081185698608L;
	
	@Getter @Setter private Long id;
	@Getter @Setter private String routerCall;

	/**
	 * 获取给定实体集合的ID集合
	 * @param entities 实体集合
	 * @param <T> 实体类型
	 * @return ID集合
	 */
	public static <T extends BaseEntity> List<Long> idList(List<T> entities) {
//		List<Long> ids = new ArrayList<>();
//		entities.forEach(entity -> ids.add(entity.getId()));
//		for (T entity : entities) {
//			ids.add(entity.getId());
//		}
//		return ids;
		return entities.stream().map(entity -> entity.getId())
				.collect(Collectors.toList());
	}

	/**
	 * 将给定的实体集合转换成为实体ID为Key,实体为Value的Map
	 * @param entities 实体集合
	 * @param <T> 实体类型
     * @return ID-实体Map
     */
	public static <T extends BaseEntity> Map<Long, T> idEntityMap(List<T> entities) {
//		Map<Long, T> idEntityMap = new HashMap<>();
//		entities.forEach(entity -> idEntityMap.put(entity.getId(), entity));
//		return idEntityMap;

		Map<Long, T> idMap = entities.stream().collect(Collectors.toMap(T::getId, entity -> entity));
		return idMap;
	}

	/**
	 * toString方法，返回属性名称及值
	 * @return	属性名称及值，格式：[name]张三 [sex]男
	 */
	public String toString(){
		StringBuffer result = new StringBuffer();
		try {
			Class<? extends Object> clazz = this.getClass();
			Field [] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				String fieldName = field.getName();
				if ("serialVersionUID".equals(fieldName)) {
					continue;
				}
				String methodName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1, fieldName.length());
				Method method;
				Object resultObj;
				method = clazz.getMethod(methodName);
				resultObj = method.invoke(this);
				if (resultObj != null && !"".equals(resultObj)) {
					result.append("[").append(fieldName).append("]").append(resultObj).append(" ");
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return result.toString();
	}

}