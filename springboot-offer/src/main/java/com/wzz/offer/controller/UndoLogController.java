package com.wzz.offer.controller;

import java.util.List;

import com.wzz.offer.entity.UndoLog;
import com.wzz.offer.service.IUndoLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2022-05-10
 */
@RestController
@RequestMapping("/system/log")
public class UndoLogController //extends BaseController
{
    private String prefix = "system/log";

    @Autowired
    private IUndoLogService undoLogService;

    //@RequiresPermissions("system:log:view")
    @GetMapping()
    public String log()
    {
        return prefix + "/log";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    //@RequiresPermissions("system:log:list")
    @PostMapping("/list")
    @ResponseBody
    public List<UndoLog> list(@RequestBody UndoLog undoLog)
    {
        List<UndoLog> list = undoLogService.selectUndoLogList(undoLog);
        return list;
    }


    /**
     * 新增保存【请填写功能名称】
     */
    //@RequiresPermissions("system:log:add")
    //@Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public int addSave(@RequestBody UndoLog undoLog)
    {
        return undoLogService.insertUndoLog(undoLog);
    }

    /**
     * 修改【请填写功能名称】
     */
    //@RequiresPermissions("system:log:edit")
    @GetMapping("/edit/{id}")
    public int edit(@RequestBody UndoLog undoLog)
    {
        return undoLogService.updateUndoLog(undoLog);
    }

    /**
     * 删除【请填写功能名称】
     */
    //@RequiresPermissions("system:log:remove")
    //@Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public int remove(String ids)
    {
        return undoLogService.deleteUndoLogByIds(ids);
    }
}
