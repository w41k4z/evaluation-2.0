import { Component } from '@angular/core';
import { ProfileService } from '../../services/profile/profile.service';

@Component({
  selector: 'app-side-nav',
  templateUrl: './side-nav.component.html',
  styleUrl: './side-nav.component.scss',
})
export class SideNavComponent {
  constructor(public profile: ProfileService) {}

  reinitialize() {
    this.profile.reinitialize();
  }
}
