import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppLayoutComponent } from './layouts/app-layout/app-layout.component';
import { FullPageLayoutComponent } from './layouts/full-page-layout/full-page-layout.component';
import { SignInComponent } from './views/sign-in/sign-in.component';
import { authGuard } from './guards/auth.guard';
import { adminGuard } from './guards/admin.guard';
import { teamGuard } from './guards/team.guard';
import { StageListComponent } from './views/stage-list/stage-list.component';
import { TeamRankingComponent } from './views/ranking/team-ranking/team-ranking.component';
import { AssignRunnerTimeComponent } from './views/admin/assign-runner-time/assign-runner-time.component';
import { AssignRunnerComponent } from './views/team/assign-runner/assign-runner.component';
import { RunnerRankingComponent } from './views/ranking/runner-ranking/runner-ranking.component';
import { GeneralRankingComponent } from './views/ranking/general-ranking/general-ranking.component';
import { ImportationComponent } from './views/admin/importation/importation.component';
import { StageAssignationComponent } from './views/team/stage-assignation/stage-assignation.component';
import { CertificateComponent } from './views/admin/certificate/certificate.component';
import { PenaltyAssignationComponent } from './views/admin/penalty-assignation/penalty-assignation.component';
import { StageGeneralRankingComponent } from './views/ranking/stage-general-ranking/stage-general-ranking.component';
import { TeamDetailsRankingComponent } from './views/ranking/team-details-ranking/team-details-ranking.component';

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
        component: RunnerRankingComponent,
      },
      {
        path: 'stage-ranking/:stageId',
        component: StageGeneralRankingComponent,
      },
      {
        path: 'team-ranking-details/:teamId',
        component: TeamDetailsRankingComponent,
      },
      {
        path: 'general-ranking-per-stage',
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
        path: 'admin/importation',
        canActivate: [adminGuard],
        component: ImportationComponent,
      },
      {
        path: 'admin/penalty-assignation',
        canActivate: [adminGuard],
        component: PenaltyAssignationComponent,
      },
      {
        path: 'team/assign-runner',
        canActivate: [teamGuard],
        component: AssignRunnerComponent,
      },
      {
        path: 'team/stage-assignation',
        canActivate: [teamGuard],
        component: StageAssignationComponent,
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
      {
        path: 'certificate/:category',
        component: CertificateComponent,
        canActivate: [adminGuard],
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
