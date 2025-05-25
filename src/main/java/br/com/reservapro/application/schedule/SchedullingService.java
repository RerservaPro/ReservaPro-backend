package br.com.reservapro.application.schedule;

import br.com.reservapro.domain.Schedulling;
import br.com.reservapro.exceptions.RecursoNaoEncontradoException;
import br.com.reservapro.exceptions.enums.RuntimeErroEnum;
import br.com.reservapro.infrastructure.database.entities.schedule.SchedulingEntity;
import br.com.reservapro.infrastructure.database.mappers.SchedulePersistenceMapper;
import br.com.reservapro.infrastructure.database.repositories.SchedulingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.AlreadyBuiltException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchedullingService {
    private final SchedulingRepository schedulingRepository;
    private final SchedulePersistenceMapper mapper;

    public List<Schedulling> findAll() {
        return this.schedulingRepository.findAll()
                .stream()
                .map(this.mapper::mapToDomain)
                .collect(Collectors.toList());
    }

    public Schedulling findById(String id) {
        return this.mapper.mapToDomain(this.schedulingRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException(RuntimeErroEnum.ERR0003)
        ));
    }

    public Schedulling create(Schedulling schedulling) {
        SchedulingEntity schedulingEntity = this.schedulingRepository.save(this.mapper.mapToEntity(schedulling));
        return this.mapper.mapToDomain(schedulingEntity);
    }


    public Schedulling update(String id, Schedulling schedulling) {
        SchedulingEntity recoveredData = this.schedulingRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException(RuntimeErroEnum.ERR0003)
        );
        if (recoveredData.getSchedullingDay() != null &&
                recoveredData.getSchedullingDay().equals(schedulling.getSchedullingDate())) {
            throw new AlreadyBuiltException("Agendamento já existente para esta data.");
        }
        recoveredData.setSchedullingDay(schedulling.getSchedullingDate());
        return this.mapper.mapToDomain(this.schedulingRepository.save(recoveredData));
    }

    public void delete(String id) {
        this.schedulingRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException(RuntimeErroEnum.ERR0003)
        );
        this.schedulingRepository.deleteById(id);
    }

    public void deleteMany(List<String> ids) {
        ids.forEach(id -> this.schedulingRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException(RuntimeErroEnum.ERR0003)
        ));

        this.schedulingRepository.deleteAllById(ids);
    }
}
