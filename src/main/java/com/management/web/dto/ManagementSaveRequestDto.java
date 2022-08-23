package com.management.web.dto;

import com.management.domain.management.Management;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ManagementSaveRequestDto {

    //일시
    private String dateTime;

    //가입자 수
    private String registUserCnt;

    //탈퇴자 수
    private String deleteUserCnt;

    //결재 금액
    private String paidAmount;

    //사용 금액
    private String usedAmount;

    //매출 금액
    private String salesAmount;

    @Builder
    public ManagementSaveRequestDto(String dateTime, String registUserCnt, String deleteUserCnt,
                                    String paidAmount, String usedAmount, String salesAmount){
        this.dateTime = dateTime;
        this.registUserCnt = registUserCnt;
        this.deleteUserCnt = deleteUserCnt;
        this.paidAmount = paidAmount;
        this.usedAmount = usedAmount;
        this.salesAmount = salesAmount;
    }

    public Management toEntity(){
        return Management.builder()
                .dateTime(dateTime)
                .registUserCnt(registUserCnt)
                .deleteUserCnt(deleteUserCnt)
                .paidAmount(paidAmount)
                .usedAmount(usedAmount)
                .salesAmount(salesAmount)
                .build();
    }
}
