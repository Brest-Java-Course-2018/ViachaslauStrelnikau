import {Component, OnInit} from '@angular/core';
import {GroupServiceService} from "../../service/group-service.service";
import {GroupDto} from "../../model/group-dto";
import {MatDialog} from "@angular/material";
import {DeleteDialogComponent} from "../../../../projects/components/src/lib/delete-dialog/delete-dialog.component";

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.scss']
})
export class GroupsComponent implements OnInit {
  groups: GroupDto[] = [];
  displayedColumns: string[] = ['groupId', 'shortName', 'fullName', 'groupAvgMarks', 'star'];

  constructor(private groupService: GroupServiceService,public dialog: MatDialog) {
  }

  title = 'Student management app';

  ngOnInit() {
    this.getGroups();
  }

  getGroups() {
    this.groupService.getGroups().subscribe(data => {
      this.groups = data;
      console.log(this.displayedColumns);
    });
  }

  openDeleteDialog(input:string)
  {

    const dialogRef = this.dialog.open(DeleteDialogComponent, {
      data:input,
      width: '250px',
    });
    dialogRef.afterClosed().subscribe(result => {

      if(result)
      {
        console.log('DELETE!!!!!!!!!'+input);
      }
    });
  }

}
