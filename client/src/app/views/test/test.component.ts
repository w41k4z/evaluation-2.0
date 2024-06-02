import { Component } from '@angular/core';
import {
  FormArray,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { CustomValidators } from '../../validators/CustomValidators';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrl: './test.component.scss',
})
export class TestComponent {
  testForm: FormGroup;
  submitted = false;

  constructor(private formBuilder: FormBuilder) {
    this.testForm = this.formBuilder.group({
      contact: ['', [Validators.required, CustomValidators.malagasyNumber]],
      email: ['', [Validators.required, Validators.email]],
      choices: this.formBuilder.array(
        [new FormControl(true), new FormControl(false), new FormControl(false)],
        [CustomValidators.minSelectedCheckboxes(2)]
      ),
    });
  }

  submitForm() {
    this.submitted = true;
    if (this.testForm.invalid) {
      return;
    }
    console.log(this.testForm.value);
    this.submitted = false;
    this.testForm.reset();
  }

  get f() {
    return this.testForm.controls;
  }

  get choices() {
    return this.testForm.get('choices') as FormArray;
  }
}
