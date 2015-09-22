package com.webapp.mapper;

import java.util.Map;

public interface IdGeneratorMapper {

	//bean을 사용하지 않고 Map으로 사용가능
	Map<String, Object> selectByName(String name);
	
	int update(Map<String, Object> idGen);
}
