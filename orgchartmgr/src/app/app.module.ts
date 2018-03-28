import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {Router} from "@angular/router";
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";

import {DepartmentsComponent} from './components/departments/departments.component';
import {EmployeesComponent} from './components/employees/employees.component';
import {PageNotFoundComponent} from './components/page-not-found/page-not-found.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {DepartmentDtoComponent } from './model/department-dto/department-dto.component';
import {DepartmentService} from "./services/department.service";


@NgModule({
 declarations: [
   AppComponent,
   DepartmentsComponent,
   EmployeesComponent,
   PageNotFoundComponent,

   NavigationComponent,

   DepartmentDtoComponent
 ],
 imports: [
   BrowserModule,
   HttpClientModule,
   NgbModule.forRoot(),
   AppRoutingModule
 ],
 providers: [DepartmentService],
 bootstrap: [AppComponent]
})
export class AppModule {
 constructor(router: Router) {
   console.log('Routes: ', JSON.stringify(router.config, undefined, 2));
 }
}
