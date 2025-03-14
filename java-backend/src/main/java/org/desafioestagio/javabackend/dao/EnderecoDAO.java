package org.desafioestagio.javabackend.dao;

import org.desafioestagio.javabackend.model.Endereco;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class EnderecoDAO {

    @PersistenceContext
    private EntityManager entityManager;

    // Salvar ou atualizar um endereço
    @Transactional
    public Endereco save(Endereco endereco) {
        if (endereco.getId() == null) {
            entityManager.persist(endereco);
            return endereco;
        } else {
            return entityManager.merge(endereco);
        }
    }

    // Listar todos os endereços
    public List<Endereco> findAll() {
        return entityManager.createQuery("FROM Endereco", Endereco.class).getResultList();
    }

    // Buscar endereço por ID
    public Optional<Endereco> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Endereco.class, id));
    }

    // Excluir endereço
    @Transactional
    public boolean deleteById(Long id) {
        Endereco endereco = entityManager.find(Endereco.class, id);
        if (endereco != null) {
            entityManager.remove(entityManager.contains(endereco) ? endereco : entityManager.merge(endereco));
            return true;
        }
        return false;
    }
}
