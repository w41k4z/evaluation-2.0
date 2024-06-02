import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppLayoutComponent } from './layouts/app-layout/app-layout.component';
import { FullPageLayoutComponent } from './layouts/full-page-layout/full-page-layout.component';
import { SignInComponent } from './views/sign-in/sign-in.component';
import { authGuard } from './guards/auth.guard';
import { adminGuard } from './guards/admin.guard';
import { teamGuard } from './guards/team.guard';
import { StageListComponent } from './views/stage-list/stage-list.component';
import { GeneralRankingComponent } from './views/ranking/general-ranking/general-ranking.component';
import { TeamRankingComponent } from './views/ranking/team-ranking/team-ranking.component';
import { AssignRunnerTimeComponent } from './views/admin/assign-runner-time/assign-runner-time.component';
import { AssignRunnerComponent } from './views/team/assign-runner/assign-runner.component';

const routes: Routes = [
  {
    path: 'app',
    component: AppLayoutComponent,
    canActivate: [authGuard],
    children: [
      {
        path: 'stage-list',
        component: StageListComponent,
      },
      {
        path: 'general-ranking',
        component: GeneralRankingComponent,
      },
      {
        path: 'team-ranking',
        component: TeamRankingComponent,
      },
      {
        path: 'admin/assign-runner-time',
        canActivate: [adminGuard],
        component: AssignRunnerTimeComponent,
      },
      {
        path: 'team/assign-runner',
        canActivate: [teamGuard],
        component: AssignRunnerComponent,
      },
    ],
  },
  {
    path: 'page',
    component: FullPageLayoutComponent,
    children: [
      {
        path: 'log-in',
        component: SignInComponent,
      },
    ],
  },
  { path: '', redirectTo: '/page/log-in', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
