import {Component, OnInit} from '@angular/core';
import {GroupDto} from "../../../model/group-dto";
import {GroupServiceService} from "../../../service/group-service.service";
import {MatDialog} from "@angular/material";
import {DeleteDialogComponent} from "../../../../../projects/components/src/lib/delete-dialog/delete-dialog.component";
import {GroupsEditComponent} from "../groups-edit/groups-edit.component";

@Component({
  selector: 'app-groups-list',
  templateUrl: './groups-list.component.html',
  styleUrls: ['./groups-list.component.scss']
})
export class GroupsListComponent implements OnInit {

  groups: GroupDto[] = [];
  displayedColumns: string[] = ['groupId', 'shortName', 'fullName', 'groupAvgMarks', 'star'];

  constructor(private groupService: GroupServiceService, public deleteDialog: MatDialog) {
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

  openDeleteDialog(input: string) {
    const dialogRef = this.deleteDialog.open(DeleteDialogComponent, {
      data: input,
      width: '250px',
    });
    dialogRef.afterClosed().subscribe(result => {

      if (result) {
        this.groupService.removeGroup(input).subscribe(response => {
          this.getGroups();
        });
      }
    });
  }
  openEditDialog(input: string)
  {
console.log('edit')!;
    const dialogRef = this.deleteDialog.open(GroupsEditComponent, {
      data: input,
      width: '250px',
    });
  }

}
