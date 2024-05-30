package proj.eval.app.controller.api.v1;

import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tests")
public class TestController {

  @GetMapping
  public String index() {
    return "Test mapping imbriqué ok";
  }

  @GetMapping("/resource")
  public ResponseEntity<Resource> resource() throws IOException {
    ClassPathResource classPathResource = new ClassPathResource(
      "/static/public/CV.pdf"
    );
    InputStreamResource resource = new InputStreamResource(
      new FileInputStream(classPathResource.getFile())
    );
    HttpHeaders headers = new HttpHeaders();
    headers.add(
      "Content-Disposition",
      String.format(
        "attachment; filename=\"%s\"",
        classPathResource.getFile().getName()
      )
    );
    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
    headers.add("Pragma", "no-cache");
    headers.add("Expires", "0");
    return ResponseEntity
      .ok()
      .headers(headers)
      .contentLength(classPathResource.getFile().length())
      .contentType(MediaType.APPLICATION_OCTET_STREAM)
      .body(resource);
  }
}
