package platform.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


@Entity
@Table(name = "Codes")
public class Code {

    @Id
    @JsonIgnore
    private String id;

    @Column(length = 2048)
    @NotEmpty
    private String code;

    @Column
    private final LocalDateTime date;

    @NotNull
    @Column
    private Long time;

    @JsonIgnore
    @Column
    private Boolean timeRestriction;

    @NotNull
    @Column
    private Integer views;

    @JsonIgnore
    @Column
    private Boolean viewsRestriction;


    public Code() {
        this.id = UUID.randomUUID().toString();
        this.date = LocalDateTime.now();
    }


    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getDate() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return date.format(dateFormat);
    }

    @JsonIgnore
    public LocalDateTime getRawLocalDateTime() {
        return date;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Boolean isViewsRestriction() {
        return viewsRestriction;
    }

    public Boolean isTimeRestriction() {
        return timeRestriction;
    }

    public void setRestrictions() {
        this.timeRestriction = time > 0;
        this.viewsRestriction = views > 0;
    }
}
