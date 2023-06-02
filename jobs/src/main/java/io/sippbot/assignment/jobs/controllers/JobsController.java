package io.sippbot.assignment.jobs.controllers;

import io.sippbot.assignment.jobs.models.dto.JobsFilter;
import io.sippbot.assignment.jobs.models.entities.JobRecord;
import io.sippbot.assignment.jobs.services.JobsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "jobs", produces = MediaType.APPLICATION_JSON_VALUE)
public class JobsController {

    final private JobsService jobsService;
    @GetMapping("search")
    public Page<JobRecord> searchJobs(final Pageable pageable, @RequestParam final Map<String,String> requestParams){

        return jobsService.search(pageable, requestParams);
    }

}
