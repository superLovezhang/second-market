package com.superflower.market.controller;


import com.superflower.market.entity.Result;
import com.superflower.market.entity.Subject;
import com.superflower.market.entity.vo.SubjectVo;
import com.superflower.market.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zz
 * @since 2020-05-08
 */
@RestController
@RequestMapping("/subject")
@CrossOrigin
@Api(tags = "分类管理")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @ApiOperation("添加分类")
    @PostMapping("/addSubject")
    public Result addSubject(@RequestBody Subject subject) {
        boolean b = subjectService.save(subject);
        return b ? Result.successWithoutData("添加成功") : Result.fail(2001l, "添加失败");
    }

    @ApiOperation("查询所有分类及其子分类")
    @GetMapping("/allSubject")
    public Result allSubject() {
        List<SubjectVo> list = subjectService.findAll();
        return Result.success("查询成功", list);
    }

    @ApiOperation("根据父id查询子分类")
    @GetMapping("/showChildSubject/{pid}")
    public Result showChildSubject(@PathVariable Integer pid) {
        List<Subject> list = subjectService.findSubjectByPid(pid);
        return  Result.success("查询成功", list);
    }

    @ApiOperation("根据id查询分类信息")
    @GetMapping("/showSubjectById/{id}")
    public Result showSubjectById(@PathVariable Integer id) {
        Subject subject = subjectService.getById(id);
        return  Result.success("查询成功", subject);
    }


}

