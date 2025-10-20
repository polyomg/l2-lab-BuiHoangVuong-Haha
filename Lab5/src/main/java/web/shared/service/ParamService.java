package web.shared.service;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ParamService {

    @Autowired
    HttpServletRequest request;

    @Autowired
    ServletContext app;


    public String getString(String name, String defaultValue) {
        String value = request.getParameter(name);       // lấy tham số theo tên
        if (value == null || value.isEmpty()) {          // nếu không có giá trị
            return defaultValue;                         // trả giá trị mặc định
        }
        return value;                                    // nếu có => trả giá trị thật
    }

    // --- Đọc số nguyên ---
    public int getInt(String name, int defaultValue) {
        try {
            return Integer.parseInt(request.getParameter(name)); // chuyển chuỗi -> số
        } catch (Exception e) {
            return defaultValue; // nếu lỗi format hoặc null => dùng mặc định
        }
    }

    // --- Đọc số thực ---
    public double getDouble(String name, double defaultValue) {
        try {
            return Double.parseDouble(request.getParameter(name));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    // --- Đọc boolean ---
    public boolean getBoolean(String name, boolean defaultValue) {
        String value = request.getParameter(name);
        if (value == null) return defaultValue;             // không có -> mặc định
        return value.equalsIgnoreCase("true") || value.equals("1") || value.equalsIgnoreCase("on");
    }

    // --- Đọc ngày tháng ---
    public Date getDate(String name, String pattern) {
        try {
            String value = request.getParameter(name);
            if (value == null || value.isEmpty()) return null; // không có tham số -> null
            return new SimpleDateFormat(pattern).parse(value); // parse chuỗi -> Date
        } catch (Exception e) {
            throw new RuntimeException("Sai định dạng ngày!"); // theo yêu cầu đề
        }
    }

    // --- Lưu file upload ---
    public File save(MultipartFile file, String path) {
        try {
            if (file.isEmpty()) return null;  // nếu không upload file nào
            // Lấy đường dẫn thật của thư mục (VD: /uploads)
            String realPath = app.getRealPath(path);
            // Tạo thư mục nếu chưa có
            Files.createDirectories(Path.of(realPath));
            // Lưu file xuống server
            File savedFile = new File(realPath, file.getOriginalFilename());
            file.transferTo(savedFile);       // ghi file lên ổ đĩa
            return savedFile;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi lưu file: " + e.getMessage());
        }
    }
}
