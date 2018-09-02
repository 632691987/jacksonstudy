package org.vincent.c01_jackson_annotations;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.InjectableValues.Std;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.Before;
import org.junit.Test;
import org.vincent.c01_jackson_annotations.entity.JacksonInjectEntity;
import org.vincent.c01_jackson_annotations.entity.JsonAnySetterEntity;
import org.vincent.c01_jackson_annotations.entity.JsonDeserializeEntity;
import org.vincent.c01_jackson_annotations.entity.JsonPropertyEntity;
import org.vincent.c01_jackson_annotations.entity.JsonSerializeEntity;
import org.vincent.c01_jackson_annotations.entity.JsonSetterEntity;

/**
 * C01S02 是 String 读取为 Entity 的
 */
public class C01S02
{
	ObjectMapper objectMapper = null;

	@Before
	public void setUp() {
		objectMapper = new ObjectMapper();
	}

	/**
	 * @JsonProperty 反向读取字符串
	 * @throws IOException
	 */
	@Test
	public void testJsonCreator() throws IOException
	{
		String jsonString = "{\"id\":20,\"theName\":\"aaa\"}";
		JsonPropertyEntity entity = objectMapper.readerFor(JsonPropertyEntity.class).readValue(jsonString);
		assertThat(entity.getName()).isEqualTo("aaa");
	}

	/**
	 * @JacksonInject 的意思是说在从 String 到 Object 之前把原本不属于它的属性值类似 AOP 了拦截进去
	 */
	@Test
	public void testJacksonInject() throws IOException
	{
		String value = "this is value";
		String json = "{\"name\":\"" + value + "\"}";

		/**
		 * addValue 的 key  : 对于 object 要 AOP 的属性
		 * addValue 的 value: 对于 object 要 AOP 的Value
		 */
		InjectableValues inject = new Std().addValue(JacksonInjectEntity.JACKSON_INJECT_KEY, value.length());

		JacksonInjectEntity entity = objectMapper.reader(inject).forType(JacksonInjectEntity.class).readValue(json);
		assertThat(entity.getName()).isEqualTo(value);
		assertThat(entity.getLength2223333()).isEqualTo(value.length());
	}

	/**
	 * 这里呢，个 @JsonAnySetter 原本的意思是说。想 @JsonAnyGetter 一样，把打平的 Key Value 队变回 Map, 然后再加上其他的字段
	 * 但是这里出现了一个相反的地方，就是，如果 JsonAnyGetterEntity 这个类里面有一个字段，叫 lastName, 而 Map 里面有一个key, 也叫lastName, 那么
	 * 在 Object -> JsonString 的时候，是先调用 lastName property, 再输出 Map 的。而 JsonAnySetterEntity 个刚好相反，是先读Map, 再读 lastName property
	 * 比对 @org.vincent.c01_jackson_annotations.C01S01#testJsonAnyGetter() 就知道的了
	 *
	 * 其实最好就是 Map 的 Key 和 其他的字段的名称不要一样
	 * @throws IOException
	 */
	@Test
	public void testJsonAnySetter() throws IOException
	{
		String jsonString = "{\"lastName\":\"lastNameInMap\",\"key1\":\"value1\",\"key2\":\"value2\",\"lastName\":\"lastNameInColumn\"}";
		JsonAnySetterEntity entity = objectMapper.readerFor(JsonAnySetterEntity.class).readValue(jsonString);
		assertThat(entity.getLastName()).isEqualTo("lastNameInColumn");
		//assertThat(entity.).isEqualTo(20);
	}

	/**
	 * @JsonSetter 其实和 @JsonProperty差不多
	 */
	@Test
	public void testJsonSetter() throws IOException
	{
		String jsonString = "{\"id\":20,\"theName\":\"aaa\"}";
		JsonSetterEntity entity = objectMapper.readerFor(JsonSetterEntity.class).readValue(jsonString);
		assertThat(entity.getName()).isEqualTo("aaa");
		assertThat(entity.getId()).isEqualTo(20);
	}

	/**
	 * @JsonDeserialize 正好相反，是更为复杂的从 Object 类型的 column 变成 String
	 */
	@Test
	public void testJsonDeserialize() throws IOException
	{
		String jsonString = "{\"name\":\"namevalue\",\"date\":\"20-12-2014 02:30:00\"}";
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

		JsonDeserializeEntity entity = objectMapper.readerFor(JsonDeserializeEntity.class).readValue(jsonString);
		assertThat(sdf.format(entity.getDate())).isEqualTo("20-12-2014 02:30:00");
	}

	/**
	 * 对特定的字段进行特别的处理, 使用到 JsonSerializer
	 */
	@Test
	public void testJsonSerialize() throws JsonProcessingException, ParseException
	{
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

		JsonSerializeEntity entity = new JsonSerializeEntity("property1", df.parse("08-21-2018 15:38:22"));
		//{"name":"property1","eventDate":"08-09-2019 03:38:22"}
		assertThat(objectMapper.writeValueAsString(entity)).contains("name", "eventDate");
	}
}
