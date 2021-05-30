package Hierarchy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("W")
public class WorkTask extends Task implements Serializable {
    private static final long serialVersion=3L;
    public int cost;
}
