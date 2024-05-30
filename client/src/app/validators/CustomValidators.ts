import { AbstractControl, FormArray, ValidatorFn } from '@angular/forms';

export class CustomValidators {
  public static malagasyNumber(
    control: AbstractControl
  ): { [key: string]: any } | null {
    const pattern = /^03[234]\d{7}$/;
    if (!control.value || !pattern.test(control.value)) {
      return { malagasyNumber: true };
    }
    return null;
  }

  public static minSelectedCheckboxes(min = 1): ValidatorFn {
    return (formArray: AbstractControl) => {
      if (formArray instanceof FormArray) {
        const totalSelected = formArray.controls
          .map((control) => control.value)
          .reduce((prev, next) => (next ? prev + next : prev), 0);
        return totalSelected >= min ? null : { required: true };
      }
      throw new Error('formArray is not an instance of FormArray');
    };
  }
}
