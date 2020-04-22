package com.smile.service;

import com.smile.dao.NumberMapper;
import com.smile.domain.Number;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-08 21:47
 */
@Service
public class NumberService {

    @Resource
    private NumberMapper numberMapper;



    public void editNumber(Number number) {
        numberMapper.updateByPrimaryKey(number);
    }

    public Number number() {
        return numberMapper.number();
    }
}
