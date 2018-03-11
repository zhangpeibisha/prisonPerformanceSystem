package org.nix.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.nix.domain.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Create by zhangpe0312@qq.com on 2018/3/9.
 *
 * 加班规则
 */
@Entity
@Table(name = "OvertimeRules")
@JsonIgnoreProperties(value={"handler","hibernateLazyInitializer" , "overtimeRecords" , "overtimeRecords"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class OvertimeRules extends BaseEntity{

    //计费名字
    private String name;
    //规则描述
    private String description;
    //计费倍数
    private double payMultiples;
    //备注
    private String note;

    //一个计费规则有多个加班记录，一个加班记录只有一个计费规则
    private Set<OvertimeRecord> overtimeRecords = new HashSet<>();

    @Column(name = "name",length = 20 , unique = true)
    public String getName() {
        return name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @Column(name = "payMultiples",columnDefinition = "double(10,2) default '1.00'",length = 10)
    public double getPayMultiples() {
        return payMultiples;
    }

    @Column(name = "note")
    public String getNote() {
        return note;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rules")
    public Set<OvertimeRecord> getOvertimeRecords() {
        return overtimeRecords;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPayMultiples(double payMultiples) {
        this.payMultiples = payMultiples;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setOvertimeRecords(Set<OvertimeRecord> overtimeRecords) {
        this.overtimeRecords = overtimeRecords;
    }
}
