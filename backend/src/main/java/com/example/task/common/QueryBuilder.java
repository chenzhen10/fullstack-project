package com.example.task.common;

import org.springframework.data.jpa.domain.Specification;

public class QueryBuilder<T> {

    private Specification<T> specification;

    public QueryBuilder() {
        this.specification = Specification.where(specification);
    }

    public Specification<T> append(Specification<T> specification) {
        this.specification = this.specification.or(specification);
        return this.specification;
    }

    public Specification<T> getQuery() {
        return specification;
    }
}
