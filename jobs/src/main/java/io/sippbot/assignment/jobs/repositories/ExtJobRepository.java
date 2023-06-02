package io.sippbot.assignment.jobs.repositories;

import io.sippbot.assignment.jobs.models.dto.Filter;
import io.sippbot.assignment.jobs.models.entities.JobRecord;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
@RequiredArgsConstructor
public class ExtJobRepository {
    private final JobRepository jobRepository;
    @PersistenceContext
    private final EntityManager em;

    public Page<JobRecord> getQueryResult(Pageable pageable, List<Filter> filters, List<String> fields){
        if(filters.size()>0) {
            return jobRepository.findAll(getSpecificationFromFilters(filters), pageable);
        }else {
            return jobRepository.findAll(pageable);
        }
    }

    private Specification<JobRecord> getSpecificationFromFilters(List<Filter> filter) {
        Specification<JobRecord> specification = where(createSpecification(filter.remove(0)));
        for (Filter input : filter) {
            specification = specification.and(createSpecification(input));
        }

        return specification;
    }

    private Specification<JobRecord> createSpecification(Filter input) {
        switch (input.getOperator()){
            case EQUALS:
                return (root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get(input.getField()),
                            castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
            case GREATER_THAN:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.gt(root.get(input.getField()),
                                (Number) castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
            case LESS_THAN:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.lt(root.get(input.getField()),
                                (Number) castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
            case LIKE:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.like(root.get(input.getField()), "%"+input.getValue()+"%");
            case IN:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.in(root.get(input.getField()))
                                .value(castToRequiredType(root.get(input.getField()).getJavaType(), input.getValues()));
            default:
                throw new RuntimeException("Operation not supported yet");
        }
    }
    private Object castToRequiredType(Class fieldType, String value) {
        if(fieldType.isAssignableFrom(Double.class)){
            return Double.valueOf(value);
        }else if(fieldType.isAssignableFrom(Integer.class)){
            return Integer.valueOf(value);
        }else if(Enum.class.isAssignableFrom(fieldType)){
            return Enum.valueOf(fieldType, value);
        }
        return null;
    }

    private Object castToRequiredType(Class fieldType, List<String> value) {
        List<Object> lists = new ArrayList<>();
        for (String s : value) {
            lists.add(castToRequiredType(fieldType, s));
        }
        return lists;
    }

}
