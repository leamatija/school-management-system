package com.internship.spring.project.schoolmanagementsystem.repository.specification;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;


public class UserSpecification extends GenericSpecification<User> {

    private SearchQuery searchQuery;

    public UserSpecification(SearchQuery searchQuery) {
        this.searchQuery = searchQuery;
    }


    @Override
    public SearchQuery getSearchCriteria() {
        return searchQuery;
    }

    public static Specification<User> toSpecification(List<SearchQuery> filters){
        final Specification<User> spec = new UserSpecification(filters.get(0));
        filters.stream().skip(1).forEach(f-> spec.or(new UserSpecification(f)));
        return spec;
    }

}
