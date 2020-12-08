package com.kairong.controller;

import com.kairong.util.CommonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.kairong.controller
 * @date:2020-12-07
 */
@RestController
@RequestMapping("/connect")
public class ConnectController {

    @RequestMapping("/checkNet")
    public CommonResult checkNet() {
        return CommonResult.success(1);
    }
}
