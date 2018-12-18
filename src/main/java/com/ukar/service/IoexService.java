package com.ukar.service;

import com.ukar.entity.EthListData;

import java.io.IOException;
import java.util.List;

/**
 * Created by jyou on 2018/5/5.
 */
public interface IoexService {

    List<EthListData> getEthList() throws IOException;

    void add(List<EthListData> list) throws IOException;

    void updateUser();
}
