import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SideNavComponent } from './components/side-nav/side-nav.component';
import { HeaderComponent } from './components/header/header.component';
import { FullPageLayoutComponent } from './layouts/full-page-layout/full-page-layout.component';
import { AppLayoutComponent } from './layouts/app-layout/app-layout.component';
import { TestComponent } from './views/test/test.component';
import { MapComponent } from './views/map/map.component';
import { LeafletModule } from '@asymmetrik/ngx-leaflet';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PaginationComponent } from './components/pagination/pagination.component';
import { SignInComponent } from './views/sign-in/sign-in.component';
import { TokenInterceptor } from './interceptors/TokenInterceptor';
import { StageListComponent } from './views/stage-list/stage-list.component';
import { GeneralRankingComponent } from './views/ranking/general-ranking/general-ranking.component';
import { TeamRankingComponent } from './views/ranking/team-ranking/team-ranking.component';
import { AssignRunnerComponent } from './views/team/assign-runner/assign-runner.component';
import { AssignRunnerTimeComponent } from './views/admin/assign-runner-time/assign-runner-time.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { provideCharts, withDefaultRegisterables } from 'ng2-charts';
import { RunnerRankingComponent } from './views/ranking/runner-ranking/runner-ranking.component';
import { ImportationComponent } from './views/admin/importation/importation.component';

@NgModule({
  declarations: [
    AppComponent,
    SideNavComponent,
    HeaderComponent,
    FullPageLayoutComponent,
    AppLayoutComponent,
    TestComponent,
    MapComponent,
    PaginationComponent,
    SignInComponent,
    StageListComponent,
    GeneralRankingComponent,
    TeamRankingComponent,
    AssignRunnerComponent,
    AssignRunnerTimeComponent,
    RunnerRankingComponent,
    ImportationComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    LeafletModule,
    FormsModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true,
    },
    provideAnimationsAsync(),
    provideCharts(withDefaultRegisterables()),
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
