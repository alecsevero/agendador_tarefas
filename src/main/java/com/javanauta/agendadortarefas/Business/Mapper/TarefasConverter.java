package com.javanauta.agendadortarefas.Business.Mapper;

import com.javanauta.agendadortarefas.Business.Dto.TarefasDto;
import com.javanauta.agendadortarefas.Infrastructe.Entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasEntity paraTarefaEntity(TarefasDto tarefasDto);

    TarefasDto paraTarefaDto(TarefasEntity tarefasEntity);
}
