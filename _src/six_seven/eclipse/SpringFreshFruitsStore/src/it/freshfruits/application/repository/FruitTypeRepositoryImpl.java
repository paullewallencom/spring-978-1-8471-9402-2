package it.freshfruits.application.repository;

import it.freshfruits.domain.entity.FruitType;
import it.freshfruits.domain.entity.FruitTypeImpl;
import it.freshfruits.domain.vo.FruitMap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("fruitRepository")
public class FruitTypeRepositoryImpl extends SqlMapClientTemplate implements FruitTypeRepository {

    @SuppressWarnings("unchecked")
    public List<FruitType> getFruits() {
        return queryForList("selectFruits");
    }

    public FruitType getFruitType(Integer id) {
        FruitMap map = (FruitMap) queryForObject("selectFruitTypeVO", id);
        return new FruitTypeImpl.Builder(map.getName(), map.getId(), map.getPrice()).build();
    }

    public Integer getFruitTypeNumber() {
        return (Integer) queryForObject("selectFruitTypeNumber");
    }

    public void insert(FruitType fruit) {
        insert("insertFruitTypeVO", fruit);
    }

    public Boolean update(FruitType fruit) {
        return update("updateFruitTypeVO", fruit) == 1 ? true : false;
    }

    @Autowired
    public void setSqlMapClient(@Qualifier("sqlMapClient") SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }
}
