import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { ProfileService } from '../services/profile/profile.service';

export const adminGuard: CanActivateFn = () => {
  const profileService = inject(ProfileService);
  const router = inject(Router);

  if (!profileService.isAdmin()) {
    router.navigateByUrl('/page/log-in');
    return false;
  }
  return true;
};
