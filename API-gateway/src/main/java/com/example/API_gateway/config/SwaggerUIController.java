package com.example.API_gateway.config;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SwaggerUIController {

    @GetMapping(value = "/swagger-ui.html", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String swaggerUI() {
        return "<!DOCTYPE html>\n" +
               "<html lang=\"en\">\n" +
               "<head>\n" +
               "    <meta charset=\"UTF-8\">\n" +
               "    <title>Swagger UI</title>\n" +
               "    <link rel=\"stylesheet\" type=\"text/css\" href=\"/webjars/swagger-ui/swagger-ui.css\" >\n" +
               "</head>\n" +
               "<body>\n" +
               "<div id=\"swagger-ui\"></div>\n" +
               "<script src=\"/webjars/swagger-ui/swagger-ui-bundle.js\"></script>\n" +
               "<script src=\"/webjars/swagger-ui/swagger-ui-standalone-preset.js\"></script>\n" +
               "<script>\n" +
               "    window.onload = function() {\n" +
               "        // Begin Swagger UI call region\n" +
               "        const ui = SwaggerUIBundle({\n" +
               "            url: \"/v3/api-docs\", // Replace with your API docs endpoint\n" +
               "            dom_id: '#swagger-ui',\n" +
               "            deepLinking: true,\n" +
               "            presets: [\n" +
               "                SwaggerUIBundle.presets.apis,\n" +
               "                SwaggerUIStandalonePreset\n" +
               "            ],\n" +
               "            plugins: [\n" +
               "                SwaggerUIBundle.plugins.DownloadUrl\n" +
               "            ],\n" +
               "            layout: \"StandaloneLayout\"\n" +
               "        })\n" +
               "        window.ui = ui\n" +
               "    }\n" +
               "</script>\n" +
               "</body>\n" +
               "</html>";
    }
}