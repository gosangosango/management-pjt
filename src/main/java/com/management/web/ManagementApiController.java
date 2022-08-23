package com.management.web;

import com.management.services.ManagementService;
import com.management.web.dto.ManagementResponseDto;
import com.management.web.dto.ManagementSaveRequestDto;
import com.management.web.dto.ManagementUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ManagementApiController {

    private final ManagementService managementService;

    @GetMapping("/api/v1/management/{dateTime}")
    public ManagementResponseDto findByDateTime(@PathVariable String dateTime){
        return managementService.findByDateTime(dateTime);
    }

    @PostMapping("/api/v1/management")
    public Long save(@RequestBody ManagementSaveRequestDto managementSaveRequestDto){
        return managementService.save(managementSaveRequestDto);
    }

    @PutMapping("/api/v1/management/{dateTime}")
    public String update(@PathVariable String dateTime, @RequestBody ManagementUpdateRequestDto managementUpdateRequestDto){
        return managementService.update(dateTime, managementUpdateRequestDto);
    }

    @DeleteMapping("/api/v1/management/{dateTime}")
    public String delete(@PathVariable String dateTime){
        return managementService.delete(dateTime);
    }
    //TODO: update delete 구현

}
