package com.management.web;

import com.management.domain.management.Management;
import com.management.domain.management.ManagementRepository;
import com.management.web.dto.ManagementResponseDto;
import com.management.web.dto.ManagementSaveRequestDto;
import com.management.web.dto.ManagementUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ManagementApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ManagementRepository managementRepository;

    @After
    public void tearDown() throws Exception{
        managementRepository.deleteAll();
    }

    @Test
    public void Management_Create() throws Exception{
        //given
        String dateTime = "2022-06-31 17";
        String registUserCnt = "322";
        String deleteUserCnt = "42";
        String paidAmount = "55,100";
        String usedAmount = "37,300";
        String salesAmount = "85,000";

        ManagementSaveRequestDto requestDto =ManagementSaveRequestDto.builder()
                .dateTime(dateTime)
                .registUserCnt(registUserCnt)
                .deleteUserCnt(deleteUserCnt)
                .paidAmount(paidAmount)
                .usedAmount(usedAmount)
                .salesAmount(salesAmount)
                .build();

        String url = "http://localhost:" + port + "/api/v1/management";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Management> all = managementRepository.findAll();
        assertThat(all.get(0).getDateTime()).isEqualTo(dateTime);
        assertThat(all.get(0).getSalesAmount()).isEqualTo(salesAmount);
    }

    @Test
    public void Management_Select() throws Exception{
        //given
        String dateTime = "2020-01-01 11";
        String registUserCnt = "12";
        String deleteUserCnt = "2";
        String paidAmount = "5,100";
        String usedAmount = "7,300";
        String salesAmount = "5,000";

        Management management = managementRepository.save(Management.builder()
                .dateTime(dateTime)
                .registUserCnt(registUserCnt)
                .deleteUserCnt(deleteUserCnt)
                .paidAmount(paidAmount)
                .usedAmount(usedAmount)
                .salesAmount(salesAmount)
                .build());

        String url = "http://localhost:" + port + "/api/v1/management/" + dateTime;

        //when
        ResponseEntity<ManagementResponseDto> responseEntity = restTemplate.getForEntity(url, ManagementResponseDto.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        List<Management> all = managementRepository.findAll();
        assertThat(all.get(0).getSalesAmount()).isEqualTo(salesAmount);
        assertThat(all.get(0).getRegistUserCnt()).isEqualTo(registUserCnt);
    }

    @Test
    public void Management_Update() throws Exception{
        //given
        String dateTime = "2020-01-01 11";
        String registUserCnt = "12";
        String deleteUserCnt = "2";
        String paidAmount = "5,100";
        String usedAmount = "7,300";
        String salesAmount = "5,000";

        Management savedManagement = managementRepository.save(Management.builder()
                .dateTime(dateTime)
                .registUserCnt(registUserCnt)
                .deleteUserCnt(deleteUserCnt)
                .paidAmount(paidAmount)
                .usedAmount(usedAmount)
                .salesAmount(salesAmount)
                .build());

        String expectedRegistUserCnt = "15";
        String expectedDeleteUserCnt = "5";
        String expectedPaidAmount = "51,000";
        String expectedUsedAmount = "73,000";
        String expectedSalesAmount = "55,000";

        ManagementUpdateRequestDto requestDto = ManagementUpdateRequestDto.builder()
                .registUserCnt(expectedRegistUserCnt)
                .deleteUserCnt(expectedDeleteUserCnt)
                .paidAmount(expectedPaidAmount)
                .usedAmount(expectedUsedAmount)
                .salesAmount(expectedSalesAmount)
                .build();

        String url = "http://localhost:" + port + "/api/v1/management/" + dateTime;

        HttpEntity<ManagementUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotEmpty();
        List<Management> all = managementRepository.findAll();
        assertThat(all.get(0).getSalesAmount()).isEqualTo(expectedSalesAmount);
        assertThat(all.get(0).getRegistUserCnt()).isEqualTo(expectedRegistUserCnt);
    }


}
