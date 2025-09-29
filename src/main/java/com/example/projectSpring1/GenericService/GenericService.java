package com.example.projectSpring1.GenericService;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public class GenericService<T, ID> {

    protected JpaRepository<T, ID> repository;
    public GenericService() {}

    public GenericService(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    // Create
    public T save(T entity) {
        return repository.save(entity);
    }

    // Read all
    public List<T> findAll() {
        return repository.findAll();
    }

    // Read by ID
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    // Update (về cơ bản là save lại entity với id đã tồn tại)
    public T update(T entity) {
        return repository.save(entity);
    }

    // Delete by ID
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    // Delete entity
    public void delete(T entity) {
        repository.delete(entity);
    }

    // Count
    public long count() {
        return repository.count();
    }
}
