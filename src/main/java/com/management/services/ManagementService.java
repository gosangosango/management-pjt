package com.management.services;

import com.management.domain.management.Management;
import com.management.domain.management.ManagementRepository;
import com.management.web.dto.ManagementResponseDto;
import com.management.web.dto.ManagementSaveRequestDto;
import com.management.web.dto.ManagementUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@RequiredArgsConstructor
@Service
public class ManagementService {

    private final ManagementRepository managementRepository;

    public ManagementResponseDto findByDateTime(String dateTime){

        Management entity = managementRepository.findByDateTime(dateTime);

        if(entity == null){
            throw new IllegalArgumentException("해당 정보가 없습니다. id=" + dateTime);
        }

        return new ManagementResponseDto(entity);
    }

    @Transactional
    public Long save(ManagementSaveRequestDto requestDto){
        return managementRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public String update (String dateTime, ManagementUpdateRequestDto requestDto){
        Management management = managementRepository.findByDateTime(dateTime);
        if(management == null){
            throw new IllegalArgumentException("해당 정보가 없습니다. id=" + dateTime);
        }
        management.update(requestDto.getRegistUserCnt(), requestDto.getDeleteUserCnt(), requestDto.getPaidAmount(),
                requestDto.getUsedAmount(), requestDto.getSalesAmount());
        return dateTime;
    }


    @Transactional
    public String patch (String dateTime, ManagementUpdateRequestDto requestDto){
        Management management = managementRepository.findByDateTime(dateTime);
        if(management == null){
            throw new IllegalArgumentException("해당 정보가 없습니다. id=" + dateTime);
        }
        management.patch(requestDto.getRegistUserCnt(), requestDto.getDeleteUserCnt(), requestDto.getPaidAmount(),
                requestDto.getUsedAmount(), requestDto.getSalesAmount());
        return dateTime;
    }

    @Transactional
    public String delete(String dateTime){
        Management management = managementRepository.findByDateTime(dateTime);
        managementRepository.delete(management);
        return dateTime;
    }
}
