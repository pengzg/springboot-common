package xyz.carjoy.question.common.base.controller;

import com.baidu.ueditor.ActionEnter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


@RestController
@RequestMapping("/admin/base/baseCommonControl")
public class BaseCommonController {
    private static final Logger log = LoggerFactory.getLogger(BaseCommonController.class);

    @Autowired
    private Environment env;

    @RequestMapping("/dispatch")
    public void config(HttpServletRequest request, HttpServletResponse response, String action) {
        response.setContentType("application/json");
        // Resource resource = new ClassPathResource(“excel/productBasicPresetData.xml”);
        // HSSFWorkbook hssfWorkbook = new HSSFWorkbook(resource.getInputStream());
        log.info("ueditor.config-location==>"+env.getProperty("ueditor.config-filename"));

        log.info("rootpath==>"+request.getSession().getServletContext().getRealPath("/"));
        log.info("上传图片初始化");
        String saveRootPath = "/data/web/web";///data/web/sqkx/web_trunk/
        try {
            request.setAttribute("ueditor_config_filename",env.getProperty("ueditor.config-filename"));
            String exec = new ActionEnter(request, saveRootPath).exec();

            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}