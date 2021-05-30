package Hierarchy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "HomeTask")
public class HomeTask extends Task implements Serializable {
    private static final long serialVersion=4L;
    public java.sql.Date startDate;
    public java.sql.Date endDate;
    @Embedded
    Address address;
    @OneToMany (cascade = CascadeType.PERSIST)
    public List<WorkTask> tasks=new ArrayList<>();
}


