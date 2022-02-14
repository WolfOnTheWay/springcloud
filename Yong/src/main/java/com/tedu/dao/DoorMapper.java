package com.tedu.dao;

import com.tedu.pojo.Door;

import java.util.List;

public interface DoorMapper {
    public List<Door> findAll();
    public void deleteById(Integer id);
    public void add(Door door);
    public Door selectById(Integer id);
    public void updateById(Door door);
}
