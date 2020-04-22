package com.smile.controller;

import com.smile.domain.Author;
import com.smile.service.AuthorService;
import com.smile.vo.BaseResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-07 22:22
 */
@RequestMapping("author")
@RestController
public class AuthorController {

    private final AuthorService authorService ;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public BaseResult author (){
        return BaseResult.success(authorService.author());
    }

    @PutMapping
    public BaseResult editAuthor(@RequestBody Author author) {
        authorService.editAuthor(author);
        return BaseResult.success();
    }
}
