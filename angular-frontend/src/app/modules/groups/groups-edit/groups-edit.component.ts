import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";

@Component({
  selector: 'app-groups-edit',
  templateUrl: './groups-edit.component.html',
  styleUrls: ['./groups-edit.component.scss']
})
export class GroupsEditComponent implements OnInit {


  title;
  constructor(
    public dialogRef: MatDialogRef<GroupsEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data:number) {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  ngOnInit() {
    this.title=this.data?'Edit '+this.data+' record':'Add record';
  }

}
