package com.management.batch;

import com.management.domain.management.Management;
import com.management.domain.management.ManagementRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class batchTest {

    @Autowired
    ManagementRepository managementRepository;

    @Autowired
    private BatchConfig batchConfig;

    @Autowired
    private JobLauncher jobLauncher;

    @Test
    public void Management_batch() throws Exception {
        //given
            Map<String, JobParameter> jobParametersMap = new HashMap<>();
            System.out.println("========================");
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date time = new Date();

            String time1 = format1.format(time);

            jobParametersMap.put("requestDate", new JobParameter(time1));

            JobParameters parameters = new JobParameters(jobParametersMap);
            //when
            JobExecution jobExecution = jobLauncher.run(batchConfig.ManagementJob(), parameters);
            System.out.println("jobEnd");


        //then
        List<Management> managementList = managementRepository.findAll();
        Management management = managementList.get(0);
        assertThat(management.getDateTime()).isEqualTo("2022-07-22 00");
        assertThat(management.getDeleteUserCnt()).isEqualTo("4");
        assertThat(management.getSalesAmount()).isEqualTo("95,000");
        Management management2 = managementList.get(1);
        assertThat(management2.getDateTime()).isEqualTo("2022-07-22 01");
        assertThat(management2.getDeleteUserCnt()).isEqualTo("9");
        assertThat(management2.getSalesAmount()).isEqualTo("125,000");
    }
}
