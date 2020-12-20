package it.freshfruits.application.repository;

import it.freshfruits.domain.entity.FruitType;

import java.util.List;

public interface FruitTypeRepository {

    public void insert(FruitType fruit);

    public Boolean update(FruitType fruit);

    public FruitType getFruitType(Integer id);

    public List<FruitType> getFruits();

    public Integer getFruitTypeNumber();
}
