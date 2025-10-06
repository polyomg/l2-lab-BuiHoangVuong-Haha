package poly.edu.lab4.model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Staff {
    @NotBlank(message = "Email is not emty!")
    @Email(message = "Email không đúng format")

    private String id;

    @NotBlank(message = "Name is not emty!")
    private String fullname;

    @Builder.Default
    private String photo = "avt.jpg";

    @NotNull(message = "Select gender")
    @Builder.Default
    private Boolean gender=true;

    @NotNull(message = "Select date")
    @Past(message = "Ngày sinh không hợp lệ")
    @Builder.Default
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date birthday = new Date();

    @Min(value = 1000, message = "more than 1000")
    @NotNull(message = "Salary is not emty!")
    @Builder.Default
    private double salary = 12345.6789;

    @Builder.Default
    private Integer level = 0;



}
