package org.gtug.karlsruhe.bunnycacher.common.domain;

import java.io.Serializable;
import java.util.Date;

public class TagDto implements Serializable {

    private String message;
    private Date timestamp;
    private String userId;
    private long eid;

    public TagDto() {

    }

    public TagDto(String message, Date timestamp, String userId, long eid) {
        this();
        this.message = message;
        this.timestamp = timestamp;
        this.userId = userId;
        this.eid = eid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getEid() {
        return eid;
    }

    public void setEid(long eid) {
        this.eid = eid;
    }

}
