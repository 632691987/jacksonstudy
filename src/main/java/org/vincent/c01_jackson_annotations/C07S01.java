package org.vincent.c01_jackson_annotations;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.vincent.c01_jackson_annotations.entity.CustomJsonAnnotationEntity;

public class C07S01
{
	ObjectMapper objectMapper = null;

	@Before
	public void setUp() {
		objectMapper = new ObjectMapper();
	}

	/**
	 * @CustomAnnotation 就是可以添加一个自己的 customize 的 annotation
	 */
	@Test
	public void testJsonFormat() throws JsonProcessingException
	{
		CustomJsonAnnotationEntity entity = new CustomJsonAnnotationEntity(1, "name");
		String jsonString = objectMapper.writeValueAsString(entity);
		assertThat(jsonString).isEqualTo("{\"name\":\"name\",\"id\":1}");
	}
}
