import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {DepartmentsComponent, EmployeesComponent, PageNotFoundComponent} from "./components";
const routes: Routes = [
  {
	path: 'departments',
	component: DepartmentsComponent
  },
  {
	path: 'employees',
	component: EmployeesComponent
  },
  {
	path: '',
	redirectTo: '/departments',
	pathMatch: 'full'
  },
  {
	path: '**',
	component: PageNotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
