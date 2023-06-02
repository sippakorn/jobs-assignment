package io.sippbot.assignment.jobs.services;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.sippbot.assignment.jobs.models.dto.JobsFilter;
import io.sippbot.assignment.jobs.models.entities.JobRecord;
import io.sippbot.assignment.jobs.repositories.ExtJobRepository;
import io.sippbot.assignment.jobs.utils.RequestParamsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JobsService {

    private final ExtJobRepository jobRepository;

    public Page<JobRecord> search(final Pageable pageable, final Map<String,String> requestParams){
        JobsFilter filter = RequestParamsUtil.convertToJobsFilter(requestParams);
        return jobRepository.getQueryResult(pageable, filter.getFilters(), filter.getFields());
    }

}
