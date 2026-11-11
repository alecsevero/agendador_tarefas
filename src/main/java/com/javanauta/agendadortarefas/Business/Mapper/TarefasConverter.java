package com.javanauta.agendadortarefas.Business.Mapper;

import com.javanauta.agendadortarefas.Business.Dto.TarefasDto;
import com.javanauta.agendadortarefas.Infrastructe.Entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasEntity paraTarefaEntity(TarefasDto tarefasDto);

    TarefasDto paraTarefaDto(TarefasEntity tarefasEntity);

    List<TarefasEntity> paraListaTarefasEntity(List<TarefasDto> tarefasDtos);

    List<TarefasDto> paraListaTarefasDto(List<TarefasEntity> tarefasEntities);
}
