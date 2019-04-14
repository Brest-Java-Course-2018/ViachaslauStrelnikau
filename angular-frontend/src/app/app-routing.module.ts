import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {GroupsListComponent} from "./modules/groups/groups-list/groups-list.component";

const routes: Routes = [

  {
    path: '**',
    component: GroupsListComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
