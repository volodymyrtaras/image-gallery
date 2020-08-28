import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './components/login/login.component';
import {RegistrationComponent} from './components/registration/registration.component';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {HomeComponent} from './components/home/home.component';
import {Cloudinary, CloudinaryModule} from '@cloudinary/angular-5.x';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatDialogModule} from '@angular/material/dialog';
import {MatTableModule} from '@angular/material/table';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatMenuModule} from '@angular/material/menu';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatSelectModule} from '@angular/material/select';
import {MatCardModule} from '@angular/material/card';
import {MatGridListModule} from '@angular/material/grid-list';
import {InfiniteScrollModule} from 'ngx-infinite-scroll';
import {SlideshowModule} from 'ng-simple-slideshow';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    HomeComponent
  ],
  imports: [
    BrowserAnimationsModule,
    MatButtonModule,
    MatCheckboxModule,
    MatFormFieldModule,
    MatInputModule,
    MatMenuModule,
    MatSidenavModule,
    MatToolbarModule,
    MatTableModule,
    MatDialogModule,
    MatDatepickerModule,
    MatSelectModule,
    MatCardModule,
    MatGridListModule,
    InfiniteScrollModule,
    SlideshowModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    CloudinaryModule.forRoot(Cloudinary, {cloud_name: 'dhlyhn67g'})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
