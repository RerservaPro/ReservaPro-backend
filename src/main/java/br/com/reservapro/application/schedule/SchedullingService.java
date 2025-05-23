package br.com.reservapro.application.schedule;

import br.com.reservapro.domain.Schedulling;
import br.com.reservapro.exceptions.RecursoNaoEncontradoException;
import br.com.reservapro.exceptions.enums.RuntimeErroEnum;
import br.com.reservapro.infrastructure.database.entities.schedule.SchedullingEntity;
import br.com.reservapro.infrastructure.database.mappers.SchedulePersistenceMapper;
import br.com.reservapro.infrastructure.database.repositories.SchedullingRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.AlreadyBuiltException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchedullingService {
    private SchedullingRepositoryImpl schedullingRepository;
    private SchedulePersistenceMapper mapper;

    public List<Schedulling> findAll() {
        return this.schedullingRepository.findAll()
                .stream()
                .map(s -> this.mapper.mapToDomain(s))
                .collect(Collectors.toList());
    }

    public Schedulling findById(String id) {
        return this.mapper.mapToDomain(this.schedullingRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException(RuntimeErroEnum.ERR0003)
        ));
    }

    public Schedulling create(Schedulling schedulling) {
        SchedullingEntity schedullingEntity = this.schedullingRepository.save(this.mapper.mapToEntity(schedulling));
        return this.mapper.mapToDomain(schedullingEntity);
    }


    public Schedulling update(String id, Schedulling schedulling) {
        SchedullingEntity recoveredData = this.schedullingRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException(RuntimeErroEnum.ERR0003)
        );

        if (recoveredData.getSchedullingDay() != null &&
                recoveredData.getSchedullingDay().equals(schedulling.getSchedullingDate())) {
            throw new AlreadyBuiltException("Agendamento já existente para esta data.");
        }
        recoveredData.setSchedullingDay(schedulling.getSchedullingDate());
        return this.mapper.mapToDomain(this.schedullingRepository.save(recoveredData));
    }

    public void delete(String id) {
        this.schedullingRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException(RuntimeErroEnum.ERR0003)
        );
        this.schedullingRepository.deleteById(id);
    }

    public void deleteMany(List<String> ids) {
        ids.forEach(id -> this.schedullingRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException(RuntimeErroEnum.ERR0003)
        ));

        this.schedullingRepository.deleteAllById(ids);
    }
}
