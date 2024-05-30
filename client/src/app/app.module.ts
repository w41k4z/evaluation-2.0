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
import { tokenInterceptor } from './interceptors/token.interceptor';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    SideNavComponent,
    HeaderComponent,
    FullPageLayoutComponent,
    AppLayoutComponent,
    TestComponent,
    MapComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    LeafletModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useValue: tokenInterceptor,
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
