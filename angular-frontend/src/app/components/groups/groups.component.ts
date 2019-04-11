import { Component, OnInit } from '@angular/core';
import {GroupServiceService} from "../../service/group-service.service";
import {GroupDto} from "../../model/group-dto";
import {MatTableDataSource} from "@angular/material";

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.scss']
})
export class GroupsComponent implements OnInit {
  groups:GroupDto[]=[];
  dataSource = new MatTableDataSource<GroupDto>(this.groups);
  displayedColumns: string[] = ['groupId', 'shortName', 'fullName', 'groupAvgMarks'];
  constructor(private groupService:GroupServiceService) {}

  title='Student management app';
  ngOnInit() {
    this.getGroups();
  }

  getGroups()
  {

  this.groupService.getGroups().subscribe(data => {
    this.groups = data;
    this.dataSource= new MatTableDataSource<GroupDto>(this.groups);
    console.log(this.displayedColumns);
  });

  }

}
