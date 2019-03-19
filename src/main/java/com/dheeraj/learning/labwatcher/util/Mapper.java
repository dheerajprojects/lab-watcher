package com.dheeraj.learning.labwatcher.util;

import com.dheeraj.learning.labwatcher.dto.PerfStatDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static List<PerfStatDTO> copyResultsToDTO(List results) {
        List resultDTOs = new ArrayList();

        for (Object ent : results) {
            PerfStatDTO dto = new PerfStatDTO();
            BeanUtils.copyProperties(ent, dto);
            resultDTOs.add(dto);
        }

        return resultDTOs;
    }
}
