import { Injectable } from '@angular/core';
import * as jwt from 'jwt-decode';
import { AuthService } from '../auth/auth.service';
import { ADMIN, TEAM } from '../../utils/Profil';
import { HttpClient } from '@angular/common/http';
import { env } from '../../../environment/env';

@Injectable({
  providedIn: 'root',
})
export class ProfileService {
  constructor(private authService: AuthService, public http: HttpClient) {}

  isAdmin() {
    const token = localStorage.getItem('token');
    if (this.authService.isAuthenticated() && token) {
      const deserializedToken: any = jwt.jwtDecode(token);
      return deserializedToken.authorities.includes(ADMIN);
    }
    return false;
  }

  isTeam() {
    const token = localStorage.getItem('token');
    if (this.authService.isAuthenticated() && token) {
      const deserializedToken: any = jwt.jwtDecode(token);
      return deserializedToken.authorities.includes(TEAM);
    }
    return false;
  }

  reinitialize() {
    this.http.get(`${env.api}/admin/reinitialize`).subscribe(() => {
      alert('Reinitialisation effectuée');
    });
  }

  assignCategories() {
    this.http.get(`${env.api}/admin/assign-categories`).subscribe(() => {
      alert('Assignation des catégories effectuée');
    });
  }
}
