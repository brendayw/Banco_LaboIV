package ar.edu.utn.frbb.tup.persistence.entity;

public class BaseEntity {
    private final Long Id;
    public BaseEntity(Long id) {
        Id = id;
    }

    public Long getId() {
        return Id;
    }
}
