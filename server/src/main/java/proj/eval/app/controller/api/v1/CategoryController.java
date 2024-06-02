package proj.eval.app.controller.api.v1;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import proj.eval.app.request.CreateCategoryRequest;
import proj.eval.app.service.CategoryService;
import proj.eval.app.util.ApiResponse;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

  private CategoryService service;

  public CategoryController(CategoryService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<ApiResponse> list() {
    ApiResponse apiResponse = new ApiResponse();
    apiResponse.setPayload(service.list());
    return ResponseEntity.ok(apiResponse);
  }

  @PostMapping
  public ResponseEntity<ApiResponse> create(
    @RequestBody @Valid CreateCategoryRequest category
  ) {
    ApiResponse apiResponse = new ApiResponse();
    apiResponse.setPayload(service.create(category.getName()));
    apiResponse.setMessage("Category created successfully");
    return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse> update(
    @PathVariable String id,
    @RequestParam String category
  ) {
    ApiResponse apiResponse = new ApiResponse();
    apiResponse.setPayload(service.update(Long.parseLong(id), category));
    apiResponse.setMessage("Category updated successfully");
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }
}
