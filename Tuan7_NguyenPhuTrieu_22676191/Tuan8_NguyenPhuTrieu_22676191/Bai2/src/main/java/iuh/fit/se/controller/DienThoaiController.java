package iuh.fit.se.controller;

import iuh.fit.se.entity.DienThoai;
import iuh.fit.se.entity.NhaCungCap;
import iuh.fit.se.repository.DienThoaiRepository;
import iuh.fit.se.repository.NhaCungCapRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class DienThoaiController {

    @Autowired
    private DienThoaiRepository dienThoaiRepository;

    @Autowired
    private NhaCungCapRepository nhaCungCapRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping("/")
    public String viewHomePage(@RequestParam(value = "maNCC", required = false) String maNCC, Model model) {
        List<DienThoai> listDienThoai;
        if (maNCC != null && !maNCC.isEmpty()) {
            listDienThoai = dienThoaiRepository.findByNhaCungCap_MaNCC(maNCC);
        } else {
            listDienThoai = dienThoaiRepository.findAll();
        }
        model.addAttribute("listDienThoai", listDienThoai);
        model.addAttribute("uploadDir", uploadDir);
        return "danh-sach";
    }

    @GetMapping("/quan-ly")
    public String viewQuanLyPage(Model model) {
        model.addAttribute("listDienThoai", dienThoaiRepository.findAll());
        model.addAttribute("uploadDir", uploadDir);
        return "quan-ly";
    }

    @GetMapping("/them-moi")
    public String showNewDienThoaiForm(Model model) {
        model.addAttribute("dienThoai", new DienThoai());
        model.addAttribute("listNCC", nhaCungCapRepository.findAll());
        return "them-moi";
    }

    @PostMapping("/luu")
    public String saveDienThoai(@Valid @ModelAttribute("dienThoai") DienThoai dienThoai,
                                BindingResult result,
                                @RequestParam("maNCC") String maNCC,
                                @RequestParam("hinhAnhFile") MultipartFile file,
                                Model model) {

        NhaCungCap ncc = nhaCungCapRepository.findById(maNCC)
                .orElseThrow(() -> new IllegalArgumentException("Invalid NCC Id:" + maNCC));
        dienThoai.setNhaCungCap(ncc);

        if (result.hasErrors()) {
            model.addAttribute("listNCC", nhaCungCapRepository.findAll());
            return (dienThoai.getMaDT() == null || dienThoai.getMaDT().isEmpty()) ? "them-moi" : "cap-nhat";
        }

        if (!file.isEmpty()) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            dienThoai.setHinhAnh(fileName);

            try {
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                try (InputStream inputStream = file.getInputStream()) {
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
        } else if (dienThoai.getMaDT() != null) {
            DienThoai existingDt = dienThoaiRepository.findById(dienThoai.getMaDT()).orElse(null);
            if (existingDt != null) {
                dienThoai.setHinhAnh(existingDt.getHinhAnh());
            }
        }

        dienThoaiRepository.save(dienThoai);
        return "redirect:/quan-ly";
    }

    @GetMapping("/cap-nhat/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") String id, Model model) {
        DienThoai dienThoai = dienThoaiRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid dien thoai Id:" + id));

        model.addAttribute("dienThoai", dienThoai);
        model.addAttribute("listNCC", nhaCungCapRepository.findAll());
        model.addAttribute("uploadDir", uploadDir);
        return "cap-nhat";
    }

    @GetMapping("/xoa/{id}")
    public String deleteDienThoai(@PathVariable(value = "id") String id) {
        dienThoaiRepository.deleteById(id);
        return "redirect:/quan-ly";
    }
}