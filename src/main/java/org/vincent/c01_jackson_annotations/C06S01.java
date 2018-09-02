package org.vincent.c01_jackson_annotations;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.util.Calendar;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.vincent.c01_jackson_annotations.entity.JsonFilterEntity;
import org.vincent.c01_jackson_annotations.entity.JsonFormatEntity;
import org.vincent.c01_jackson_annotations.entity.JsonIdentifyInfoEntity;
import org.vincent.c01_jackson_annotations.entity.JsonReferenceEntity;
import org.vincent.c01_jackson_annotations.entity.JsonUnwrappedEntity;
import org.vincent.c01_jackson_annotations.entity.JsonViewEntity;

public class C06S01
{
	ObjectMapper objectMapper = null;

	@Before
	public void setUp() {
		objectMapper = new ObjectMapper();
	}

	/**
	 * @JsonFormat 是显示 date format string
	 */
	@Test
	public void testJsonFormat() throws JsonProcessingException
	{
		JsonFormatEntity entity = new JsonFormatEntity("name", Calendar.getInstance().getTime());
		String jsonString = objectMapper.writeValueAsString(entity);
		//{"name":"name","eventDate":"02-09-2018 08:52:56"}
		assertThat(jsonString).contains("name", "eventDate");
	}

	@Test
	public void testJsonUnwrapped() throws JsonProcessingException
	{
		JsonUnwrappedEntity.JsonUnwrappedName name = new JsonUnwrappedEntity.JsonUnwrappedName("first1", "last1");

		JsonUnwrappedEntity entity = new JsonUnwrappedEntity(15, name);
		String jsonString = objectMapper.writeValueAsString(entity);

		//这是没有加Annotation的: {"id":15,"name":{"firstName":"first1","lastName":"last1"}}
		//这是  有加Annotation的：{"id":15,"firstName":"first1","lastName":"last1"}
		assertThat(jsonString).isEqualTo("{\"id\":15,\"firstName\":\"first1\",\"lastName\":\"last1\"}");
	}

	/**
	 * @JsonView 所谓的 JsonView 的意思，就是说，我首先创建一个 view class, 然后里面再设不同的 internal class
	 * 然后在类里面， annotated 不同的属性，然后在显示的时候，就可以类似 filter 的形式显示不同的属性。
	 *
	 * 例如，有个类有三个属性，第一个属性是 annotated class 1, 第二个属性是 annotated class 2, 第三个属性是 annotated class 3,
	 * 那么在 objectMapper.writerWithView(class1.class) 就只显示 第一个属性
	 * 但是要注意继承的问题，如果第二个属性是继承第一个属性，那么如果要 objectMapper.writerWithView(class2.class)
	 * 那么显示的时候会连同 class1 所 annotated
	 */
	@Test
	public void testJsonView() throws JsonProcessingException
	{
		JsonViewEntity entity = new JsonViewEntity(15, "first", "last", "property2");
		objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
		String jsonString = null;

		jsonString = objectMapper.writerWithView(JsonViewEntity.InternalViews.Public1.class).writeValueAsString(entity);
		//{"id":15,"firstName":"first"}
		assertThat(jsonString).isEqualTo("{\"id\":15,\"firstName\":\"first\"}");

		jsonString = objectMapper.writerWithView(JsonViewEntity.InternalViews.Internal1.class).writeValueAsString(entity);
		//{"id":15,"firstName":"first","lastName":"last"}
		assertThat(jsonString).isEqualTo("{\"id\":15,\"firstName\":\"first\",\"lastName\":\"last\"}");

		jsonString = objectMapper.writerWithView(JsonViewEntity.InternalViews.Internal2.class).writeValueAsString(entity);
		//{"property2":"property2"}
		assertThat(jsonString).isEqualTo("{\"property2\":\"property2\"}");
	}

	/**
	 * @JsonFilter 跟 @JsonView 一样拥有 filter 不同 property 输出的功能，但是很明显 @JsonFilter 简单很多
	 */
	@Test
	public void testJsonFilter() throws JsonProcessingException
	{
		JsonFilterEntity entity = new JsonFilterEntity(15, "name");
		FilterProvider provider = new SimpleFilterProvider().addFilter(
				JsonFilterEntity.filterName,
				SimpleBeanPropertyFilter.filterOutAllExcept("name"));

		String jsonString = objectMapper.writer(provider).writeValueAsString(entity);
		assertThat(jsonString).doesNotContain("id");
		assertThat(jsonString).contains("name");
	}

	/**
	 * @JsonManagedReference 和 @JsonBackReference 是一种互相引用的，在这个情景里面，
	 * AClass 拥有 BClass, BClass 拥有 AClass
	 * 这里显示的时候就是为了防止互相引用引致的无限循环
	 *
	 * 因此 @JsonBackReference 是不会写出来的
	 */
	@Test
	public void testJsonReference() throws JsonProcessingException
	{
		JsonReferenceEntity.AClass aClass= new JsonReferenceEntity.AClass(1, "name", null);
		JsonReferenceEntity.BClass bClass= new JsonReferenceEntity.BClass(1, "name");
		bClass.setaClassList(Lists.list(aClass));
		aClass.setbClass(bClass);

		String jsonString1 = objectMapper.writeValueAsString(bClass);
		//{"id":1,"name":"name"}
		assertThat(jsonString1).isEqualTo("{\"id\":1,\"name\":\"name\"}");

		//{"id":1,"name":"name","bClass":{"id":1,"name":"name"}}
		String jsonString2 = objectMapper.writeValueAsString(aClass);
		assertThat(jsonString2).isEqualTo("{\"id\":1,\"name\":\"name\",\"bClass\":{\"id\":1,\"name\":\"name\"}}");
	}

	/**
	 * 不是很明白这个有什么用，但是看资料， @JsonIdentityInfo 和 {@JsonManagedReference 和 @JsonBackReference}
	 * 是一样功能的，防止死循环出 string 的
	 */
	@Test
	public void testJsonIdentifyInfo() throws JsonProcessingException
	{
		JsonIdentifyInfoEntity.AClass aClass= new JsonIdentifyInfoEntity.AClass(1, "name", null);
		JsonIdentifyInfoEntity.BClass bClass= new JsonIdentifyInfoEntity.BClass(1, "name");
		bClass.setaClassList(Lists.list(aClass));
		aClass.setbClass(bClass);

		String jsonString1 = objectMapper.writeValueAsString(bClass);
		//{"id":1,"name":"name","aClassList":[{"id":1,"name":"name","bClass":1}]}
		assertThat(jsonString1).isEqualTo("{\"id\":1,\"name\":\"name\",\"aClassList\":[{\"id\":1,\"name\":\"name\",\"bClass\":1}]}");

		String jsonString2 = objectMapper.writeValueAsString(aClass);
		//{"id":1,"name":"name","bClass":{"id":1,"name":"name","aClassList":[1]}}
		assertThat(jsonString2).isEqualTo("{\"id\":1,\"name\":\"name\",\"bClass\":{\"id\":1,\"name\":\"name\",\"aClassList\":[1]}}");
	}
}
