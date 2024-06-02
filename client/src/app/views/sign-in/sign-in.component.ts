import { Component } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { SignInService } from '../../services/auth/sign-in.service';
import { AuthService } from '../../services/auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.scss',
})
export class SignInComponent {
  checkoutForm: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', Validators.required),
  });
  loading$ = this.signInService.loading$;
  error$ = this.signInService.error$;
  submitted = false;

  constructor(
    public signInService: SignInService,
    public authService: AuthService,
    public route: Router,
    public formBuilder: FormBuilder
  ) {}

  get f() {
    return this.checkoutForm.controls;
  }

  signIn() {
    this.submitted = true;
    if (this.checkoutForm.invalid) {
      return;
    }
    this.signInService
      .authenticate(
        this.checkoutForm.value.email,
        this.checkoutForm.value.password
      )
      .subscribe((response) => {
        alert('Sign in successful: ' + response.payload.token);
        this.authService.authenticate(response.payload.token);
        this.route.navigate(['/app/general-ranking']);
        this.reset();
      });
  }

  reset() {
    this.submitted = false;
    this.checkoutForm.reset();
  }
}
