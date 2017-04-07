package com.demo.console.models.entities;

import java.io.Serializable;

public interface BaseDaoObject extends Serializable {
    public Long getId();

    public String getPk();
}
