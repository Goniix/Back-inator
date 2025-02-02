package servlet.model;

import java.util.List;

import utils.DS;

public abstract class DAO<E> {
    public DS manager;

    public DAO(DS mng) {
        this.manager = mng;
    }

    public abstract List<E> findAll();

    public abstract E findById(int id);

    public abstract void add(E item);

    public abstract void remove(int id);
}
