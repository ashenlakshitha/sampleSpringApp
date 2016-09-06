package com.intervest.staysure.database.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by charith on 9/4/16.
 */
@Entity
@Table(name = "t_policy")
public class Policy implements Serializable {

    // TODO add not null fields
    @Id
    @Column(name = "status", unique = true, nullable = false)
    private int status;

    @Column(name = "description")
    private String description;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
