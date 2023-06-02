package io.sippbot.assignment.jobs.utils;

import io.sippbot.assignment.jobs.models.dto.Filter;
import io.sippbot.assignment.jobs.models.dto.JobsFilter;
import io.sippbot.assignment.jobs.models.dto.QueryOperator;

import java.util.*;

public class RequestParamsUtil {
    public static JobsFilter convertToJobsFilter(Map<String, String> requestParams) {

        List<Filter> filters = new ArrayList<>();
        requestParams.entrySet().forEach(entry -> {
            if (entry.getKey().equalsIgnoreCase("page") || entry.getKey().equalsIgnoreCase("size")){
                // skip
            } else if (entry.getValue().contains(",")) {
                filters.add(Filter.builder().field(entry.getKey())
                        .operator(QueryOperator.IN)
                        .values(Arrays.asList(entry.getValue().split(","))).build());
            } else if (entry.getKey().contains("[gte]")) {
                filters.add(Filter.builder().field(entry.getKey().substring(0, entry.getKey().length() - 5))
                        .operator(QueryOperator.GREATER_THAN)
                        .value(entry.getValue()).build());
            } else if (entry.getKey().contains("[lte]")) {
                filters.add(Filter.builder().field(entry.getKey().substring(0, entry.getKey().length() - 5))
                        .operator(QueryOperator.LESS_THAN)
                        .value(entry.getValue()).build());
            } else if (entry.getKey().contains("[like]")) {
                filters.add(Filter.builder().field(entry.getKey().substring(0, entry.getKey().length() - 6))
                        .operator(QueryOperator.LIKE)
                        .value(entry.getValue()).build());
            } else {
                filters.add(Filter.builder().field(entry.getKey())
                        .operator(QueryOperator.EQUALS)
                        .value(entry.getValue()).build());
            }
        });

        JobsFilter jobsFilter = JobsFilter.builder()
                .filters(filters)
                .fields(Arrays.asList(Optional.ofNullable(requestParams.get("fields")).orElse("").split(",")))
                .build();

        return jobsFilter;
    }
}
