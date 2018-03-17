package org.nix.web.controller.process;

import org.apache.log4j.Logger;
import org.nix.annotation.ValidatePermission;
import org.nix.dao.service.OvertimeRulesService;
import org.nix.domain.entity.OvertimeRules;
import org.nix.domain.entity.entitybuild.OvertimeRulesBuild;
import org.nix.web.controller.utils.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Create by zhangpe0312@qq.com on 2018/3/14.
 */
@RestController
public class OvertimeRulesController {
    //日志记录
    private static Logger logger = Logger.getLogger(OvertimeRulesController.class);

    @Autowired
    private OvertimeRulesService overtimeRulesService;

    /**
     * 添加计费模块
     *
     * @param name         计费名称
     * @param description  计费时间段
     * @param payMultiples 计费倍数
     * @param note         备注
     * @return 操作结果
     */
    @RequestMapping(value = "/addOvertimeRules", method = RequestMethod.POST)
    @ValidatePermission
    @ResponseBody
    public Map<String, Object> addOvertimeRules(@RequestParam("name") String name,
                                                @RequestParam("description") String description,
                                                @RequestParam("payMultiples") double payMultiples,
                                                @RequestParam("note") String note) {

        //如果参数不是要求的，则返回参数格式错误异常
        if (!description.equals("weekend") && !description.equals("usually")) {
            throw new IllegalArgumentException();
        }

        OvertimeRules overtimeRules = new OvertimeRulesBuild()
                .setCreateTime()
                .setDescription(description)
                .setName(name)
                .setNote(note)
                .setPayMultiples(payMultiples)
                .build();

        overtimeRulesService.save(overtimeRules);

        logger.info("添加" + description + "的加班工资规则成功");

        return new ResultMap()
                .resultSuccess()
                .send();
    }

    /**
     * 查看工资标准列表
     * @param limit 每页多少行
     * @param currentPage 当前页
     * @return 操作结果
     */
    @RequestMapping(value = "/wageScale" , method = RequestMethod.POST)
    public
    Map<String, Object> wageScale( @RequestParam("limit") int limit,
                                   @RequestParam("currentPage") int currentPage)  {




        return new ResultMap()
                .resultSuccess()
                .send();
    }

}
