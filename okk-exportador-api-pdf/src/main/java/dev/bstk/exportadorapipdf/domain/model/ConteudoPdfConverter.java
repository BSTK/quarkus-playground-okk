package dev.bstk.exportadorapipdf.domain.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.bstk.exportadorapipdf.helper.JSONHelper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ConteudoPdfConverter<T> implements AttributeConverter<T, String> {

    @Override
    public String convertToDatabaseColumn(final T attribute) {
        try {
            return JSONHelper.mapper().writeValueAsString(attribute);
        } catch (JsonProcessingException ex) {
            throw new IllegalArgumentException("", ex);
        }
    }

    @Override
    public T convertToEntityAttribute(final String dbData) {
        try {
            return (T) JSONHelper.mapper().readValue(dbData, ConteudoPdfConverter.class.getTypeParameters()[0].getClass());
        } catch (JsonProcessingException ex) {
            throw new IllegalArgumentException("", ex);
        }
    }
}
