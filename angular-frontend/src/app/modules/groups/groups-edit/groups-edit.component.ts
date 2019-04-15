import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {Group} from "../../../model/group";

@Component({
  selector: 'app-groups-edit',
  templateUrl: './groups-edit.component.html',
  styleUrls: ['./groups-edit.component.scss']
})
export class GroupsEditComponent implements OnInit {

  title;
  constructor(
    public dialogRef: MatDialogRef<GroupsEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Group) {
  }
  onNoClick(): void {
    this.dialogRef.close();
  }

  ngOnInit() {
    this.title=this.data.groupId?'Edit '+this.data.groupId+' record':'Add record';
  }

}
