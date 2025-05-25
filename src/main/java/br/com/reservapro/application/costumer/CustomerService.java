package br.com.reservapro.application.costumer;

import br.com.reservapro.domain.Customer;
import br.com.reservapro.exceptions.AlreadyExistsException;
import br.com.reservapro.exceptions.RecursoNaoEncontradoException;
import br.com.reservapro.exceptions.enums.RuntimeErroEnum;
import br.com.reservapro.infrastructure.database.entities.costumer.CustomerEntity;
import br.com.reservapro.infrastructure.database.mappers.CustomerPersistenceMapper;
import br.com.reservapro.infrastructure.database.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerPersistenceMapper mapper;

    public List<Customer> findAll() {
        return this.customerRepository.findAll()
                .stream()
                .map(this.mapper::mapToDomain).collect(Collectors.toList());
    }

    public Customer create(Customer customer) {
        this.customerRepository.findByEmail(customer.getEmail()).orElseThrow(
                () -> new AlreadyExistsException("Email already in use: " + customer.getEmail())
        );
        return this.mapper.mapToDomain(this.customerRepository.save(this.mapper.mapToEntity(customer)));
    }

    public Customer findById(String id) {
        return this.mapper.mapToDomain(this.customerRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException(RuntimeErroEnum.ERR0003)
        ));
    }

    public Customer update(String id, Customer customer) {
        CustomerEntity customerToEdit = this.customerRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException(RuntimeErroEnum.ERR0003)
        );
        customerToEdit.setEmail(customer.getEmail() == null ? customerToEdit.getEmail() : customer.getEmail());
        customerToEdit.setPhoneNumber(customer.getPhoneNumber() == null ? customerToEdit.getPhoneNumber() : customer.getPhoneNumber());
        return this.mapper.mapToDomain(this.customerRepository.save(customerToEdit));
    }

    public void deleteOne(String id) {
        this.customerRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException(RuntimeErroEnum.ERR0003)
        );
        this.customerRepository.deleteById(id);
    }

    public void deleteMany(List<String> ids) {
        ids.forEach(id -> this.customerRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException(RuntimeErroEnum.ERR0003)
        ));

        this.customerRepository.deleteAllById(ids);
    }
}
