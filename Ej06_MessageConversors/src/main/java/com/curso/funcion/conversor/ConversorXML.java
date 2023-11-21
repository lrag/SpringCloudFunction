package com.curso.funcion.conversor;

import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.AbstractMessageConverter;
import org.springframework.util.MimeType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;


public class ConversorXML extends AbstractMessageConverter {

    public ConversorXML() {
        super(new MimeType("application", "xml"));
    }	
	
	@Override
	protected boolean supports(Class<?> clazz) {
		return true;
	}

    @Override
    protected Object convertFromInternal(Message<?> message, Class<?> targetClass, Object conversionHint) {
        String payload = (String) message.getPayload();
        
        XmlMapper xmlMapper = new XmlMapper();
        Object obj = null;
		try {
			obj = xmlMapper.readValue(payload, targetClass);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        return obj;        
    }
	
	@Override
	protected Object convertToInternal(Object payload, @Nullable MessageHeaders headers, @Nullable Object conversionHint) {
	    XmlMapper xmlMapper = new XmlMapper();
	    String xml = null;		
	    try {
			xml = xmlMapper.writeValueAsString(payload);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}		
		return xml;
	}
	
}
