package com.plutus.dto;

import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */
public class TableResponse {
    private Integer total;
    private Object rows;

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }

    public Integer getTotal() {

        return total;
    }

    public Object getRows() {
        return rows;
    }
}
