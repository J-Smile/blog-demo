package com.smile.service;

import com.smile.dao.BlogMapper;
import com.smile.dao.SortMapper;
import com.smile.domain.Sort;
import com.smile.utils.UUid;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Smile
 * @description:
 * @create: 2020-03-25 09:57
 */
@Service
public class SortService {

    @Resource
    private SortMapper sortMapper;
    @Resource
    private BlogMapper blogMapper;


    public List<Sort> getAll() {
        return sortMapper.selectAll();
    }

    public void saveSort(Sort sort) {
        if (sort.getSid() != null) {
            sortMapper.updateByPrimaryKey(sort);
        } else {
            sort.setStatus(true);
            sortMapper.insert(sort);
        }
    }


    public void delSort(String sid) {
        sortMapper.deleteByPrimaryKey(sid);
        Example example = new Example(Sort.class);
        example.createCriteria().andEqualTo("sid", sid);
        blogMapper.deleteByExample(example);
    }

    public Sort getSort(Integer sid) {
        return sortMapper.selectByPrimaryKey(sid);
    }

    public List<Sort> getSortStatus() {
        return sortMapper.sortStatus();
    }
}
