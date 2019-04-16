import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {GroupsListComponent} from "./modules/groups/groups-list/groups-list.component";
import {StudentsListComponent} from "./modules/students/students-list/students-list.component";

const routes: Routes = [
  {
    path: 'students',
    component: StudentsListComponent
  },
  {
    path: 'groups',
    component: GroupsListComponent
  },
  {
    path: '**',
    redirectTo:'groups'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
