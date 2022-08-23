package com.management.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ManagementUpdateRequestDto {

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
    public ManagementUpdateRequestDto(String registUserCnt, String deleteUserCnt,
                                      String paidAmount, String usedAmount, String salesAmount){
        this.registUserCnt = registUserCnt;
        this.deleteUserCnt = deleteUserCnt;
        this.paidAmount = paidAmount;
        this.usedAmount = usedAmount;
        this.salesAmount = salesAmount;
    }
}
