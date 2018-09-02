package org.vincent.c01_jackson_annotations;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.vincent.c01_jackson_annotations.entity.JsonAnyGetterEntity;
import org.vincent.c01_jackson_annotations.entity.JsonGetterEntity;
import org.vincent.c01_jackson_annotations.entity.JsonPropertyOrderEntity;
import org.vincent.c01_jackson_annotations.entity.JsonRawValueEntity;
import org.vincent.c01_jackson_annotations.entity.JsonRootNameEntity;
import org.vincent.c01_jackson_annotations.entity.JsonValueEntity;

/**
 * C01S01 是 Entity 输出成为 String 的
 */
public class C01S01
{

	ObjectMapper objectMapper = null;

	@Before
	public void setUp() {
		objectMapper = new ObjectMapper();
	}

	/**
	 * @JsonAnyGetter 就是能够把属性平放在JSON String 里面的
	 * 但是缺点是只能够放一次.
	 * PS: 注意即使JSON里面有两个同样的node name 也不算错误
	 */
	@Test
	public void testJsonAnyGetter() throws JsonProcessingException
	{
		JsonAnyGetterEntity class1 = new JsonAnyGetterEntity();

		Map<String, String> maps = class1.getProperties();
		maps.put("key1", "value1");
		maps.put("key2", "value2");
		maps.put("lastName", "lastNameInMap");

		class1.setLastName("lastNameInColumn");

		//{"lastName":"lastNameInColumn","key1":"value1","key2":"value2","lastName":"lastNameInMap"}
		assertThat(objectMapper.writeValueAsString(class1))
				.isEqualTo("{\"lastName\":\"lastNameInColumn\",\"key1\":\"value1\",\"key2\":\"value2\",\"lastName\":\"lastNameInMap\"}");
	}

	/**
	 * @JsonGetter 的意思就是说给一个别名给这个node, 而不是变量原本的名字
	 */
	@Test
	public void testJsonGetter() throws JsonProcessingException
	{
		JsonGetterEntity class1 = new JsonGetterEntity();

		class1.setId(10);
		class1.setName("anotherName");

		//{"id":10,"ANOTHER_NODE_PROPERTY":"anotherName"}
		assertThat(objectMapper.writeValueAsString(class1)).contains(JsonGetterEntity.DISPLAY_NAME).doesNotContain("name");
	}

	/**
	 * @JsonPropertyOrder 是保证导出顺序
	 */
	@Test
	public void testJsonPropertyOrder() throws JsonProcessingException
	{
		JsonPropertyOrderEntity entity = new JsonPropertyOrderEntity("aaa","bbb");

		//{"lastName":"bbb","firstName":"aaa"}
		assertThat(objectMapper.writeValueAsString(entity)).isEqualTo("{\"lastName\":\"bbb\",\"firstName\":\"aaa\"}");
	}

	/**
	 * @JsonRawValue 是用来在JSON里面避开关键的字符串变形，例如，这里的第二个值得是 ["key"+"value"}] --> 不要 []
	 */
	@Test
	public void testJsonRawValue() throws JsonProcessingException
	{
		JsonRawValueEntity entity = new JsonRawValueEntity("name property", "\"key\"+\"value\"}");

		//{"name":"name property","jsonString":"key"+"value"}}
		assertThat(objectMapper.writeValueAsString(entity)).isEqualTo("{\"name\":\"name property\",\"jsonString\":\"key\"+\"value\"}}");
	}

	/**
	 * @JsonValue 就是能够把 enum 提取成 string
	 * 但是缺点是只能够放一次.
	 */
	@Test
	public void testJsonValue() throws JsonProcessingException
	{
		JsonValueEntity entity = JsonValueEntity.TYPE2;

		//"JsonValueEntity{id=2, name='Type B'}"
		assertThat(objectMapper.writeValueAsString(entity)).isEqualTo("\"JsonValueEntity{id=2, name='Type B'}\"");
	}

	/**
	 * @JsonRootName 是用来添加一个总 node 的
	 * 但是记得要 enable "SerializationFeature.WRAP_ROOT_VALUE" 这个 feature
	 */
	@Test
	public void testJsonRootName() throws JsonProcessingException
	{
		objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);

		JsonRootNameEntity entity = new JsonRootNameEntity(12,"bbb");

		//{"ROOT_NAME":{"id":12,"name":"bbb"}}
		assertThat(objectMapper.writeValueAsString(entity)).contains(JsonRootNameEntity.ROOTNAME);
	}
}
