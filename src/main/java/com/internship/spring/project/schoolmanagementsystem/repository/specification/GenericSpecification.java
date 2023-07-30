package com.internship.spring.project.schoolmanagementsystem.repository.specification;


import com.internship.spring.project.schoolmanagementsystem.domain.entity.BaseEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

public abstract class GenericSpecification<T extends BaseEntity> implements Specification<T> {

    public abstract SearchQuery getSearchCriteria();

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        {
            if (getSearchCriteria().getOperation().equalsIgnoreCase(">")) {
                return criteriaBuilder.greaterThanOrEqualTo(
                        root.get(getSearchCriteria().getKey()), getSearchCriteria().getValue().toString());
            } else if (getSearchCriteria().getOperation().equalsIgnoreCase("<")) {
                return criteriaBuilder.lessThanOrEqualTo(
                        root.get(getSearchCriteria().getKey()), getSearchCriteria().getValue().toString());
            } else if (getSearchCriteria().getOperation().equalsIgnoreCase(":")) {
                if (root.get(getSearchCriteria().getKey()).getJavaType() == String.class) {
                    return criteriaBuilder.like(
                            root.get(getSearchCriteria().getKey()), "%" + getSearchCriteria().getValue() + "%");
                } else {
                    return criteriaBuilder.equal(root.get(getSearchCriteria().getKey()), getSearchCriteria().getValue());
                }
            } else if (getSearchCriteria().getOperation().equalsIgnoreCase("date-between")) {
                List<String>dates = (List<String>) getSearchCriteria().getValue();
                Instant startInstant = LocalDateTime.parse(dates.get(0)).toInstant(ZoneOffset.UTC);
                var startDate = Date.from(startInstant);
                Instant endInstant =  LocalDateTime.parse(dates.get(1)).toInstant(ZoneOffset.UTC);
                var endDate = Date.from(endInstant);
                return criteriaBuilder.between(root.get(getSearchCriteria().getKey()), startDate, endDate);
            }
        }
        return null;
    }


}

