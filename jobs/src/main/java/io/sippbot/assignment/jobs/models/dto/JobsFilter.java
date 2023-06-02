package io.sippbot.assignment.jobs.models.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class JobsFilter {
    private List<Filter> filters;
    private List<String> fields;

}

