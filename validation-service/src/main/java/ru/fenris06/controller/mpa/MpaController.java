package ru.fenris06.controller.mpa;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fenris06.client.mpa.MpaClient;

import ru.fenris06.dto.MpaDto;

import java.util.List;

@RestController
@RequestMapping("/mpa")
@RequiredArgsConstructor
@Tag(name = "Mpa-Controller", description = "Controller for films mpa")
public class MpaController {
    private final MpaClient mpaClient;

    @GetMapping
    @Operation(summary = "Get all mpa")
    public List<MpaDto> getMpas(){
        return mpaClient.getMpas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get mpa by id")
    public MpaDto getMpa(@PathVariable("id") Long id) {
        return mpaClient.getMpa(id);
    }
}
