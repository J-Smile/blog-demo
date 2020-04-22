package com.smile.service;

import com.smile.dao.BlogTagMapper;
import com.smile.dao.TagMapper;
import com.smile.domain.Blog;
import com.smile.domain.BlogTag;
import com.smile.domain.Sort;
import com.smile.domain.Tag;
import com.smile.utils.UUid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: 淳淳
 * @create: 2020-03-25 11:21
 * @description: 标签
 **/
@Service
public class TagService {
    @Resource
    private TagMapper tagMapper;


    public List<Tag> getAll() {
        return tagMapper.selectAll();
    }

    public void saveTag(Tag tag) {
        if (tag.getTid() != null) {
            tagMapper.updateByPrimaryKey(tag);
        } else {
            tag.setStatus(true);
            tagMapper.insert(tag);
        }
    }


    public void delTag(int tid) {
        tagMapper.deleteByPrimaryKey(tid);
    }

    public Tag getTag(int tid) {
        return tagMapper.selectByPrimaryKey(tid);
    }

    public List<Tag> getSortStatus() {
        return tagMapper.tagStatus();
    }


}
