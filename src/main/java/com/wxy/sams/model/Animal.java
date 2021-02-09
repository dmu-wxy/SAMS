package com.wxy.sams.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "流浪动物实体类",description = "描述")
public class Animal implements Serializable {

    @ApiModelProperty(value = "id")
    private int aid;

    @ApiModelProperty(value = "流浪动物名称")
    private String aname;

    @ApiModelProperty(value = "流浪动物品种")
    private String breed;

    @ApiModelProperty(value = "流浪动物位置")
    private String p_addr;

    @ApiModelProperty(value = "流浪动物性别")
    private String gender;

    @ApiModelProperty(value = "流浪动物生日")
    private Date birth;

}
