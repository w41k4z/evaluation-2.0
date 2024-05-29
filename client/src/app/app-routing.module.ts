import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppLayoutComponent } from './layouts/app-layout/app-layout.component';
import { TestComponent } from './views/test/test.component';
import { FullPageLayoutComponent } from './layouts/full-page-layout/full-page-layout.component';
import { MapComponent } from './views/map/map.component';

const routes: Routes = [
  {
    path: 'app',
    component: AppLayoutComponent,
    children: [
      {
        path: 'test',
        component: TestComponent,
      },
      {
        path: 'map',
        component: MapComponent,
      },
    ],
  },
  {
    path: 'page',
    component: FullPageLayoutComponent,
    children: [],
  },
  { path: '', redirectTo: '/app/test', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
