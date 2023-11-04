package ru.fenris06.comtroller.mpa;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fenris06.dto.MpaDto;
import ru.fenris06.service.mpa.MpaService;

import java.util.List;

@RestController
@RequestMapping("/mpa")
@RequiredArgsConstructor
public class MpaController {
    private final MpaService mpaService;

    @GetMapping
    public List<MpaDto> getMpas(){
        return mpaService.getMpas();
    }

    @GetMapping("/{id}")
    public MpaDto getMpa(@PathVariable("id") Long id) {
        return mpaService.getMpa(id);
    }
}
