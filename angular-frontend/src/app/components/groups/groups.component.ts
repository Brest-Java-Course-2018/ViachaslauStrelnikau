import { Component, OnInit } from '@angular/core';
import {GroupServiceService} from "../../service/group-service.service";
import {GroupDto} from "../../model/group-dto";
import {Observable} from "rxjs";

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.scss']
})
export class GroupsComponent implements OnInit {
  groups:GroupDto[]=[];
  constructor(private groupService:GroupServiceService) {}

  ngOnInit() {
    this.getGroups();
  }

  getGroups()
  {

  this.groupService.getGroups().subscribe(data => {
    this.groups = data;
  });

  }

}
