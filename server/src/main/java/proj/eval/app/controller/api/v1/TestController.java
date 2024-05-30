package proj.eval.app.controller.api.v1;

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
}
