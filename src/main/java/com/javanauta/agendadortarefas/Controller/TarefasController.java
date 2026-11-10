package com.javanauta.agendadortarefas.Controller;

import com.javanauta.agendadortarefas.Business.Dto.TarefasDto;
import com.javanauta.agendadortarefas.Business.TarefasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tarefas")
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDto> gravarTarefas(@RequestBody TarefasDto tarefasDto,
                                                    @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, tarefasDto));
    }
}
