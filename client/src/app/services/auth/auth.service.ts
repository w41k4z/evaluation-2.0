import { inject, Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  router = inject(Router);

  constructor() {}

  isAuthenticated(): boolean {
    return localStorage.getItem('token') ? true : false;
  }

  authenticate(token: string): void {
    localStorage.setItem('token', token);
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigateByUrl('/page/log-in');
  }
}
