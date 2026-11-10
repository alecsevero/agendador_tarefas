package com.javanauta.agendadortarefas.Infrastructe.Repository;

import com.javanauta.agendadortarefas.Infrastructe.Entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {
}
