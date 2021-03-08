package com.kunlun.system.dao;

import com.kunlun.system.model.MachineModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMachineDao {
    public List<MachineModel> getAllMachine(MachineModel MachineModel, int currentPage, int pageSize) throws Exception;
}
