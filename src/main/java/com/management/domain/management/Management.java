package com.management.domain.management;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Management {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //일시
    @Column(name="dateTime" , unique=true)
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
    public Management(String dateTime, String registUserCnt, String deleteUserCnt,
                      String paidAmount, String usedAmount,String salesAmount){
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


    public void update(String registUserCnt, String deleteUserCnt, String paidAmount,
                       String usedAmount, String salesAmount){
        this.registUserCnt = registUserCnt;
        this.deleteUserCnt = deleteUserCnt;
        this.paidAmount = paidAmount;
        this.usedAmount = usedAmount;
        this.salesAmount = salesAmount;
    }

    public void patch(String registUserCnt, String deleteUserCnt, String paidAmount,
                       String usedAmount, String salesAmount){
        if(registUserCnt != null) this.registUserCnt = registUserCnt;
        if(deleteUserCnt != null) this.deleteUserCnt = deleteUserCnt;
        if(paidAmount != null) this.paidAmount = paidAmount;
        if(usedAmount != null) this.usedAmount = usedAmount;
        if(salesAmount != null) this.salesAmount = salesAmount;
    }

    //TODO : 도메인 기능 메소드 구현(삽입, 수정, 삭제, 조회)
}
