import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {GroupsComponent} from "./components/groups/groups.component";

const routes: Routes = [

  {
    path: '**',
    component: GroupsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
