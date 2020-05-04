package com.example.task.service.search;

import com.example.task.model.SensorModel;
import com.example.task.model.type.SensorType;
import com.example.task.model.type.Unit;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class SensorSpecification {

    public static Specification<SensorModel> fuzzySearchByField(String field, String value) {

        return (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = null;
            if (root.get(field).getJavaType() == String.class) {
                predicate = criteriaBuilder.like(root.get(field), "%" + value + "%");
            } else if (root.get(field).getJavaType().isEnum()) {
                if (root.get(field).getJavaType() == Unit.class) {
                    predicate = criteriaBuilder.equal(root.get(field), getEnum(value, Unit.class));
                } else {
                    predicate = criteriaBuilder.equal(root.get(field), getEnum(value, SensorType.class));
                }
            } else if (isNumeric(value)) {
                predicate = criteriaBuilder.equal(root.get(field), value);
            }
            return predicate;
        };
    }

    private static boolean isNumeric(String data) {
        if (data.isEmpty()) {
            return false;
        }
        for (char c : data.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                return false;
            }
        }
        return true;
    }

    private static <E extends Enum<E>> Enum getEnum(String value, Class<E> enumClass) {
        List<E> collect = Arrays.stream(enumClass.getEnumConstants()).filter(e -> e.name().contains(value)).collect(toList());
        if (!collect.isEmpty()) {
            return collect.get(0);
        }
        return null;
    }

}
