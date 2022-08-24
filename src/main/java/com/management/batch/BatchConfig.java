package com.management.batch;

import com.management.domain.management.Management;
import com.management.domain.management.ManagementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    @Autowired
    private ManagementRepository managementRepository;

    @Value("${input.file}")
    private String filePath;

    @Value("${input.delimeter}")
    private String delimeter;

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job ManagementJob() throws Exception {
        Job managementJob = jobBuilderFactory.get("ManagementJob")
                .start(Step())
                .build();
        return managementJob;
    }

    @Bean
    @Transactional
    @JobScope
    public Step Step() throws Exception {
        return stepBuilderFactory.get("Step")
                .tasklet(((contribution, chunkContext) -> {
                    FileReader fileReader = new FileReader(filePath);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line;
                    while((line = bufferedReader.readLine()) != null) {
                        List<String> managementList = Arrays.asList(line.split(delimeter));
                        managementRepository.save(Management.builder()
                                .dateTime(managementList.get(0))
                                .registUserCnt(managementList.get(1))
                                .deleteUserCnt(managementList.get(2))
                                .paidAmount(managementList.get(3))
                                .usedAmount(managementList.get(4))
                                .salesAmount(managementList.get(5))
                                .build());
                    }
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }
}

