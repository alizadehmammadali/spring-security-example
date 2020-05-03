package az.softspark.springsecurity.dao.converters;

import az.softspark.springsecurity.model.enums.Role;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role,String> {


    @Override
    public String convertToDatabaseColumn(Role attribute) {
        return attribute.name();
    }

    @Override
    public Role convertToEntityAttribute(String dbData) {
        return Role.valueOf(dbData);
    }
}
