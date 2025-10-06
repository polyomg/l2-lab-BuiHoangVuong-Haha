package poly.edu.Java5_Lab3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Staff {

    private String id;
    private String fullname;
    @Builder.Default
    private String photo = "trump-logo.png";
    @Builder.Default
    private boolean gender = true;
    @Builder.Default
    private Date birthday = new Date();
    @Builder.Default
    private double salary = 12345.6789;
    @Builder.Default
    private Integer level = 0;

}
