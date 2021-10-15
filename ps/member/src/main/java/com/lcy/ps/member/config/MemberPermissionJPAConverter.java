package com.lcy.ps.member.config;

import com.lcy.ps.member.domain.member.permission.MemberPermission;
import com.lcy.ps.member.domain.member.permission.ProjectPermission;
import com.lcy.ps.member.domain.member.permission.SessionPermission;
import com.lcy.ps.member.domain.member.permission.WorkPermission;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class MemberPermissionJPAConverter implements AttributeConverter<MemberPermission, String> {

    @Override
    public String convertToDatabaseColumn(MemberPermission memberPermission) {
        return memberPermission.toString();
    }

    @Override
    public MemberPermission convertToEntityAttribute(String dbData) {
        String[] arr = dbData.split(":");

        if (arr[0].equals("P")) {
            return ProjectPermission.valueOf(arr[1]);
        } else if (arr[0].equals("S")) {
            return SessionPermission.valueOf(arr[1]);
        } else if (arr[0].equals("W")) {
            return WorkPermission.valueOf(arr[1]);
        }

        throw new ConversionFailedException(
                TypeDescriptor.valueOf(String.class),
                TypeDescriptor.valueOf(MemberPermission.class),
                dbData,
                new Throwable("값이 틀령"));
    }
}
