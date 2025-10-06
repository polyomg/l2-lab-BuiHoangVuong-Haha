package poly.edu.lab4.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import poly.edu.lab4.model.Staff;

@Controller
@RequestMapping("/staff")
public class StaffController {

    // Hiển thị form mới
    @GetMapping("/form")
    public String formStaff(Model model) {
        model.addAttribute("staff", new Staff());
        model.addAttribute("message", "Vui lòng nhập thông tin nhân viên!");
        return "lession1/staff-create";
    }

    // Xử lý submit form với validation
    @PostMapping("/save")
    public String saveStaff(
            Model model,
            @Valid @ModelAttribute("staff") Staff staff,
            BindingResult bindingResult,
            @RequestPart("photo_file") MultipartFile photoFile) {

        // Kiểm tra lỗi validation
        if (bindingResult.hasErrors()) {
            model.addAttribute("message", "Có lỗi xảy ra, vui lòng kiểm tra lại!");
            return "lession1/staff-create";
        }

        // Xử lý file upload
        if (!photoFile.isEmpty()) {
            staff.setPhoto(photoFile.getOriginalFilename());
        }

        // Xử lý thành công
        model.addAttribute("message", "Xin chào " + staff.getFullname());
        return "lession1/staff-create";
    }

}
