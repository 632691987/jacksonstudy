package org.vincent.c01_jackson_annotations.entity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateDeserializer extends JsonDeserializer<Date>
{

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

	@Override
	public Date deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException
	{
		try {
			return sdf.parse(jsonParser.getText());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
