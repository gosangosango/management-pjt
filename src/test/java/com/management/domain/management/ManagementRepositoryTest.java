package com.management.domain.management;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagementRepositoryTest {

    @Autowired
    ManagementRepository managementRepository;

    @After
    public void cleanUp(){
        managementRepository.deleteAll();
    }

    @Test
    public void Management_Select_findByDatetime(){
        //given
        String dateTime = "2022-07-31 01";
        String registUserCnt = "32";
        String deleteUserCnt = "4";
        String paidAmount = "45,100";
        String usedAmount = "27,300";
        String salesAmount = "95,000";
        managementRepository.save(Management.builder()
                .dateTime(dateTime)
                .registUserCnt(registUserCnt)
                .deleteUserCnt(deleteUserCnt)
                .paidAmount(paidAmount)
                .usedAmount(usedAmount)
                .salesAmount(salesAmount)
                .build());

        //when
        List<Management> managementList = managementRepository.findAll();
        Management managementByDateTime = managementRepository.findByDateTime(dateTime);

        //then
        Management management = managementList.get(0);
        assertThat(management.getDateTime()).isEqualTo(dateTime);
        assertThat(management.getDeleteUserCnt()).isEqualTo(deleteUserCnt);
        assertThat(management.getSalesAmount()).isEqualTo(salesAmount);

        assertThat(managementByDateTime.getDateTime()).isEqualTo(dateTime);
        assertThat(managementByDateTime.getDeleteUserCnt()).isEqualTo(deleteUserCnt);
        assertThat(managementByDateTime.getSalesAmount()).isEqualTo(salesAmount);
    }

}
