package org.vincent.c01_jackson_annotations.entity;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateSerializer extends JsonSerializer<Date>
{
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException
	{
		gen.writeString(sdf.format(value));
	}
}
