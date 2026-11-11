package com.javanauta.agendadortarefas.Business;

import com.javanauta.agendadortarefas.Business.Dto.TarefasDto;
import com.javanauta.agendadortarefas.Business.Mapper.TarefaUpdateConverter;
import com.javanauta.agendadortarefas.Business.Mapper.TarefasConverter;
import com.javanauta.agendadortarefas.Infrastructe.Entity.TarefasEntity;
import com.javanauta.agendadortarefas.Infrastructe.Enums.StatusNotificacaoEnum;
import com.javanauta.agendadortarefas.Infrastructe.Excepitions.ResourceNotFoundException;
import com.javanauta.agendadortarefas.Infrastructe.Repository.TarefasRepository;
import com.javanauta.agendadortarefas.Infrastructe.Security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefasDto gravarTarefa(String token, TarefasDto tarefasDto) {
        String email = jwtUtil.extractEmailToken(token.substring(7));

        tarefasDto.setDataCriacao(LocalDateTime.now());
        tarefasDto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        tarefasDto.setEmailUsuario(email);
        TarefasEntity entity = tarefaConverter.paraTarefaEntity(tarefasDto);

        return tarefaConverter.paraTarefaDto(tarefasRepository.save(entity));
    }

    public List<TarefasDto> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return tarefaConverter.paraListaTarefasDto(
                tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefasDto> buscaTarefasPorEmail(String token) {
        String email = jwtUtil.extractEmailToken(token.substring(7));
        List<TarefasEntity> listsTarefas = tarefasRepository.findByEmailUsuario(email);

        return tarefaConverter.paraListaTarefasDto(listsTarefas);
    }

    public void deletaTarefaPorId(String id) {
        try {
            tarefasRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao deletar tarefa por id, id inexistente "
                    + id, e.getCause());
        }
    }

    public TarefasDto alteraStatus(StatusNotificacaoEnum status, String id) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Tarefa não encontrada " + id));
            entity.setStatusNotificacaoEnum(status);
            return tarefaConverter.paraTarefaDto(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa" + e.getCause());
        }
    }

    public TarefasDto updateTarefas(TarefasDto dto, String id) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Tarefa não encontrada " + id));
            tarefaUpdateConverter.updateTarefas(dto, entity);
            return tarefaConverter.paraTarefaDto(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa" + e.getCause());
        }
    }
}
