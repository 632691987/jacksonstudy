package org.vincent.c01_jackson_annotations;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import org.vincent.c01_jackson_annotations.entity.JsonAutoDetectEntity;
import org.vincent.c01_jackson_annotations.entity.JsonIgnoreEntity;
import org.vincent.c01_jackson_annotations.entity.JsonIgnoreTypeEntity;
import org.vincent.c01_jackson_annotations.entity.JsonIncludeEntity;
import org.vincent.c01_jackson_annotations.entity.JsonTypeEntity;

public class C01S03
{
	ObjectMapper objectMapper = null;

	@Before
	public void setUp() {
		objectMapper = new ObjectMapper();
	}

	/**
	 * @JsonInclude(Include.NON_NULL) 有很多不同选项的，对于 Include.NON_NULL 就是 null value 的 column 不显示
	 */
	@Test
	public void testJsonInclude() throws JsonProcessingException
	{
		JsonIncludeEntity entity = new JsonIncludeEntity(13, null);
		String jsonString = objectMapper.writeValueAsString(entity);

		//{"id":13}
		assertThat(jsonString).doesNotContain("name");
	}

	/**
	 * @JsonAutoDetect 系好野来的，原来本来连 private 的字段都可以 detect 到 ，也可以定制只要 protected 的字段
	 */
	@Test
	public void testJsonAutoDetect() throws JsonProcessingException
	{
		JsonAutoDetectEntity entity = new JsonAutoDetectEntity(15,"name");
		String jsonString = objectMapper.writeValueAsString(entity);

		//{"id":15,"name":"name"}
		assertThat(jsonString).isEqualTo("{\"id\":15,\"name\":\"name\"}");
	}

	/**
	 *
	 * @JsonIgnore 就是在现实的时候忽略某属性，例如下面的例子里面就是忽略了 id 属性
	 */
	@Test
	public void testJsonIgnore() throws JsonProcessingException
	{
		JsonIgnoreEntity entity = new JsonIgnoreEntity(15, "name");
		String jsonString = objectMapper.writeValueAsString(entity);

		//{"name":"name"}
		assertThat(jsonString).isEqualTo("{\"name\":\"name\"}");
		assertThat(jsonString).doesNotContain("id");
	}

	/**
	 * @JsonIgnoreType 相比较 JsonIgnore, 这个是针对整个 inner class 的
	 */
	@Test
	public void testJsonIgnoreType() throws JsonProcessingException
	{
		JsonIgnoreTypeEntity.BillingAddress billingAddress = new JsonIgnoreTypeEntity.BillingAddress("stree1", "street2");
		JsonIgnoreTypeEntity.ShippingAddress shippingAddress = new JsonIgnoreTypeEntity.ShippingAddress("stree3","steet4");

		JsonIgnoreTypeEntity entity = new JsonIgnoreTypeEntity(15, "name", billingAddress, shippingAddress);
		String jsonString = objectMapper.writeValueAsString(entity);

		//{"id":15,"name":"name","shippingAddress":{"street3":"stree3","street4":"steet4"}}
		assertThat(jsonString).isEqualTo("{\"id\":15,\"name\":\"name\",\"shippingAddress\":{\"street3\":\"stree3\",\"street4\":\"steet4\"}}");
		assertThat(jsonString).doesNotContain("billingAddress");
		assertThat(jsonString).contains("shippingAddress");
	}

	/**
	 * 这个例子有点复杂，应用场景：
	 * 如果有一个 abstract 的 class, 下面有两个类去继承它。
	 * 然后如果要在得出的JsonString 里面写明是属于哪个类型，那么可以用这几个 annotation 的组合
	 * 用了这几个组合，可以添加一个 type 的 node
	 *
	 * 这里有一个细节的事情，
	 * @JsonSubTypes.Type(value=Dog.class, name="dog1") 的级别比 @JsonTypeName("dog2") 高
	 * 两个 Annotation 也是有重复功能的
	 *
	 * 这个功能最重点是可以从 string 到  object 的建立，通过 type 得到具体的 class
	 */
	@Test
	public void testJsonTypeEntity() throws IOException
	{
		JsonTypeEntity.Dog dog = new JsonTypeEntity.Dog("dogName");
		JsonTypeEntity entity1 = new JsonTypeEntity(dog);
		String jsonString1 = objectMapper.writeValueAsString(entity1);

		//{"animal":{"type":"dog2","name":"dogName","barkValue":0.0}}
		assertThat(jsonString1).contains("dog2", "type222");
		assertThat(jsonString1).isEqualTo("{\"animal\":{\"type222\":\"dog2\",\"name\":\"dogName\",\"barkValue\":0.0}}");


		JsonTypeEntity.Cat cat = new JsonTypeEntity.Cat("catName");
		JsonTypeEntity entity2 = new JsonTypeEntity(cat);
		String jsonString2 = objectMapper.writeValueAsString(entity2);

		//{"animal":{"type":"cat2","name":"catName","likeCream":false,"lives":0}}
		assertThat(jsonString2).contains("cat2", "type222");
		assertThat(jsonString2).isEqualTo("{\"animal\":{\"type222\":\"cat2\",\"name\":\"catName\",\"likeCream\":false,\"lives\":0}}");

		//反过来通过 jsonString1 变回 dog
		JsonTypeEntity entity3 = objectMapper.readerFor(JsonTypeEntity.class).readValue(jsonString1);
		assertThat(entity3.getAnimal()).isInstanceOf(JsonTypeEntity.Dog.class);
		//反过来通过 jsonString2 变回 cat
		JsonTypeEntity entity4 = objectMapper.readerFor(JsonTypeEntity.class).readValue(jsonString2);
		assertThat(entity4.getAnimal()).isInstanceOf(JsonTypeEntity.Cat.class);
	}

	@Test
	public void testJsonIgnoreProperties() {

	}

}
